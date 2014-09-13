package com.christopherfebles.magic.enums;

/**
 * Defines possible languages of Magic Cards.
 * 
 * @author Christopher Febles
 *
 */
public enum Language implements DatabaseSearchable {

    CHINESE_TRADITIONAL( "Chinese Traditional" ),
    GERMAN( "German" ),
    FRENCH( "French" ),
    ITALIAN( "Italian" ),
    JAPANESE( "Japanese" ),
    KOREAN( "Korean" ),
    PORTUGUESE( "Portuguese (Brazil)" ),
    RUSSIAN( "Russian" ),
    CHINESE_SIMPLIFIED( "Chinese Simplified" ),
    SPANISH( "Spanish" ),
    ENGLISH( "English" ),
    UNKNOWN_NON_ENGLISH( "Unknown" );

    private final String language;

    private Language( String language ) {
        this.language = language;
    }

    /**
     * Gets the displayable text associated with this Language.
     * 
     * @return The displayable text associated with this Language. Never null.
     */
    public String getValue() {
        return language;
    }

    /**
     * Get the Language object corresponding to the given displayable text value.<br>
     * <br>
     * This method employs a linear search, but since the Language list is short, this shouldn't be a performance bottleneck.
     * 
     * @param languageValue
     *            The displayable text for this Language, typically loaded from Gatherer.
     * @return The associated Language object, or null if none found.
     */
    public static Language getLanguageByValue( String languageValue ) {

        Language retVal = null;

        for ( Language language : Language.values() ) {
            if ( language.getValue().equalsIgnoreCase( languageValue ) ) {
                retVal = language;
                break;
            }
        }

        return retVal;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public String getDatabaseSearchText() {
        return this.getValue();
    }

}
