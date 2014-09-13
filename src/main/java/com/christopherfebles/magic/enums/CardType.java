package com.christopherfebles.magic.enums;

/**
 * Allows related Magic Card types to be (Java)type-safe comparable via the equals() method.
 * 
 * @author Christopher Febles
 *
 */
public interface CardType extends DatabaseSearchable {

    static final String TYPE_SEPARATOR = "—";
    static final String TYPE_SEPARATOR_WITH_SPACES = " — ";

    /**
     * All card types can be compared to each other for equality
     * 
     * @param otherCard
     *            The other card to compare this card to.
     * @return True if the toString() of each card is equal
     */
    boolean equals( CardType otherCard );
    
}
