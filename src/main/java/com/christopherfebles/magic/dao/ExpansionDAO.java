package com.christopherfebles.magic.dao;

import java.util.List;

/**
 * Magic Expansion-specific data access.
 * 
 * @author Christopher Febles
 *
 */
public interface ExpansionDAO {

    /**
     * Load all the Magic expansions present in the database, sorted alphabetically
     * 
     * @return A list of expansion names
     */
    List<String> getAllExpansions();

}
