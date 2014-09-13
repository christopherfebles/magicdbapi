package com.christopherfebles.magic.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.christopherfebles.magic.enums.CardType;
import com.christopherfebles.magic.enums.Color;
import com.christopherfebles.magic.enums.Language;
import com.christopherfebles.magic.enums.SubType;
import com.christopherfebles.magic.enums.SuperType;
import com.christopherfebles.magic.enums.Type;

/**
 * Represents a complete Magic Card in memory.
 * 
 * @author Christopher Febles
 *
 */
public class MagicCard implements Comparable<MagicCard> {

    private Integer multiverseId;
    private String name;

    private List<Color> colors;
    private List<Mana> manaCost;
    private Integer convertedCost;

    private List<SuperType> superTypes;
    private List<Type> types;
    private List<SubType> subTypes;
    private String type;

    private String text;
    private String flavorText;

    private String power;
    private String toughness;

    private String expansion;
    private String rarity;
    private String artist;

    private String number;
    private String watermark;
    private Language language;

    // Database values
    private String colorString;
    private String manaCostString;
    private byte[] cardImageArray;

    public MagicCard() {
        this.colors = new ArrayList<>();
        this.manaCost = new ArrayList<>();
        this.superTypes = new ArrayList<>();
        this.types = new ArrayList<>();
        this.subTypes = new ArrayList<>();

        // Card defaults
        colorString = "";
        manaCostString = "";
        this.setManaCostWithString( "0" );
    }

    /**
     * Construct this object with all non-nullable fields set.<br>
     * <br>
     * Color of this card will be autocalculated based on the Mana cost
     * 
     * @param multiverseId
     *            The ID of this card, to pass to {@link #setMultiverseId(Integer)}
     * @param name
     *            The name of this card, to pass to {@link #setName(String)}
     * @param manaCost
     *            The cost of this card, to pass to {@link #setManaCostWithString(String)}
     * @param types
     *            The types of this card, to pass to {@link #setTypes(String)}
     * @param expansion
     *            The expansions this card appeared in, to pass to {@link #setExpansion(String)}
     */
    public MagicCard( Integer multiverseId, String name, String manaCost, String types, String expansion ) {
        this();
        this.setMultiverseId( multiverseId );
        this.setName( name );
        this.setManaCostWithString( manaCost );
        this.setTypes( types );
        this.setExpansion( expansion );
    }

    /**
     * Reset all the fields of this card to the values in the given card
     * 
     * @param card
     *            The new MagicCard that will be represented by this object
     */
    protected void resetMagicCardValues( MagicCard card ) {

        this.setMultiverseId( card.multiverseId );
        this.setName( card.name );

        this.setColors( card.colors );
        this.setManaCost( card.manaCost );

        this.setSuperTypes( card.superTypes );
        this.setTypes( card.types );
        this.setSubTypes( card.subTypes );

        this.setText( card.text );
        this.setFlavorText( card.flavorText );

        this.setPower( card.power );
        this.setToughness( card.toughness );

        this.setExpansion( card.expansion );
        this.setRarity( card.rarity );
        this.setArtist( card.artist );

        this.setNumber( card.number );
        this.setWatermark( card.watermark );
        this.setLanguage( card.language );

        this.setCardImageArray( card.cardImageArray );
    }

    @Override
    public boolean equals( Object o ) {
        if ( !( o instanceof MagicCard ) ) {
            return false;
        }
        MagicCard other = ( MagicCard ) o;

        return other != null && this.getMultiverseId().equals( other.getMultiverseId() );
    }

    @Override
    public int hashCode() {
        return this.getMultiverseId().hashCode();
    }

    @Override
    public int compareTo( MagicCard o ) {
        int retVal = this.getName().toLowerCase().compareTo( o.getName().toLowerCase() );

        if ( retVal == 0 ) {
            // Secondarily order by Id
            retVal = this.getMultiverseId().compareTo( o.getMultiverseId() );
        }

        return retVal;
    }

