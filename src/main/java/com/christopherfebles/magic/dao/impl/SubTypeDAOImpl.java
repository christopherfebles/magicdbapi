package com.christopherfebles.magic.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.christopherfebles.magic.dao.SubTypeDAO;
import com.christopherfebles.magic.enums.*;

import static com.christopherfebles.magic.dao.MagicDAOConstants.*;

@Repository
public class SubTypeDAOImpl implements SubTypeDAO {

    private static final Logger LOG = LoggerFactory.getLogger( SubTypeDAOImpl.class );

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource( DataSource dataSource ) {
        jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
    }

    @Override
    public List<SubType> getAllSubTypes() {

        String query = "Select distinct type_name from " + CARD_TYPE_TABLE + " order by type_name ";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        SqlParameterSource paramSource = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );

        List<CardType> nonSubTypes = new ArrayList<>();
        nonSubTypes.addAll( Arrays.asList( SuperType.values() ) );
        nonSubTypes.addAll( Arrays.asList( Type.values() ) );

        List<SubType> subTypesList = new LinkedList<>();
        while ( rowSet.next() ) {
            String type = rowSet.getString( "type_name" );
            SubType subType = new SubType( type );

            // Include only SubTypes
            if ( !nonSubTypes.contains( subType ) ) {
                subTypesList.add( subType );
            }
        }

        LOG.trace( "{} subtypes loaded: {}", subTypesList.size(), subTypesList.toString() );

        return subTypesList;
    }

    @Override
    public Map<SubType, Integer> getSubTypesAndFrequency() {

        Map<SubType, Integer> subTypeMap = new LinkedHashMap<>();

        String query = "Select type_name, count(type_name) as typeCount From " + CARD_TYPE_TABLE + " group by type_name order by typeCount desc ";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        List<CardType> nonSubTypes = new ArrayList<>();
        nonSubTypes.addAll( Arrays.asList( SuperType.values() ) );
        nonSubTypes.addAll( Arrays.asList( Type.values() ) );

        SqlParameterSource paramSource = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );
        while ( rowSet.next() ) {
            String typeStr = rowSet.getString( "type_name" );
            SubType subType = new SubType( typeStr );
            int typeCount = rowSet.getInt( "typeCount" );

            // Include only SubTypes
            if ( !nonSubTypes.contains( subType ) ) {
                subTypeMap.put( subType, typeCount );
            }
        }

        LOG.trace( "{} subtypes loaded: {}", subTypeMap.keySet().size(), subTypeMap.toString() );

        return subTypeMap;
    }
}
