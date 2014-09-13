package com.christopherfebles.magic.datasource;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * This class redirects all log messages from the parent class from JCL to SLF4J.<br>
 * <br>
 * This class is package-only access
 * 
 * @author Christopher Febles
 *
 */
abstract class SLF4JDriverManagerDataSource extends DriverManagerDataSource {
    
    private static final Logger LOG = LoggerFactory.getLogger( SLF4JDriverManagerDataSource.class );

    /**
     * Reset parent logger field to point to EmbeddedLog instance.
     */
    public SLF4JDriverManagerDataSource() {

        LOG.trace( "Reset parent logger field to point to EmbeddedLog instance." );
        try {
            Field loggerField = AbstractDataSource.class.getDeclaredField( "logger" );
            loggerField.setAccessible( true );
            loggerField.set( this, new EmbeddedLog() );
        } catch ( NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e ) {
            LOG.error( "Error reassigning final Log field.", e );
        }
        
    }
    /**
     * Redirect all JCL log messages for parent classes to correct appender.
     * 
     * @author Christopher Febles
     *
     */
    private static class EmbeddedLog implements org.apache.commons.logging.Log {

        private static final Logger LOG = LoggerFactory.getLogger( DriverManagerDataSource.class );

        @Override
        public boolean isDebugEnabled() {
            return LOG.isDebugEnabled();
        }

        @Override
        public boolean isErrorEnabled() {
            return LOG.isErrorEnabled();
        }

        @Override
        public boolean isFatalEnabled() {
            return LOG.isErrorEnabled();
        }

        @Override
        public boolean isInfoEnabled() {
            return LOG.isInfoEnabled();
        }

        @Override
        public boolean isTraceEnabled() {
            return LOG.isTraceEnabled();
        }

        @Override
        public boolean isWarnEnabled() {
            return LOG.isWarnEnabled();
        }

        @Override
        public void trace( Object message ) {
            LOG.trace( message.toString() );
        }

        @Override
        public void trace( Object message, Throwable t ) {
            LOG.trace( message.toString(), t );
        }

        @Override
        public void debug( Object message ) {
            LOG.debug( message.toString() );
        }

        @Override
        public void debug( Object message, Throwable t ) {
            LOG.debug( message.toString(), t );
        }

        @Override
        public void info( Object message ) {
            LOG.info( message.toString() );
        }

        @Override
        public void info( Object message, Throwable t ) {
            LOG.info( message.toString(), t );
        }

        @Override
        public void warn( Object message ) {
            LOG.warn( message.toString() );
        }

        @Override
        public void warn( Object message, Throwable t ) {
            LOG.warn( message.toString(), t );
        }

        @Override
        public void error( Object message ) {
            LOG.error( message.toString() );
        }

        @Override
        public void error( Object message, Throwable t ) {
            LOG.error( message.toString(), t );
        }

        @Override
        public void fatal( Object message ) {
            LOG.error( message.toString() );
        }

        @Override
        public void fatal( Object message, Throwable t ) {
            LOG.error( message.toString(), t );
        }

    }
}
