package com.christopherfebles.magic.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.christopherfebles.magic.model.MagicCard;
import com.christopherfebles.magic.testsupport.DAOTester;
import com.christopherfebles.magic.testsupport.UnitTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:/applicationContext-test.xml" } )
@Category( UnitTest.class )
public class MagicCardDAOImplTest extends DAOTester {
    
    private static final Logger LOG = LoggerFactory.getLogger( MagicCardDAOImplTest.class );
    
    @Test
    public void updateOwnedCardCountPositiveTest() {
        //Check how many owned of test card
        int numOwned = cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID );
        
        assertEquals( 1, cardDAO.updateOwnedCardCount( VALID_MULTIVERSE_ID, 5 ) );
        assertEquals( 5, cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID ) );
        
        //Reset Test
        assertEquals( 1, cardDAO.updateOwnedCardCount( VALID_MULTIVERSE_ID, numOwned ) );
        assertEquals( numOwned, cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID ) );
    }
    
    @Test
    public void updateOwnedCardCountZeroTest() {
        //Check how many owned of test card
        int numOwned = cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID );
        assertTrue( numOwned > 0 );
        
        cardDAO.updateOwnedCardCount( OWNED_CARD_MULTIVERSE_ID, 0 );
        assertEquals( 0, cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID ) );
        
        //Reset Test
        assertEquals( 1, cardDAO.updateOwnedCardCount( OWNED_CARD_MULTIVERSE_ID, numOwned ) );
        assertEquals( numOwned, cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID ) );
    }
    
    @Test
    public void updateOwnedCardCountNegativeTest() {
        //Check how many owned of test card
        int numOwned = cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID );
        assertTrue( numOwned > 0 );
        
        cardDAO.updateOwnedCardCount( OWNED_CARD_MULTIVERSE_ID, -5 );
        assertEquals( 0, cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID ) );
        
        //Reset Test
        assertEquals( 1, cardDAO.updateOwnedCardCount( OWNED_CARD_MULTIVERSE_ID, numOwned ) );
        assertEquals( numOwned, cardDAO.numberOfOwnedCard( OWNED_CARD_MULTIVERSE_ID ) );
    }
    
    @Test
    public void ownedCardAddRemoveTest() {
        
        //Check how many owned of test card
        int numOwned = cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID );
        
        //Increment number of owned cards
        assertTrue( cardDAO.incrementOwnedCard( VALID_MULTIVERSE_ID ) );
        assertEquals( (numOwned+1), cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID ) );
        
        //Decrement number of owned cards
        assertTrue( cardDAO.decrementOwnedCard( VALID_MULTIVERSE_ID ) );
        assertEquals( numOwned, cardDAO.numberOfOwnedCard( VALID_MULTIVERSE_ID ) );
    }
    
    @Test
    public void isCardOwnedWithOwnedCardTest() {
        assertTrue( cardDAO.isCardOwned( OWNED_CARD_MULTIVERSE_ID ) );
    }
    
    @Test
    public void isCardOwnedWithUnOwnedCardTest() {
        assertFalse( cardDAO.isCardOwned( INVALID_MULTIVERSE_ID ) );
    }
    
    @Test
    public void getOwnedCardsTest() {
        LOG.trace( "Calling getOwnedCards()..." );
        List<MagicCard> ownedCards = cardDAO.getOwnedCards();
        LOG.trace( "getOwnedCards() completed." );
        
        LOG.trace( "All Owned Cards: {}", ownedCards );
        
        assertNotNull( ownedCards );
    }
    
    @Test
    public void isCardInDatabaseWithValidId() {
        assertTrue( cardDAO.isCardInDatabase( VALID_MULTIVERSE_ID ) );
    }
    
    @Test
    public void isCardInDatabaseWithInvalidId() {
        assertFalse( cardDAO.isCardInDatabase( INVALID_MULTIVERSE_ID ) );
    }
    
    @Test
    public void getCardByIdTestWithValidId() {
        MagicCard card = cardDAO.getCardFromDatabaseById( VALID_MULTIVERSE_ID );
        
        assertNotNull( card );
        assertEquals( VALID_MULTIVERSE_ID, card.getMultiverseId().intValue() );
    }
    
    @Test
    public void getCardByIdTestWithInvalidId() {
        MagicCard card = cardDAO.getCardFromDatabaseById( INVALID_MULTIVERSE_ID );
        
        assertNull( card );
    }
    
    @Test
    public void numberOfCardsOwnedTest() {
        int numCards = cardDAO.numberOfCardsOwned();
        int numCardsLoaded = cardDAO.getOwnedCards().size();
        
        assertEquals( numCards, numCardsLoaded );
    }
    
    @Test
    public void saveCardInsertUnicodeTest() {
        //Load valid card
        MagicCard card = cardDAO.getCardFromDatabaseById( VALID_MULTIVERSE_ID );
        
        //Update ID to make it unique
        card.setMultiverseId( TEMPORARY_MULTIVERSE_ID );
        
        //Update value to make it testable
        card.setFlavorText( "その供犠台は何もせぬよ" );
        
        //Save card
        assertTrue( cardDAO.addCardToDatabase( card ) );
        
        //Reload card
        MagicCard newCard = cardDAO.getCardFromDatabaseById( TEMPORARY_MULTIVERSE_ID );
        
        assertEquals( TEMPORARY_MULTIVERSE_ID, newCard.getMultiverseId().intValue() );
        assertEquals( "その供犠台は何もせぬよ", newCard.getFlavorText() );
        
        //Remove new card
        assertTrue( cardDAO.removeCardFromDatabaseById( TEMPORARY_MULTIVERSE_ID ) );
    }
    
    @Test
    public void saveCardInsertTest() {
        //Load valid card
        MagicCard card = cardDAO.getCardFromDatabaseById( VALID_MULTIVERSE_ID );
        
        //Update ID to make it unique
        card.setMultiverseId( TEMPORARY_MULTIVERSE_ID );
        
        //Update value to make it testable
        card.setFlavorText( "wut" );
        
        //Save card
        assertTrue( cardDAO.addCardToDatabase( card ) );
        
        //Reload card
        MagicCard newCard = cardDAO.getCardFromDatabaseById( TEMPORARY_MULTIVERSE_ID );
        
        assertEquals( TEMPORARY_MULTIVERSE_ID, newCard.getMultiverseId().intValue() );
        assertEquals( "wut", newCard.getFlavorText() );
        
        //Remove new card
        assertTrue( cardDAO.removeCardFromDatabaseById( TEMPORARY_MULTIVERSE_ID ) );
    }
}
