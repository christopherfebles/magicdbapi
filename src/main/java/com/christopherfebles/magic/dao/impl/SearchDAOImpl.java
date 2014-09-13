package com.christopherfebles.magic.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.christopherfebles.magic.dao.SearchDAO;
import com.christopherfebles.magic.dao.mapper.MagicCardRowMapper;
import com.christopherfebles.magic.dao.parameter.SearchParameter;
import com.christopherfebles.magic.model.MagicCard;

import static com.christopherfebles.magic.dao.MagicDAOConstants.*;

@Repository
public class SearchDAOImpl implements SearchDAO {

    private static final Logger LOG = LoggerFactory.getLogger( SearchDAOImpl.class );

    private static final String DEFAULT_ORDER_BY_CLAUSE = " order by " + ALL_CARDS_PREFIX + "name, " + ALL_CARDS_PREFIX + "multiverse_id ";
    private static final int DEFAULT_PAGE_SIZE = 10;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource( DataSource dataSource ) {
        jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
    }

    @Override
    public MagicCard getNextWithSearchParameters( int pointer, List<SearchParameter> searchParams ) {
        List<MagicCard> searchResults = this.getPageWithSearchParametersAndPageSize( pointer, 1, searchParams );

        MagicCard retVal = null;
        if ( searchResults != null && !searchResults.isEmpty() ) {
            retVal = searchResults.get( 0 );
        }

        return retVal;
    }

    @Override
    public List<MagicCard> getPageWithSearchParameters( int pageNumber, List<SearchParameter> searchParams ) {

        Validate.isTrue( pageNumber > 0, "The first page is page one." );

        return this.getPageWithSearchParametersAndPageSize( pageNumber, DEFAULT_PAGE_SIZE, searchParams );
    }

    @Override
    public List<MagicCard> getAllWithSearchParameters( List<SearchParameter> searchParams ) {
        return this.getPageWithSearchParametersAndPageSize( -1, -1, searchParams );
    }

    @Override
    public List<MagicCard> getPageWithSearchParametersAndPageSize( int pageNumber, int pageSize, List<SearchParameter> searchParams ) {

        String query = "Select " + CARD_SELECT_COLUMNS + this.generateSQLWithSearchParameters( searchParams );

        if ( pageNumber > 0 ) {
            int limit = pageSize;
            int offset = ( limit * ( pageNumber - 1 ) );

            query += " limit " + limit + " offset " + offset;
        }
        LOG.debug( DATABASE_QUERY_LOG_MSG, query );

        Map<String, String> parameters = new HashMap<>();
        for ( SearchParameter parm : searchParams ) {
            parameters.put( parm.getParameterName(), parm.getSearchText() );
        }

        SqlParameterSource paramSource = new MapSqlParameterSource( parameters );
        return this.loadCardsWithSQLQuery( query, paramSource );
    }

    @Override
    public List<MagicCard> getPageByNameWithSearchParameters( int pageNumber, List<SearchParameter> searchParams ) {
        Validate.isTrue( pageNumber > 0, "The first page is page one." );

        return this.getPageByNameWithSearchParametersAndPageSize( pageNumber, DEFAULT_PAGE_SIZE, searchParams );
    }

    @Override
    public List<MagicCard> getAllByNameWithSearchParameters( List<SearchParameter> searchParams ) {
        return this.getPageByNameWithSearchParametersAndPageSize( -1, -1, searchParams );
    }

    @Override
    public MagicCard getNextByNameWithSearchParameters( int pointer, List<SearchParameter> searchParams ) {

        List<MagicCard> searchResults = this.getPageByNameWithSearchParametersAndPageSize( pointer, 1, searchParams );

        MagicCard retVal = null;
        if ( searchResults != null && !searchResults.isEmpty() ) {
            retVal = searchResults.get( 0 );
        }

        return retVal;
    }

    @Override
    public List<MagicCard> getPageByNameWithSearchParametersAndPageSize( int pageNumber, int pageSize, List<SearchParameter> searchParams ) {

        String query = "Select group_concat(" + ALL_CARDS_PREFIX + "multiverse_id) " + this.generateSQLWithSearchParameters( searchParams );

        // Remove order by clause
        query = query.replace( DEFAULT_ORDER_BY_CLAUSE, "" );

        // Add group by clause
        query += " Group By " + ALL_CARDS_PREFIX + "name ";

        // Re-add order by clause
        query += DEFAULT_ORDER_BY_CLAUSE;

        if ( pageNumber > 0 ) {
            int limit = pageSize;
            int offset = ( limit * ( pageNumber - 1 ) );

            query += " limit " + limit + " offset " + offset;
        }
        LOG.debug( DATABASE_QUERY_LOG_MSG, query );

        Map<String, String> parameters = new HashMap<>();
        for ( SearchParameter parm : searchParams ) {
            parameters.put( parm.getParameterName(), parm.getSearchText() );
        }

        SqlParameterSource paramSource = new MapSqlParameterSource( parameters );
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );

