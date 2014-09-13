package com.christopherfebles.magic.enums;

import org.springframework.util.StringUtils;

/**
 * Defines card and Mana allowed colors.
 * 
 * @author Christopher Febles
 *
 */
public enum Color implements DatabaseSearchable {

    WHITE( "W" ),
    BLACK( "B" ),
    RED( "R" ),
    BLUE( "U" ),
    GREEN( "G" ),
    COLORLESS( "C" ),
    VARIABLE_COLORLESS( "X" );

    private final String colorValue;

    private Color( String color ) {
        this.colorValue = color;
    }

    /**
     * Gets the single character representation of this Color.
     * 
     * @return A single character representing this Color. Never null.
     */
    public String getValue() {
        return colorValue;
    }

    /**
     * Get the Color object corresponding to the given single character value.<br>
     * <br>
     * This method employs a linear search, but since the Color list is short, this shouldn't be a performance bottleneck.
     * 
     * @param colorValue
     *            The single character value representing a color, such as "W" or "B".
     * @return The associated Color object, or null if none found.
     */
    public static Color getColorByValue( String colorValue ) {

        Color retVal = null;

        for ( Color color : Color.values() ) {
            if ( color.getValue().equalsIgnoreCase( colorValue ) ) {
                retVal = color;
                break;
            }
        }

        return retVal;
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
        return "%" + this.getValue() + "%";
    }

    /**
     * Check if Color contains a given value, to avoid an IllegalArgumentException when calling {@link #valueOf(String)}
     * 
     * @param name      The name of Color Enum constant to look up
     * @return          True if the given name is present, false otherwise 
     */
    public static boolean contains( String name ) {
        for ( Color color : Color.values() ) {
            if ( color.name().equalsIgnoreCase( name ) ) {
                return true;
            }
        }
        return false;
    }
}