    /**
     * Set the mana cost (and mana cost string) of this card with a given String representation (Ex: 2R, 0, 2{R/G} ) of the cost.<br>
     * <br>
     * This method handles semicolon delimited values, hybrid mana, colorless mana, and Phyrexian mana.<br>
     * This method is complex. Check out the unit tests for some valid parameter values.<br>
     * <br>
     * As a side effect of this method being called, the Color of this card will be recalculated based on the new Mana Cost. <br>
     * This method is complex. See MagicCardTest for unit test coverage.
     *
     * @see #setColors()
     * @param cost
     *            A string representation of the colors of mana that this card costs. May be semicolon delimited or not.
     */
    public void setManaCostWithString( String cost ) {
        
        List<Mana> manaCostList = new ArrayList<>();
        String[] costAr = this.parseManaStringToArray( cost );

        // Go through each mana string and create Mana objects
        for ( String oneMana : costAr ) {
            if ( oneMana.length() != 1 && !oneMana.contains( "/" ) && !oneMana.contains( "P" ) ) {
                for ( String oneManaLength : oneMana.split( "" ) ) {
                    if ( StringUtils.isNotEmpty( oneManaLength ) ) {
                        manaCostList.add( Mana.getManaFromString( oneManaLength ) );
                    }
                }
            } else {
                manaCostList.add( Mana.getManaFromString( oneMana ) );
            }
        }

        this.setManaCost( manaCostList );
        this.manaCostString = "";
        for ( Mana mana : this.getManaCost() ) {
            this.manaCostString += mana.toString();
        }
        this.setColors();
    }
    
    /**
     * Extract the given String into an array, where each element position represents a single Mana.
     * 
     * @see #setManaCostWithString(String)
     * 
     * @param cost      The string to parse
     * @return          An array where each position represents a single Mana
     */
    private String[] parseManaStringToArray( String cost ) {

        String[] costAr = null;
        String localCost = cost;

        // Parse Mana String
        if ( localCost.contains( ";" ) ) {
            costAr = localCost.split( ";" );
        } else if ( localCost.contains( "{" ) ) {
            // See: http://stackoverflow.com/questions/16907843/java-split-regex-split-string-using-any-text-between-curly-brackets-and-keep-th
            // It's easier to change the string to match the regex than to change the regex
            localCost = localCost.replace( "{", " {" );
            localCost = localCost.replace( "}", "} " );
            costAr = localCost.split( "\\s(?=\\{)|(?<=\\})\\s" );
        } else if ( localCost.contains( "P" ) ) {
            // Split Phyrexian mana when it's not separated by a delimiter.
            List<String> manaList = new ArrayList<>();
            String[] characterArray = localCost.split( "" );
            for ( int x = 0; x < characterArray.length; x++ ) {
                String character = characterArray[x];
                // If the character is numeric, it's colorless, if not, it may be Phyrexian
                if ( StringUtils.isNumeric( character ) ) {
                    manaList.add( character );
                } else {
                    // Check if Phyrexian
                    String nextCharacter = characterArray[x + 1];
                    if ( nextCharacter.equals( "P" ) ) {
                        character += nextCharacter;
                        x++;
                    }
                    // else if not Phyrexian, add mana to list
                    manaList.add( character );
                }
            }
            costAr = manaList.toArray( new String[manaList.size()] );
        } else {
            costAr = localCost.split( "" );
        }

        costAr = ArrayUtils.removeElement( costAr, "" );
        
        return costAr;
    }

    /**
     * Set the color of this card based on the current Mana cost
     */
    protected void setColors() {
        List<Mana> manaCostList = this.getManaCost();
        List<Color> finalColor = new ArrayList<>();

        if ( CollectionUtils.isEmpty( manaCostList ) ) {
            // Set to default
            finalColor.add( Color.COLORLESS );
            this.colorString = Color.COLORLESS.getValue();
            return;
        }

        // Clear duplicates
        for ( Mana mana : manaCostList ) {
            if ( CollectionUtils.isEmpty( mana.getColors() ) ) {
                finalColor.add( Color.COLORLESS );
            } else {
                for ( Color color : mana.getColors() ) {
                    if ( !finalColor.contains( color ) ) {
                        finalColor.add( color );
                    }
                }
            }
        }
        
        // DO NOT Check card text for colored activated abilities
        // Activated abilities do not affect the card's color

        // Remove colorless
        finalColor.remove( Color.COLORLESS );
        finalColor.remove( Color.VARIABLE_COLORLESS );
        if ( finalColor.isEmpty() ) {
            finalColor.add( Color.COLORLESS );
        }

        this.setColors( finalColor );
        this.colorString = "";
        for ( Color color : this.getColors() ) {
            if ( !this.colorString.contains( color.getValue() ) ) {
                this.colorString += color.getValue();
            }
        }
    }