        String inClause = null;
        while ( rowSet.next() ) {
            String result = rowSet.getString( 1 );
            if ( inClause == null ) {
                inClause = result;
            } else {
                inClause += "," + result;
            }
        }
        if ( inClause == null ) {
            // No cards exist for the given search
            return new ArrayList<>();
        }
        inClause = inClause.replace( ",,", "," );
        if ( inClause.endsWith( "," ) ) {
            inClause = inClause.substring( 0, inClause.length() - 1 );
        }

        query = "Select " + CARD_SELECT_COLUMNS + " From " + ALL_CARDS_TABLE + " Where " + ALL_CARDS_PREFIX + "multiverse_id in ( " + inClause + " )";
        LOG.debug( DATABASE_QUERY_LOG_MSG, query );

        return this.loadCardsWithSQLQuery( query, null );
    }

    @Override
    public int numberOfUniqueNamesWithSearchParameters( List<SearchParameter> searchParams ) {
        return this.getCountWithCustomQueryAndSearchParameters( "Select Count(distinct " + ALL_CARDS_PREFIX + "name) ", searchParams );
    }

    @Override
    public int numberOfResultsWithSearchParameters( List<SearchParameter> searchParams ) {
        return this.getCountWithCustomQueryAndSearchParameters( "Select Count(*) ", searchParams );
    }

    private int getCountWithCustomQueryAndSearchParameters( String selectClause, List<SearchParameter> searchParams ) {

        String query = selectClause + this.generateSQLWithSearchParameters( searchParams );

        Map<String, String> parameters = new HashMap<>();
        for ( SearchParameter parm : searchParams ) {
            parameters.put( parm.getParameterName(), parm.getSearchText() );
        }

        SqlParameterSource paramSource = new MapSqlParameterSource( parameters );
        int numCards = -1;

        try {
            LOG.debug( DATABASE_QUERY_LOG_MSG, query );
            numCards = jdbcTemplate.queryForObject( query, paramSource, Integer.class );
        } catch ( DataAccessException e ) {
            LOG.error( "Unexpected error when accessing database.", e );
            numCards = -1;
        }

        return numCards;

    }

    /**
     * Generate SQL based on the search options provided.
     * 
     * @param searchParams
     *            The list of search parameters to convert to SQL
     * @return A partial (FROM, WHERE, and DEFAULT_ORDER_BY_CLAUSE) SQL statement, parameterized by name
     */
    private String generateSQLWithSearchParameters( List<SearchParameter> searchParams ) {

        String fromClause = " From " + ALL_CARDS_TABLE + " ";

        String whereClause = "";
        boolean addWhere = true;

        for ( SearchParameter parm : searchParams ) {

            if ( addWhere ) {
                whereClause += " Where ";
                addWhere = false;
            } else if ( parm.isAnd() ) {
                whereClause += " And ";
            } else {
                whereClause += " Or ";
            }

            if ( parm.getFieldName().getTablePrefix().equals( ALL_CARDS_PREFIX ) ) {
                // MySQL LIKE has same performance as = when no % is present in parameter
                whereClause += " " + parm.getFieldName().getColumnName() + " like :" + parm.getParameterName();
            } else if ( parm.getFieldName().getTablePrefix().equals( CARD_TYPE_PREFIX ) || parm.getFieldName().getTablePrefix().equals( MY_CARDS_PREFIX ) ) {
                // Instead of a join, add subqueries for simplicity
                // All tables will join by multiverse_id
                whereClause += ALL_CARDS_PREFIX + "multiverse_id in " + "( Select " + parm.getFieldName().getTablePrefix() + "multiverse_id " + "From "
                        + parm.getFieldName().getTableName();

                if ( parm.getFieldName().getTablePrefix().equals( CARD_TYPE_PREFIX ) ) {
                    whereClause += "Where " + parm.getFieldName().getColumnName() + " like :" + parm.getParameterName();
                }
                whereClause += " ) ";
            }
        }

        return fromClause + whereClause + DEFAULT_ORDER_BY_CLAUSE;
    }

    @Override
    public int numberOfResultsPerPage() {
        return DEFAULT_PAGE_SIZE;
    }

    /**
     * Common method for loading a list of cards from the database
     * 
     * @param query
     *            The query to run against the databasse
     * @param paramSource
     *            Parameters for the given query, or null if no parameters required
     * @return A list of results, or null if an error occurred
     */
    private List<MagicCard> loadCardsWithSQLQuery( String query, SqlParameterSource paramSource ) {

        List<MagicCard> allCards = null;

        try {
            LOG.trace( DATABASE_QUERY_LOG_MSG, query );
            allCards = jdbcTemplate.query( query, paramSource, new MagicCardRowMapper() );
        } catch ( DataAccessException e ) {
            LOG.error( "Unexpected error when accessing database.", e );
            allCards = null;
        }

        return allCards;
    }
}
