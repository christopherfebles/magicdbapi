package com.christopherfebles.magic.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.christopherfebles.magic.dao.MagicCardDAO;
import com.christopherfebles.magic.dao.exception.DuplicateRowException;
import com.christopherfebles.magic.dao.mapper.MagicCardRowMapper;
import com.christopherfebles.magic.enums.CardType;
import com.christopherfebles.magic.enums.Language;
import com.christopherfebles.magic.model.MagicCard;

import static com.christopherfebles.magic.dao.MagicDAOConstants.*;

@Repository
/**
 * At some point, test out using Hibernate instead of Spring JDBC. 
 * 
 * @author Christopher Febles
 *
 */
public class MagicCardDAOImpl implements MagicCardDAO {

    private static final Logger LOG = LoggerFactory.getLogger( MagicCardDAOImpl.class );
    
    //Error messages
    private static final String NULL_MULTIVERSEID_ERROR_MESSAGE = "multiverseId cannot be null.";
    private static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error when accessing database.";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource( DataSource dataSource ) {
        jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
    }

    @Override
    public boolean isCardOwned( Integer multiverseId ) {
        return this.numberOfOwnedCard( multiverseId ) > 0;
    }

    @Override
    public int numberOfOwnedCard( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        String query = "Select count from " + MY_CARDS_TABLE + " where multiverse_id = :id";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );
        SqlParameterSource paramSource = new MapSqlParameterSource( "id", multiverseId );

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );

        int numCards = 0;
        while ( rowSet.next() ) {
            if ( numCards != 0 ) {
                // This query should never return more than one row
                throw new DuplicateRowException( MY_CARDS_TABLE_NO_ALIAS + " has duplicate rows for multiverse_id: " + multiverseId );
            }
            numCards = rowSet.getInt( "count" );
        }

        return numCards;
    }

    @Override
    public boolean addOwnedCard( Integer multiverseId ) {
        return this.incrementOwnedCard( multiverseId );
    }

    @Override
    public boolean incrementOwnedCard( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        int numOwned = this.numberOfOwnedCard( multiverseId );

        numOwned++;
        int rowsAffected = this.updateOwnedCardCount( multiverseId, numOwned );
        return rowsAffected > 0;
    }

    @Override
    public boolean decrementOwnedCard( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        int numOwned = this.numberOfOwnedCard( multiverseId );

        numOwned--;
        int rowsAffected = this.updateOwnedCardCount( multiverseId, numOwned );
        return rowsAffected > 0;
    }

    @Override
    public int updateOwnedCardCount( Integer multiverseId, Integer newCardCount ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );

        if ( newCardCount <= 0 ) {
            // Delete Row
            boolean success = this.deleteCardByIdFromTable( multiverseId, MY_CARDS_TABLE_NO_ALIAS );
            if ( success ) {
                return 1;
            } else {
                return 0;
            }
        }

        String query = "Insert Into " + MY_CARDS_TABLE_NO_ALIAS + " ( multiverse_id, count ) " + "Values( :multiverse_id, :count ) "
                + "On Duplicate Key Update multiverse_id=Values(multiverse_id), count=Values(count) ";

        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        // Insert parameters
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put( "multiverse_id", multiverseId );
        namedParameters.put( "count", newCardCount );

        // Execute query
        return jdbcTemplate.update( query, namedParameters );
    }

    @Override
    public boolean removeCardFromDatabaseById( Integer multiverseId ) {
        return this.deleteCardByIdFromTable( multiverseId, ALL_CARDS_TABLE_NO_ALIAS );
    }

    @Override
    public boolean removeOwnedCardById( Integer multiverseId ) {
        return this.deleteCardByIdFromTable( multiverseId, MY_CARDS_TABLE_NO_ALIAS );
    }

    private boolean deleteCardByIdFromTable( Integer multiverseId, String table ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        String query = "Delete From " + table + " where multiverse_id = :id";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );
        SqlParameterSource paramSource = new MapSqlParameterSource( "id", multiverseId );

        int rowsAffected = jdbcTemplate.update( query, paramSource );

        return rowsAffected > 0;
    }

    @Override
    public boolean saveCardToDatabase( MagicCard card ) {
        return this.addCardToDatabase( card );
    }

    @Override
    public synchronized boolean addCardToDatabase( MagicCard card ) {

        Validate.notNull( card, "MagicCard cannot be null." );

        String query = "";
        // Not sure if I need to do this, because of On Duplicate Key in MySQL
        if ( this.isCardInDatabase( card.getMultiverseId() ) ) {
            // Update SQL
            query = "Update " + ALL_CARDS_TABLE_NO_ALIAS + " Set " + "name = :name, " + "cost = :cost, " + "converted_cost = :convertedCost, "
                    + "types = :types, " + "text = :text, " + "power = :power, " + "toughness = :toughness, " + "expansion = :expansion, " + "color = :color, "
                    + "flavor_text = :flavor, " + "rarity = :rarity, " + "image = :image, " + "artist = :artist, " + "number = :number, "
                    + "watermark = :watermark, " + "language = :language," + "touched_by_updater = now() " + "Where multiverse_id = :id";
        } else {
            // Insert SQL
            query = "Insert Into "
                    + ALL_CARDS_TABLE_NO_ALIAS
                    + "(multiverse_id, name, cost, converted_cost, types, text, power, toughness, expansion, color, flavor_text, rarity, image, artist, number, watermark, language ) "
                    + "Values(:id, :name, :cost, :convertedCost, :types, :text, :power, :toughness, :expansion, :color, :flavor, :rarity, :image, :artist, :number, :watermark, :language) "
                    + "On Duplicate Key Update multiverse_id=Values(multiverse_id), name=Values(name), cost=Values(cost), "
                    + "converted_cost=Values(converted_cost), types=Values(types), "
                    + "text=Values(text), power=Values(power), toughness=Values(toughness), "
                    + "expansion=Values(expansion), color=Values(color), flavor_text=Values(flavor_text), "
                    + "rarity=Values(rarity), image=Values(image), artist=Values(artist), number=Values(number), watermark=Values(watermark), language=Values(language), touched_by_updater=now() ";
        }
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        // Insert parameters
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put( "id", card.getMultiverseId() );
        namedParameters.put( "name", card.getName() );
        namedParameters.put( "cost", card.getManaCostString() );
        namedParameters.put( "convertedCost", card.getConvertedCost() );
        namedParameters.put( "types", card.getType() );
        namedParameters.put( "text", card.getText() );
        namedParameters.put( "power", card.getPower() );
        namedParameters.put( "toughness", card.getToughness() );
        namedParameters.put( "expansion", card.getExpansion() );
        namedParameters.put( "color", card.getColorsString() );
        namedParameters.put( "flavor", card.getFlavorText() );
        namedParameters.put( "rarity", card.getRarity() );
        namedParameters.put( "image", card.getCardImageArray() );
        namedParameters.put( "artist", card.getArtist() );
        namedParameters.put( "number", card.getNumber() );
        namedParameters.put( "watermark", card.getWatermark() );

        Language lang = card.getLanguage();
        String langStr = null;
        if ( lang != null ) {
            langStr = lang.toString();
        }
        namedParameters.put( "language", langStr );

        // Execute query
        int rowsAffected = jdbcTemplate.update( query, namedParameters );

        // Update Types
        this.updateCardTypesInDatabase( card );

        return rowsAffected > 0;
    }

    private void updateCardTypesInDatabase( MagicCard card ) {

        String query = "Insert Into " + CARD_TYPE_TABLE_NO_ALIAS + " ( multiverse_id, type_name ) " + "Values( :multiverse_id, :type_name ) "
                + "On Duplicate Key Update multiverse_id=Values(multiverse_id), type_name=Values(type_name) ";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put( "multiverse_id", card.getMultiverseId() );

        // Store each type in the database
        for ( CardType type : card.getSuperTypes() ) {
            namedParameters.put( "type_name", type.toString() );
            jdbcTemplate.update( query, namedParameters );
        }
        for ( CardType type : card.getTypes() ) {
            namedParameters.put( "type_name", type.toString() );
            jdbcTemplate.update( query, namedParameters );
        }
        for ( CardType type : card.getSubTypes() ) {
            namedParameters.put( "type_name", type.toString() );
            jdbcTemplate.update( query, namedParameters );
        }

    }

    @Override
    public MagicCard getCardFromDatabaseById( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        Validate.isTrue( multiverseId >= 0, "multiverseId cannot be negative." );

        String query = "Select " + CARD_SELECT_COLUMNS + " From " + ALL_CARDS_TABLE + " Where multiverse_id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource( "id", multiverseId );
        MagicCard card = null;

        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            List<MagicCard> cardList = jdbcTemplate.query( query, paramSource, new MagicCardRowMapper() );
            if ( CollectionUtils.isNotEmpty( cardList ) ) {
                card = cardList.get( 0 );
            }
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            card = null;
        }

        return card;
    }

    @Override
    public boolean isCardInDatabase( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        Validate.isTrue( multiverseId >= 0, "multiverseId cannot be negative." );

        String query = "Select 1 from " + ALL_CARDS_TABLE + " where multiverse_id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource( "id", multiverseId );
        boolean exists = false;
        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            exists = jdbcTemplate.queryForObject( query, paramSource, Integer.class ) != null;
        } catch ( IncorrectResultSizeDataAccessException e ) {
            // If no result rows are returned, this exception is thrown
            // No need to log the stack trace, as this is an expected condition.
            LOG.trace( "No results returned for query: {} with id: {}", query, multiverseId );
            exists = false;
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            exists = false;
        }

        return exists;
    }

    @Override
    public int numberOfCardsOwned() {
        return this.cardCount( MY_CARDS_TABLE );
    }

    @Override
    public int numberOfCardsInDatabase() {
        return this.cardCount( ALL_CARDS_TABLE );
    }

    private int cardCount( String table ) {

        String query = "Select Count(*) From " + table;
        SqlParameterSource paramSource = null;
        int numCards = -1;

        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            numCards = jdbcTemplate.queryForObject( query, paramSource, Integer.class );
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            numCards = -1;
        }

        return numCards;
    }

    @Override
    public List<MagicCard> getOwnedCards() {

        String query = "Select " + CARD_SELECT_COLUMNS + " From " + ALL_CARDS_TABLE + " join " + MY_CARDS_TABLE + " on " + ALL_CARDS_PREFIX
                + "multiverse_id = " + MY_CARDS_PREFIX + "multiverse_id";

        List<MagicCard> ownedCards = null;

        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            ownedCards = jdbcTemplate.query( query, new MagicCardRowMapper() );
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            ownedCards = null;
        }

        return ownedCards;
    }

    @Override
    public int getHighestMultiverseId() {

        String query = "Select max(multiverse_id) from " + ALL_CARDS_TABLE;
        SqlParameterSource paramSource = null;
        int maxId = -1;

        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            maxId = jdbcTemplate.queryForObject( query, paramSource, Integer.class );
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            maxId = -1;
        }

        return maxId;
    }

    @Override
    public List<Integer> getAllMultiverseIds() {

        String query = "Select multiverse_id from " + ALL_CARDS_TABLE + " order by multiverse_id";
        SqlParameterSource paramSource = null;
        List<Integer> retVal = null;
        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            retVal = jdbcTemplate.queryForList( query, paramSource, Integer.class );
        } catch ( DataAccessException e ) {
            LOG.error( UNEXPECTED_ERROR_MESSAGE, e );
            retVal = null;
        }

        return retVal;
    }

    @Override
    public byte[] getCardImageById( Integer multiverseId ) {

        Validate.notNull( multiverseId, NULL_MULTIVERSEID_ERROR_MESSAGE );
        String query = "Select image from " + ALL_CARDS_TABLE + " where multiverse_id = :id";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );
        SqlParameterSource paramSource = new MapSqlParameterSource( "id", multiverseId );

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );

        byte[] imageArray = null;
        while ( rowSet.next() ) {
            if ( imageArray != null ) {
                // This query should never return more than one row
                throw new DuplicateRowException( ALL_CARDS_TABLE + " has duplicate rows for multiverse_id: " + multiverseId );
            }
            try {
                imageArray = ( ( ResultSetWrappingSqlRowSet ) rowSet ).getResultSet().getBytes( "image" );
            } catch ( SQLException e ) {
                LOG.error( "Error loading image from the database for multiverse_id {}", multiverseId, e );
            }
        }

        return imageArray;
    }

}
