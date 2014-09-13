package com.christopherfebles.magic.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.christopherfebles.magic.enums.Color;

/**
 * Represents a type of mana on a card. Can be more than one color.<br>
 * <br>
 * Conceptually, one Mana object represents one mana symbol on a physical card. It can be bi-color ({R/G}), mono-color (G), colorless (2), colorless/colored
 * hybrid (2/G), or other combination, so long as such combination is represented as a single mana icon on the card.
 * 
 * @author Christopher Febles
 *
 */
public class Mana {

    private List<Color> colors;
    private Integer costValue;
    private boolean isPhyrexian;

    public Mana() {
        colors = new ArrayList<>();
        this.setPhyrexian( false );
    }

    /**
     * Phyrexian mana can be paid with either the color or 2 life.
     * 
     * @return True if this Mana represents Phyrexian mana, false otherwise.
     */
    public boolean isPhyrexian() {
        return isPhyrexian;
    }

    public void setPhyrexian( boolean isPhyrexian ) {
        this.isPhyrexian = isPhyrexian;
    }

    public void addColor( Color color ) {
        this.getColors().add( color );
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors( List<Color> colors ) {
        this.colors = colors;
    }

    public Integer getCostValue() {
        return costValue;
    }

    /**
     * Calculated cost value of this Mana.
     * 
     * For all colors, it is one. For Colorless, it is the value of the number of colorless.
     * 
     * @param costValue
     *            The new costValue for this Mana
     */
    public void setCostValue( Integer costValue ) {
        this.costValue = costValue;
    }

    @Override
    public String toString() {

        if ( CollectionUtils.isEmpty( this.getColors() ) ) {
            return "0";
        }

        String retVal = "";

        int colorless = 0;
        for ( Color color : this.getColors() ) {
            if ( color.equals( Color.COLORLESS ) ) {
                colorless++;
            } else if ( colorless != 0 ) {
                retVal += colorless;
                retVal += "/";
                retVal += color.getValue();
                if ( this.isPhyrexian() ) {
                    retVal += "P";
                }
                retVal += "/";
            } else {
                retVal += color.getValue();
                if ( this.isPhyrexian() ) {
                    retVal += "P";
                }
                retVal += "/";
            }
        }

        if ( StringUtils.isEmpty( retVal ) ) {
            retVal = String.valueOf( colorless );
        } else {
            retVal = retVal.substring( 0, retVal.length() - 1 );

            if ( retVal.contains( "/" ) ) {
                retVal = "{" + retVal + "}";
            }
        }

        return retVal;
    }

    /**
     * Generate a Mana object from a String of semi-colon separated colors
     * 
     * @param manaStr
     *            The String to parse. Ex: {B/W} or B/W or B
     * @return A new mana object
     */
    public static Mana getManaFromString( String manaStr ) {

        String localManaStr = manaStr;
        Mana mana = new Mana();
        
        
        if ( localManaStr.contains( "P" ) ) {
            mana.setPhyrexian( true );
            localManaStr = localManaStr.replace( "P", "" );
        }
        localManaStr = localManaStr.trim().replace( "{", "" );
        localManaStr = localManaStr.replace( "}", "" );

        String[] colorArNoHybrids = localManaStr.split( "/" );
        int colorlessCost = 0;
        int coloredCost = 0;

        for ( String colorNoHybrid : colorArNoHybrids ) {
            String[] singleColors = colorNoHybrid.split( "" );
            for ( String singleColor : singleColors ) {
                if ( !singleColor.isEmpty() ) {

                    // If color is numeric, replace with that many Colorless
                    if ( StringUtils.isNumeric( singleColor ) ) {
                        colorlessCost = Integer.parseInt( singleColor );
                        for ( int x = 0; x < colorlessCost; x++ ) {
                            mana.addColor( Color.COLORLESS );
                        }
                    } else {
                        // non-numeric color
                        mana.addColor( Color.getColorByValue( singleColor.toUpperCase().trim() ) );
                        coloredCost = 1;
                    }
                }
            }
        }

        // If this is a hybrid mana, it may include colorless values ({2/W})
        // In this case, the cost value is the higher of the two numbers, always colorless
        if ( colorlessCost > coloredCost ) {
            mana.setCostValue( colorlessCost );
        } else {
            mana.setCostValue( coloredCost );
        }

        return mana;
    }

}
