package com.christopherfebles.magic.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.christopherfebles.magic.enums.CardType;
import com.christopherfebles.magic.enums.Color;
import com.christopherfebles.magic.enums.SuperType;
import com.christopherfebles.magic.enums.Type;
import com.christopherfebles.magic.model.MagicCard;
import com.christopherfebles.magic.model.Mana;
import com.christopherfebles.magic.testsupport.UnitTest;

@Category( UnitTest.class )
public class MagicCardTest {
    
    @Test
    public void testSetTypes() {
        String typeStr = ( SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island" );
        MagicCard card = new MagicCard();
        
        card.setTypes( typeStr );
        
        assertEquals( typeStr, card.getType() );
    }

    @Test
    public void testSetManaCostWithStringNormal() {
        
//        2R, 0, 2{R/G}, 3RPRP
        String manaCost = "2R";
        
        MagicCard card = new MagicCard();
        card.setManaCostWithString( manaCost );
        
        assertEquals( Color.RED.getValue(), card.getColorsString() );
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 3, card.getConvertedCost().intValue() );
    }

    @Test
    public void testSetManaCostWithStringWithSingleValue() {
        
//        2R, 0, 2{R/G}, 3RPRP
        String manaCost = "0";
        
        MagicCard card = new MagicCard();
        card.setManaCostWithString( manaCost );
        
        assertEquals( Color.COLORLESS.getValue(), card.getColorsString() );
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 0, card.getConvertedCost().intValue() );
    }
    
    @Test
    public void testManaCostWithHybridMana() {
        
        MagicCard card = new MagicCard();
        String manaCost = "{R/G}";
        
        card.setManaCostWithString( manaCost );
        
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 1, card.getManaCost().size() );
        assertEquals( 1, card.getConvertedCost().intValue() );
    }

    @Test
    public void testSetManaCostWithStringWithSplitValue() {
        
//        2R, 0, 2{R/G}, 3RPRP
        String manaCost = "2{R/G}";
        
        MagicCard card = new MagicCard();
        card.setManaCostWithString( manaCost );
        
        assertEquals( "RG", card.getColorsString() );
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 3, card.getConvertedCost().intValue() );
    }

    @Test
    public void testSetManaCostWithStringWithMultipleNormalColors() {
        
//        2R, 0, 2{R/G}, 3RPRP
        String manaCost = "2RRGGUUBB";
        
        MagicCard card = new MagicCard();
        card.setManaCostWithString( manaCost );
        
        assertEquals( "RGUB", card.getColorsString() );
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 10, card.getConvertedCost().intValue() );
    }

    @Test
    public void testSetManaCostWithStringWithPhyrexianMana() {
        
//        2R, 0, 2{R/G}, 3RPRP
        String manaCost = "3RPRP";
        
        MagicCard card = new MagicCard();
        card.setManaCostWithString( manaCost );
        
        assertEquals( "R", card.getColorsString() );
        assertEquals( manaCost, card.getManaCostString() );
        assertEquals( 5, card.getConvertedCost().intValue() );
        
        //Check if all mana is correct
        for( Mana mana : card.getManaCost() ) {
            assertTrue( mana.toString().equals( "3" ) ||
                    mana.toString().equals( "RP" ));
        }
    }
}
