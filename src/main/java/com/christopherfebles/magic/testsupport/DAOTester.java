package com.christopherfebles.magic.testsupport;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.christopherfebles.magic.dao.MagicCardDAO;
import com.christopherfebles.magic.enums.CardType;
import com.christopherfebles.magic.enums.Language;
import com.christopherfebles.magic.enums.SuperType;
import com.christopherfebles.magic.enums.Type;
import com.christopherfebles.magic.model.MagicCard;
import org.springframework.stereotype.Component;

/**
 * Shared Before/After methods for setting up the database before DAO tests.<br>
 * <br>
 * Instead of using MagicCardDAO for these, I suppose I could use direct SQL, but I guess this gives MagicCardDAO more test coverage. And I'm too lazy to change
 * it.<br>
 * <br>
 * This class requires JUnit in the compile scope.
 * 
 * @author Christopher Febles
 *
 */
@Component
public class DAOTester {

    protected static final int VALID_MULTIVERSE_ID = 600;
    protected static final int INVALID_MULTIVERSE_ID = 0;
    protected static final int TEMPORARY_MULTIVERSE_ID = Integer.MAX_VALUE;
    protected static final int OWNED_CARD_MULTIVERSE_ID = 373723;

    protected static boolean daoTesterInitializationComplete = false;

    @Autowired
    protected MagicCardDAO cardDAO;

    /**
     * If database is empty, insert values for unit tests to pass
     * 
     * If database is NOT empty, this test depends on values stored in the database (integration test)
     */
    @Before
    public void setUp() {
        if ( !daoTesterInitializationComplete ) {
            // Insert valid multiverse cards
            // VALID_MULTIVERSE_ID and OWNED_CARD_MULTIVERSE_ID
            MagicCard blackLotus = new MagicCard( VALID_MULTIVERSE_ID, "Black Lotus", "0", Type.ARTIFACT.toString(), "Vintage Masters" );
            blackLotus.setLanguage( Language.ENGLISH );
            MagicCard island = new MagicCard( OWNED_CARD_MULTIVERSE_ID, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString()
                    + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Theros" );
            island.setLanguage( Language.ENGLISH );

            // Insert into database
            assertTrue( cardDAO.addCardToDatabase( blackLotus ) );
            assertTrue( cardDAO.addCardToDatabase( island ) );

            // Add to owned list
            assertTrue( cardDAO.incrementOwnedCard( OWNED_CARD_MULTIVERSE_ID ) );

            daoTesterInitializationComplete = true;
        }
        additionalSetUp();
    }

    @After
    public void cleanUp() {
        additionalCleanUp();
    }

    /**
     * Method for subclasses to override when they need more than the basic setup above
     */
    protected void additionalSetUp() {
        //This method is called before every unit test
    }

    /**
     * Method for subclasses to override when they need more than the basic cleanup above
     */
    protected void additionalCleanUp() {
        //This method is called after every unit test
    }

    /*********** Convenience methods for subclasses to add test cards to the embedded database ***********/

    /**
     * Add all fireball cards to the database
     */
    protected void addFireballs() {
        MagicCard fireball;
        fireball = new MagicCard( 197, "Fireball", "XR", Type.SORCERY.toString(), "Limited Edition Alpha" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 492, "Fireball", "XR", Type.SORCERY.toString(), "Limited Edition Beta" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 794, "Fireball", "XR", Type.SORCERY.toString(), "Unlimited Edition" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 1291, "Fireball", "XR", Type.SORCERY.toString(), "Revised Edition" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 2275, "Fireball", "XR", Type.SORCERY.toString(), "Fourth Edition" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 4048, "Fireball", "XR", Type.SORCERY.toString(), "Fifth Edition" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 26621, "Fireball", "XR", Type.SORCERY.toString(), "Beatdown Box Set" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 46768, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 75529, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 75694, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 75859, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 76024, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 76189, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 76354, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 76519, "Fireball", "XR", Type.SORCERY.toString(), "Darksteel" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 189231, "Fireball", "XR", Type.SORCERY.toString(), "Duel Decks: Jace vs. Chandra" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 191076, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 199193, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 199442, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 199691, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 199940, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 200189, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 200438, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 200687, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 200936, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2010" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 205223, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 220478, "Fireball", "XR", Type.SORCERY.toString(), "Archenemy" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 221511, "Fireball", "XR", Type.SORCERY.toString(), "Masters Edition IV" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 221782, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 222352, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 224901, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 225150, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 225399, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 225648, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 225897, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 226146, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 226395, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2011" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 227456, "Fireball", "XR", Type.SORCERY.toString(), "Duel Decks: Jace vs. Chandra" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 234703, "Fireball", "XR", Type.SORCERY.toString(), "Premium Deck Series: Fire and Lightning" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 263008, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 263257, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 263506, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 263755, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 264004, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 264253, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 264502, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 264751, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 265000, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 376334, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 376690, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 377046, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 377402, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 377758, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
        fireball = new MagicCard( 378114, "Fireball", "XR", Type.SORCERY.toString(), "Commander 2013 Edition" );
        fireball.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( fireball ) );
    }

    /**
     * Add some Planeswalkers (all named Chandra Nalaar) to the database
     */
    protected void addPlaneswalkersNamedChandra() {

        MagicCard planeswalker;
        planeswalker = new MagicCard( 140176, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 154484, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 154785, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 155096, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 155397, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 155698, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 155999, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 156300, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 156601, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 156902, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Lorwyn" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 185815, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra",
                "Duel Decks: Jace vs. Chandra" );
        planeswalker.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 191242, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 199189, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 199438, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 199687, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 199936, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 200185, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 200434, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 200683, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 200932, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2010" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 205958, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 221800, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 222370, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 224919, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 225168, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 225417, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 225666, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 225915, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 226164, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 226413, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra", "Magic 2011" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
        planeswalker = new MagicCard( 227445, "Chandra Nalaar", "3RR", Type.PLANESWALKER + Type.TYPE_SEPARATOR_WITH_SPACES + "Chandra",
                "Duel Decks: Jace vs. Chandra" );
        planeswalker.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( planeswalker ) );
    }

