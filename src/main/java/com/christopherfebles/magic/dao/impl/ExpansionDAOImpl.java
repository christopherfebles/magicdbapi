package com.christopherfebles.magic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.christopherfebles.magic.dao.ExpansionDAO;

import static com.christopherfebles.magic.dao.MagicDAOConstants.*;

@Repository
public class ExpansionDAOImpl implements ExpansionDAO {

    private static final Logger LOG = LoggerFactory.getLogger( ExpansionDAOImpl.class );

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource( DataSource dataSource ) {
        jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
    }

    @Override
    public List<String> getAllExpansions() {

        List<String> expansionList = new ArrayList<>();
        String query = "Select distinct expansion From " + ALL_CARDS_TABLE + " order by expansion ";
        LOG.trace( DATABASE_QUERY_LOG_MSG, query );

        SqlParameterSource paramSource = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet( query, paramSource );

        while ( rowSet.next() ) {
            expansionList.add( rowSet.getString( "expansion" ) );
        }

        return expansionList;
    }

}
