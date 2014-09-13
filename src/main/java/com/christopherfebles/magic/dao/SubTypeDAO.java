package com.christopherfebles.magic.dao;

import java.util.List;
import java.util.Map;

import com.christopherfebles.magic.enums.SubType;

/**
 * Magic SubType-specific data access.<br>
 * <br>
 * Magic Cards' types can be broken down into SuperType, Type, and SubType.<br>
 * For example, the full type "Basic Land — Island" consists of a SuperType (Basic), a Type (Land), and a SubType (Island).<br>
 * <br>
 * This class provides access to SubType data stored in the database.
 * 
 * @author Christopher Febles
 *
 */
public interface SubTypeDAO {

    /**
     * Load all subtypes from the database
     * 
     * @return A list of subtypes, ordered alphabetically
     */
    List<SubType> getAllSubTypes();

    /**
     * Load all subTypes from the database, and count how often they appear
     * 
     * @return A list of subtypes, ordered from most common to least common
     */
    Map<SubType, Integer> getSubTypesAndFrequency();

}
