package com.christopherfebles.magic.enums;

/**
 * Defines a shared interface for enums used to search the database.
 * 
 * @author Christopher Febles
 *
 */
public interface DatabaseSearchable {

    /**
     * Return the text of this object that should be used to search the database.<br>
     * This value may include SQL wildcards.
     * 
     * @return The text string that will match what is stored in the database.
     */
    String getDatabaseSearchText();

}
