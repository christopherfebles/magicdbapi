package com.christopherfebles.magic.datasource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mysql.management.driverlaunched.ServerLauncherSocketFactory;

/**
 * Sets up and shuts down the embedded MySQL server when testing is done
 * 
 * @see <a href="http://literatitech.blogspot.com/2011/04/embedded-mysql-server-for-junit-testing.html">Embedded MySql Server for jUnit Testing With Maven</a>
 * 
 * @author Christopher Febles
 *
 */
public class EmbeddedInitializedConnectionDataSource extends InitializedConnectionDataSource {

    private static final Logger LOG = LoggerFactory.getLogger( EmbeddedInitializedConnectionDataSource.class );

    private File basedir;
    private File datadir;

    public EmbeddedInitializedConnectionDataSource() {
        super();

        // We need to set our own base/data dirs as we must
        // pass those values to the shutdown() method later

        try {
            basedir = File.createTempFile( "mysqld-base", null );
            datadir = File.createTempFile( "mysqld-data", null );
            basedir.delete();
            datadir.delete();
            basedir.mkdir();
            datadir.mkdir();
            basedir.deleteOnExit();
            datadir.deleteOnExit();
        } catch ( IOException e ) {
            LOG.error( "Error creating temp MySQL files.", e );
        }
    }

    @Override
    protected Connection getConnectionFromDriverManager( String url, Properties props ) throws SQLException {

        StringBuilder sb = new StringBuilder( url );
        sb.append( "&server.basedir=" ).append( basedir.getPath() );
        sb.append( "&server.datadir=" ).append( datadir.getPath() );
        LOG.trace( "Updating url from {} to {}", url, sb.toString() );

        return super.getConnectionFromDriverManager( sb.toString(), props );
    }

    /**
     * Shut down MySQL when done
     */
    public void close() {
        LOG.debug( "Shutting down embedded MySQL." );
        ServerLauncherSocketFactory.shutdown( basedir, datadir );
    }

}