    /**
     * Add 10 randomly selected white cards to the database with all unique names
     */
    protected void addWhiteCards() {

        MagicCard whiteCard;
        whiteCard = new MagicCard( 212691, "Admonition Angel", "3WWW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Angel", "Worldwake" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 832, "Benalish Hero", "W", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Soldier", "Unlimited Edition" );
        whiteCard.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 167464, "Plated Sliver", "W", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Sliver", "Legions" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 231547, "Dispense Justice", "2W", Type.INSTANT.toString(), "Scars of Mirrodin" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 337030, "Gather the Townsfolk", "1W", Type.SORCERY.toString(), "Dark Ascension" );
        whiteCard.setLanguage( Language.GERMAN );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 133282, "Jedit's Dragoons", "5W", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Cat Soldier", "Time Spiral" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 367305, "Angelic Skirmisher", "4WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Angel", "Gatecrash" );
        whiteCard.setLanguage( Language.KOREAN );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 225621, "Honor of the Pure", "1W", Type.ENCHANTMENT.toString(), "Magic 2011" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 127991, "White Shield Crusader", "WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Coldsnap" );
        whiteCard.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
        whiteCard = new MagicCard( 29909, "Devoted Caretaker", "W", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Cleric", "Odyssey" );
        whiteCard.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( whiteCard ) );
    }

    /**
     * Add 20 randomly selected Paladins to the database with 12 unique names
     */
    protected void addPaladins() {
        MagicCard paladin;
        paladin = new MagicCard( 372031, "Fiendslayer Paladin", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Magic 2014 Core Set" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 370786, "Fiendslayer Paladin", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Magic 2014 Core Set" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 294921, "Silverblade Paladin", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Avacyn Restored" );
        paladin.setLanguage( Language.KOREAN );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 184084, "Northern Paladin", "2WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Seventh Edition" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 98287, "Paladin en-Vec", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Ninth Edition" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 366852, "Truefire Paladin", "RW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Gatecrash" );
        paladin.setLanguage( Language.GERMAN );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 132730, "Pentarch Paladin", "2WWW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Time Spiral" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 373371, "Dawnstrike Paladin", "3WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight",
                "Duel Decks: Heroes vs. Monsters" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 243848, "Paladin of Prahv", "4WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight",
                "Duel Decks: Knights vs. Dragons" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 2352, "Northern Paladin", "2WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Fourth Edition" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 371035, "Fiendslayer Paladin", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Magic 2014 Core Set" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 149004, "Paladin en-Vec", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Tenth Edition" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 99727, "Paladin en-Vec", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Ninth Edition" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 372215, "Dawnstrike Paladin", "3WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Magic 2014 Core Set" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 213818, "Accorder Paladin", "1W", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Mirrodin Besieged" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 193763, "Lightwielder Paladin", "3WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Magic 2010" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 134536, "Pentarch Paladin", "2WWW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Time Spiral" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 100087, "Paladin en-Vec", "1WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Ninth Edition" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 3030, "Serra Paladin", "2WW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Homelands" );
        paladin.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
        paladin = new MagicCard( 196748, "Knotvine Paladin", "GW", Type.CREATURE + Type.TYPE_SEPARATOR_WITH_SPACES + "Human Knight", "Alara Reborn" );
        paladin.setLanguage( Language.UNKNOWN_NON_ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( paladin ) );
    }

