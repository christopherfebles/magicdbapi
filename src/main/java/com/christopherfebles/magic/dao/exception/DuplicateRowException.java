package com.christopherfebles.magic.dao.exception;

/**
 * Thrown when duplicate rows are unexpectedly returned from the database.
 * 
 * @author Christopher Febles
 *
 */
public class DuplicateRowException extends RuntimeException {

    /**
     * Generated Serial ID
     */
    private static final long serialVersionUID = 7161726690529826355L;

    /**
     * Default constructor
     * 
     * @param message   The exception's detail message.
     */
    public DuplicateRowException( String message ) {
        super( message );
    }
    
}
