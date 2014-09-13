package com.christopherfebles.magic.enums;

import org.springframework.util.StringUtils;

/**
 * Magic Cards' types can be broken down into SuperType, Type, and SubType.<br>
 * For example, the full type "Basic Land — Island" consists of a SuperType (Basic), a Type (Land), and a SubType (Island).<br>
 * <br>
 * This is an enumeration of all possible SuperTypes as of August 2014. SuperType is an optional field, and not all Magic Cards will have one.
 * 
 * @author Christopher Febles
 *
 */
public enum SuperType implements CardType {

    BASIC,
    ELITE,
    LEGENDARY,
    ONGOING,
    SNOW,
    WORLD;

    @Override
    public boolean equals( CardType otherCard ) {
        return this.toString().equalsIgnoreCase( otherCard.toString() );
    }

    @Override
    public String toString() {
        return StringUtils.capitalize( super.toString().toLowerCase() );
    }

    @Override
    public String getDatabaseSearchText() {
        return toString();
    }

    /**
     * Check if SuperType contains a given value, to avoid an IllegalArgumentException when calling {@link #valueOf(String)}
     * 
     * @param name      The name of SuperType Enum constant to look up
     * @return          True if the given name is present, false otherwise 
     */
    public static boolean contains( String name ) {
        for ( SuperType type : SuperType.values() ) {
            if ( type.name().equalsIgnoreCase( name ) ) {
                return true;
            }
        }
        return false;
    }

}
