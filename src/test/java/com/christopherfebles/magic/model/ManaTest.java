package com.christopherfebles.magic.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.christopherfebles.magic.enums.Color;
import com.christopherfebles.magic.testsupport.UnitTest;

@Category( UnitTest.class )
public class ManaTest {
    
    @Test
    public void toStringTestWithZeroCost() {
        Mana manaCost = new Mana();
        assertEquals( "0", manaCost.toString() );
    }
    
    @Test
    public void testPhyrexianMana() {
        String phyrexianRed = "RP";
        
        Mana phyrexianRedMana = Mana.getManaFromString( phyrexianRed );
        
        assertEquals( phyrexianRed, phyrexianRedMana.toString() );
    }
    
    @Test
    public void getManaFromStringTest() {
        String manaCostStr = "R";
        Mana mana = Mana.getManaFromString( manaCostStr );
        
        assertEquals( 1, mana.getColors().size() );
        assertEquals( Color.RED, mana.getColors().get( 0 ) );
    }
    
    @Test
    public void getManaFromStringOnlyColorlessTest() {
        String manaCostStr = "5";
        Mana mana = Mana.getManaFromString( manaCostStr );
        
        assertEquals( 5, mana.getColors().size() );
        assertEquals( Color.COLORLESS, mana.getColors().get( 0 ) );
    }
    
    @Test
    public void getManaFromStringWithHybridManaTest() {
        String manaCostStr = "{R/G}";
        Mana mana = Mana.getManaFromString( manaCostStr );
        
        assertEquals( 2, mana.getColors().size() );
        assertEquals( Color.RED, mana.getColors().get( 0 ) );
        assertEquals( Color.GREEN, mana.getColors().get( 1 ) );
        assertEquals( 1, mana.getCostValue().intValue() );
        
        assertEquals( manaCostStr, mana.toString() );
    }
    
    @Test
    public void getManaFromStringWithHybridIncludingColorlessManaTest() {
        String manaCostStr = "{2/W}";
        Mana mana = Mana.getManaFromString( manaCostStr );
        
        assertEquals( 3, mana.getColors().size() );//Two colorless and a white
        assertEquals( Color.COLORLESS, mana.getColors().get( 0 ) );
        assertEquals( Color.COLORLESS, mana.getColors().get( 1 ) );
        assertEquals( Color.WHITE, mana.getColors().get( 2 ) );
        assertEquals( 2, mana.getCostValue().intValue() );
        
        assertEquals( manaCostStr, mana.toString() );
    }

}
