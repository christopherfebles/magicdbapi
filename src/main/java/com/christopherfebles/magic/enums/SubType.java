package com.christopherfebles.magic.enums;

import org.springframework.util.StringUtils;

/**
 * Magic Cards' types can be broken down into SuperType, Type, and SubType.<br>
 * For example, the full type "Basic Land — Island" consists of a SuperType (Basic), a Type (Land), and a SubType (Island).<br>
 * <br>
 * This class represents a single value in the SubType field, which is the text located after the {@link CardType#TYPE_SEPARATOR}. Cards may have multiple
 * SubTypes, such as "Human Assassin". In this case, two objects of SubType would be necessary.
 * 
 * @author Christopher Febles
 *
 */
public class SubType implements CardType, Comparable<SubType> {

    private String type;

    public SubType( String type ) {
        this.setType( type );
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    @Override
    public boolean equals( CardType otherCard ) {
        return this.toString().equalsIgnoreCase( otherCard.toString() );
    }

    @Override
    public boolean equals( Object other ) {
        if ( !( other instanceof CardType ) ) {
            return false;
        }
        return this.toString().equalsIgnoreCase( other.toString() );
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return StringUtils.capitalize( this.getType().toLowerCase() );
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
    public int compareTo( SubType o ) {
        return this.toString().compareTo( o.toString() );
    }

    @Override
    public String getDatabaseSearchText() {
        return this.toString();
    }

}