    /**
     * Generate a full type String from the various Type objects
     * 
     * @return A string containing card types: "Creature — Human Nomad"
     */
    public String getType() {

        this.type = "";
        for ( SuperType type : this.getSuperTypes() ) {
            this.type += type.toString() + " ";
        }
        for ( Type type : this.getTypes() ) {
            this.type += type.toString() + " ";
        }

        for ( SubType type : this.getSubTypes() ) {

            if ( StringUtils.isNotEmpty( this.type ) && !this.type.contains( CardType.TYPE_SEPARATOR ) ) {
                this.type += CardType.TYPE_SEPARATOR_WITH_SPACES;
            }

            this.type += type.toString() + " ";
        }
        // This must be replaceAll because \s is a regex
        this.type = this.type.trim().replaceAll( "\\s+", " " );

        return this.type;
    }

    /**
     * Set all the type lists with a string containing all types of this card
     * 
     * @param allTypes
     *            A string containing card types: "Creature — Human Nomad"
     */
    public void setTypes( String allTypes ) {

        Validate.isTrue( StringUtils.isNotBlank( allTypes ), "Types to process cannot be null or empty." );

        List<SuperType> superTypeList = new ArrayList<>();
        List<Type> typeList = new ArrayList<>();
        List<SubType> subTypeList = new ArrayList<>();

        String[] typesAr = allTypes.toUpperCase().split( " " );

        for ( String type : typesAr ) {
            if ( SuperType.contains( type ) ) {
                superTypeList.add( SuperType.valueOf( type ) );
            } else if ( Type.contains( type ) ) {
                typeList.add( Type.valueOf( type ) );
            } else if ( !type.equals( CardType.TYPE_SEPARATOR ) ) {
              // type must be a SubType
              String subType = StringUtils.capitalize( type.toLowerCase() );
              subTypeList.add( new SubType( subType ) );
            }
        }

        this.setSuperTypes( superTypeList );
        this.setSubTypes( subTypeList );
        this.setTypes( typeList );
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString( this, ToStringStyle.MULTI_LINE_STYLE );
    }

    public byte[] getCardImageArray() {
        return cardImageArray;
    }

    public void setCardImageArray( byte[] cardImageArray ) {
        this.cardImageArray = cardImageArray;
    }

    public void addColor( Color newColor ) {
        this.colors.add( newColor );
        this.colorString += newColor.getValue();
    }

    public void addManaCost( Mana newMana ) {
        this.manaCost.add( newMana );
        this.manaCostString += newMana.toString();
    }

    public void addSuperType( SuperType newType ) {
        this.superTypes.add( newType );
    }

    public void addType( Type newType ) {
        this.types.add( newType );
    }

    public void addSubType( SubType newType ) {
        this.subTypes.add( newType );
    }

    public Integer getMultiverseId() {
        return multiverseId;
    }

    public void setMultiverseId( Integer multiverseId ) {
        this.multiverseId = multiverseId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Color> getColors() {
        return colors;
    }

    public String getColorsString() {
        return colorString;
    }

    protected void setColors( List<Color> colors ) {
        this.colors = colors;
    }

    public List<Mana> getManaCost() {
        return manaCost;
    }

    public String getManaCostString() {
        return manaCostString;
    }

    protected void setManaCost( List<Mana> cost ) {
        this.manaCost = cost;
    }

    public Integer getConvertedCost() {
        if ( this.getManaCost() != null ) {
            convertedCost = 0;
            for ( Mana mana : this.getManaCost() ) {
                convertedCost += mana.getCostValue();
            }
        }
        return convertedCost;
    }

    public List<SuperType> getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes( List<SuperType> superTypes ) {
        this.superTypes = superTypes;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes( List<Type> types ) {
        this.types = types;
    }

    public List<SubType> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes( List<SubType> subTypes ) {
        this.subTypes = subTypes;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText( String flavorText ) {
        this.flavorText = flavorText;
    }

    /**
     * For Creatures, Power = Power For Planeswalkers, Power = Loyalty
     */
    public String getPower() {
        return power;
    }

    /**
     * Loyalty is only available for Planeswalker cards
     */
    public String getLoyalty() {
        return this.power;
    }

    public void setPower( String power ) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness( String toughness ) {
        this.toughness = toughness;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion( String expansion ) {
        this.expansion = expansion;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity( String rarity ) {
        this.rarity = rarity;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist( String artist ) {
        this.artist = artist;
    }

    /**
     * The "Card Number" for this Magic Card<br>
     * <br>
     * In the case of cards with multiple versions, this may be suffixed with a non-numeric character
     * 
     * @return The Card Number, as reported by Gatherer, or null if none exists
     */
    public String getNumber() {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark( String watermark ) {
        this.watermark = watermark;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage( Language language ) {
        this.language = language;
    }

}
