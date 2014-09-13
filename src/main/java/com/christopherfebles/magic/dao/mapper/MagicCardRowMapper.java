package com.christopherfebles.magic.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.christopherfebles.magic.enums.Language;
import com.christopherfebles.magic.model.MagicCard;

/**
 * Map a database row from All_Cards to the MagicCard data type.
 * 
 * @author Christopher Febles
 *
 */
public class MagicCardRowMapper implements RowMapper<MagicCard> {

    private static final Logger LOG = LoggerFactory.getLogger( MagicCardRowMapper.class );

    @Override
    public MagicCard mapRow( ResultSet resultSet, int rowNum ) throws SQLException {

        int multiverseId = resultSet.getInt( "multiverse_id" );
        if ( resultSet.wasNull() ) {
            return null;
        }

        MagicCard card = new MagicCard();
        card.setMultiverseId( multiverseId );
        card.setName( resultSet.getString( "name" ) );
        LOG.trace( "Mapping card {} with id {}", card.getName(), card.getMultiverseId() );

        // Process card colors
        // Colors now calculated as a side-effect of setting Mana cost

        // Process card cost
        String manaCost = resultSet.getString( "cost" );
        card.setManaCostWithString( manaCost );
        LOG.trace( "Mana cost originally {}, set to {} for card {} with id {}", manaCost, card.getManaCostString(), card.getName(), card.getMultiverseId() );

        // Process types
        card.setTypes( resultSet.getString( "types" ) );

        card.setText( resultSet.getString( "text" ) );
        card.setFlavorText( resultSet.getString( "flavor_text" ) );

        card.setPower( resultSet.getString( "power" ) );
        card.setToughness( resultSet.getString( "toughness" ) );

        card.setExpansion( resultSet.getString( "expansion" ) );
        card.setRarity( resultSet.getString( "rarity" ) );

        // Do not load images from the database here to avoid out of memory errors.
        // Images load on demand in the interface.

        card.setArtist( resultSet.getString( "artist" ) );
        card.setNumber( resultSet.getString( "number" ) );
        card.setWatermark( resultSet.getString( "watermark" ) );

        String languageStr = resultSet.getString( "language" );
        if ( StringUtils.isNotEmpty( languageStr ) ) {
            card.setLanguage( Language.getLanguageByValue( languageStr ) );
        }

        return card;
    }

}