    /**
     * Adds all Island cards to the embedded database except the one defined in {@link #setUp()}, above.
     */
    protected void addAllIslands() {

        MagicCard island;
        island = new MagicCard( 292, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Limited Edition Alpha" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 293, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Limited Edition Alpha" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 592, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Limited Edition Beta" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 593, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Limited Edition Beta" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 594, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Limited Edition Beta" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 894, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Unlimited Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 895, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Unlimited Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 896, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Unlimited Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 1392, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Revised Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 1393, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Revised Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 1394, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Revised Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2389, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fourth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2390, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fourth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2391, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fourth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2767, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ice Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2768, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ice Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 2769, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ice Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 3581, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirage" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 3582, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirage" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 3583, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirage" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 3584, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirage" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4199, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fifth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4200, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fifth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4201, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fifth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4202, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Fifth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4421, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4422, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4423, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4424, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4949, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Tempest" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4950, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Tempest" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4951, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Tempest" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 4952, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Tempest" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8326, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Urza's Saga" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8327, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Urza's Saga" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8328, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Urza's Saga" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8329, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Urza's Saga" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8387, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Second Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8390, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Second Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 8391, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Second Age" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 9677, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Unglued" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 10551, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Three Kingdoms" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 10641, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Three Kingdoms" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 10642, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Portal Three Kingdoms" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 11348, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Seventh Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 11349, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Seventh Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 11350, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Seventh Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 11351, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Seventh Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 14737, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Classic Sixth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 14738, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Classic Sixth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 14739, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Classic Sixth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 14740, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Classic Sixth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 20884, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mercadian Masques" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 20885, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mercadian Masques" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 20886, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mercadian Masques" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 20887, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mercadian Masques" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 21144, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Battle Royale Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 21793, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 1999" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 21794, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 1999" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 21795, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 1999" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 21800, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 1999" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 22364, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Battle Royale Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 22365, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Battle Royale Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 22366, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Battle Royale Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 22367, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Battle Royale Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 25451, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 2000" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 25452, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Starter 2000" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 25964, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Invasion" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 26301, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Invasion" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 26302, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Invasion" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 26303, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Invasion" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 27236, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Beatdown Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 27237, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Beatdown Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 27238, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Beatdown Box Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 31613, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Odyssey" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 31614, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Odyssey" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 31615, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Odyssey" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 31616, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Odyssey" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 40117, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Onslaught" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 40118, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Onslaught" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 40119, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Onslaught" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 40120, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Onslaught" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 46441, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Eighth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 46442, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Eighth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 46443, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Eighth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 46444, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Eighth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 48410, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 48411, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 48412, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 48413, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 73951, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Unhinged" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 79061, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Champions of Kamigawa" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 79069, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Champions of Kamigawa" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 79071, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Champions of Kamigawa" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 79074, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Champions of Kamigawa" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 83136, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ninth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 83137, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ninth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 83138, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ninth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 83139, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ninth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 95100, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ravnica: City of Guilds" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 95103, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ravnica: City of Guilds" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 95107, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ravnica: City of Guilds" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 95113, "Island", "0", SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Ravnica: City of Guilds" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 118905, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Time Spiral" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 122077, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Time Spiral" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 122089, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Time Spiral" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 122095, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Time Spiral" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 129606, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Tenth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 129607, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Tenth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 129608, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Tenth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 129609, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Tenth Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 143619, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Lorwyn" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 143624, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Lorwyn" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 143628, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Lorwyn" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 143632, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Lorwyn" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 157874, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shadowmoor" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 157875, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shadowmoor" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 157883, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shadowmoor" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 158237, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shadowmoor" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 159281, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 159282, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 159283, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 174977, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shards of Alara" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 174978, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shards of Alara" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 174979, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shards of Alara" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 174980, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Shards of Alara" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 190583, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Chandra" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 190588, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Chandra" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 190589, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Chandra" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 190590, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Chandra" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 191387, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2010" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 191389, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2010" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 191390, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2010" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 191400, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2010" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 195161, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 195165, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 195170, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 195187, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 201963, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 201964, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 201965, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 201966, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Zendikar" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205286, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205287, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205288, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205289, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205932, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Rise of the Eldrazi" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205939, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Rise of the Eldrazi" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205944, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Rise of the Eldrazi" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 205945, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Rise of the Eldrazi" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 206072, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition III" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 206073, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition III" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 206074, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Masters Edition III" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 207935, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Premium Deck Series: Slivers" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 210511, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island",
                "Duel Decks: Phyrexia vs. the Coalition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 213616, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2011" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 213617, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2011" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 213618, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2011" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 213619, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2011" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 214666, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Scars of Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 214672, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Scars of Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 214678, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Scars of Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 214682, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Scars of Mirrodin" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 220365, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Mirrodin Besieged" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 220369, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Mirrodin Besieged" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 221300, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Archenemy" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 221301, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Archenemy" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 221302, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Archenemy" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 222724, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Elspeth vs. Tezzeret" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 222725, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Elspeth vs. Tezzeret" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 222726, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Elspeth vs. Tezzeret" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 222727, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Elspeth vs. Tezzeret" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 227505, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "New Phyrexia" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 227530, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "New Phyrexia" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 244323, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 244324, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 244325, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 244326, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 245235, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Innistrad" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 245236, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Innistrad" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 245237, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Innistrad" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249723, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249724, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249725, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249726, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249804, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic: The Gathering-Commander" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249805, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic: The Gathering-Commander" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249806, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic: The Gathering-Commander" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 249807, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic: The Gathering-Commander" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 259287, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Ajani vs. Nicol Bolas" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263111, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263112, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263113, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263114, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263360, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263361, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263362, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263363, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263609, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263610, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263611, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263612, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263858, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263859, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263860, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 263861, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264107, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264108, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264109, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264110, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264356, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264357, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264358, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264359, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264605, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264606, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264607, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264608, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264854, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264855, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264856, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 264857, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 265103, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 265104, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 265105, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 265106, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2012" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 269625, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 269633, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 269639, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 276452, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase 2012 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 276453, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase 2012 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 276462, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase 2012 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 276466, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase 2012 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 276471, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Planechase 2012 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 284490, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Venser vs. Koth" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 284491, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Venser vs. Koth" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 284492, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Venser vs. Koth" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 289313, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 289314, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 289315, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 289316, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 292763, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Izzet vs. Golgari" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 292764, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Izzet vs. Golgari" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 292765, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Izzet vs. Golgari" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 292766, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Izzet vs. Golgari" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 295695, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 295701, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 295707, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Avacyn Restored" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330255, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330256, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330257, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330258, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330504, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330505, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330506, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330507, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330753, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330754, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330755, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 330756, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331251, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331252, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331253, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331254, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331500, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331501, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331502, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331503, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331749, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331750, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331751, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331752, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331998, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 331999, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332000, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332001, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332247, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332248, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332249, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332250, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332496, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332497, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332498, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332499, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332745, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332746, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332747, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 332748, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2013" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 333719, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355443, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355444, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355445, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355446, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355447, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355717, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355718, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355719, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355720, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355721, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355991, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355992, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355993, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355994, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 355995, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356265, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356266, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356267, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356268, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356269, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356539, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356540, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356541, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356542, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356543, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356813, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356814, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356815, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356816, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 356817, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357087, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357088, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357089, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357090, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357091, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357361, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357362, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357363, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357364, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357365, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357635, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357636, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357637, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357638, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 357639, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 365713, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 365714, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 365715, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 365716, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 365717, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Return to Ravnica" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 370608, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2014 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 370611, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2014 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 370647, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2014 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 370773, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2014 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 373558, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Theros" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 373595, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Theros" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 373736, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Theros" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 376373, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Commander 2013 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 376374, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Commander 2013 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 376375, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Commander 2013 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 376376, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Commander 2013 Edition" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 380194, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Vraska" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 380217, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Vraska" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 380222, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Vraska" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 380223, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Vraska" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 380261, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Duel Decks: Jace vs. Vraska" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 383281, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2015 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 383282, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2015 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 383283, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2015 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
        island = new MagicCard( 383284, "Island", "0",
                SuperType.BASIC.toString() + " " + Type.LAND.toString() + CardType.TYPE_SEPARATOR_WITH_SPACES + "Island", "Magic 2015 Core Set" );
        island.setLanguage( Language.ENGLISH );
        assertTrue( cardDAO.addCardToDatabase( island ) );
    }
}
