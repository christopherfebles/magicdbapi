package com.christopherfebles.magic.enums;

import org.springframework.util.StringUtils;

/**
 * Magic Cards' types can be broken down into SuperType, Type, and SubType.<br>
 * For example, the full type "Basic Land — Island" consists of a SuperType (Basic), a Type (Land), and a SubType (Island).<br>
 * <br>
 * This is an enumeration of all possible Types as of August 2014. I believe Type is a required field, but Magic Cards in Unglued or Unhinged are nuts.
 * 
 * @author Christopher Febles
 *
 */
public enum Type implements CardType {

    ARTIFACT,
    CREATURE,
    ENCHANTMENT,
    HERO,
    INSTANT,
    LAND,
    PHENOMENON,
    PLANE,
    PLANESWALKER,
    SCHEME,
    SORCERY,
    TRIBAL,
    VANGUARD;

    @Override
    public boolean equals( CardType otherCard ) {
        return this.toString().equalsIgnoreCase( otherCard.toString() );
    }

    @Override
    public String toString() {
        return StringUtils.capitalize( super.toString().toLowerCase() );
    }

    /**
     * Bean formatted method name for JSP display
     * 
     * @see #toString()
     * @return The same value as the toString() method
     */
    public String getDisplayableName() {
        return this.toString();
    }

    @Override
    public String getDatabaseSearchText() {
        return this.toString();
    }

    /**
     * Check if Type contains a given value, to avoid an IllegalArgumentException when calling {@link #valueOf(String)}
     * 
     * @param name      The name of Type Enum constant to look up
     * @return          True if the given name is present, false otherwise 
     */
    public static boolean contains( String name ) {
        for ( Type type : Type.values() ) {
            if ( type.name().equalsIgnoreCase( name ) ) {
                return true;
            }
        }
        return false;
    }

}
