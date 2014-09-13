package com.christopherfebles.magic.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Whenever a new connection is created, initialize that connection by running a database script.<br>
 * <br>
 * Currently, the database script is hardcoded as SetSessionVariables.sql
 * 
 * @author Christopher Febles
 *
 */
public class InitializedConnectionDataSource extends SLF4JDriverManagerDataSource {

    private static final Logger LOG = LoggerFactory.getLogger( InitializedConnectionDataSource.class );
    
    public InitializedConnectionDataSource() {
        super();
    }

    /**
     * Intercept new Connection requests and apply connection initialization script.
     */
    @Override
    protected Connection getConnectionFromDriverManager( String url, Properties props ) throws SQLException {

        String initializationSQLFileName = "SetSessionVariables.sql";
        Connection conn = super.getConnectionFromDriverManager( url, props );

        LOG.trace( "Connection creation intercepted. Applying initialization script." );
        InputStream is = InitializedConnectionDataSource.class.getClassLoader().getResourceAsStream( initializationSQLFileName );
        String sql = null;

        try {
            sql = IOUtils.toString( is, StandardCharsets.UTF_8 );
            LOG.trace( "Loaded SQL from external file {}: \n{}\n", initializationSQLFileName, sql );
        } catch ( IOException e ) {
            LOG.error( "Error loading SQL in file {}", initializationSQLFileName, e );
        }
        
        if ( StringUtils.isNotEmpty( sql ) ) {
            //Run SQL against the database connection
            try ( Statement stmt = conn.createStatement() ) {
                stmt.execute( sql );
            }
            LOG.trace( "Initialization script run against the database successfully." );
        }
        
        return conn;
    }
    
}
