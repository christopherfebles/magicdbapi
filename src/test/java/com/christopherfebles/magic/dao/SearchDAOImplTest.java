package com.christopherfebles.magic.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.christopherfebles.magic.dao.parameter.SearchParameter;
import com.christopherfebles.magic.dao.parameter.SearchParameter.FieldName;
import com.christopherfebles.magic.enums.Color;
import com.christopherfebles.magic.enums.Language;
import com.christopherfebles.magic.enums.SubType;
import com.christopherfebles.magic.enums.Type;
import com.christopherfebles.magic.model.MagicCard;
import com.christopherfebles.magic.testsupport.DAOTester;
import com.christopherfebles.magic.testsupport.UnitTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:/applicationContext-test.xml" } )
@Category( UnitTest.class )
public class SearchDAOImplTest extends DAOTester {
    
    private static final Logger LOG = LoggerFactory.getLogger( SearchDAOImplTest.class );
    
    protected static final int OWNED_FIREBALL_ID = 221550;
    
    @Autowired
    private SearchDAO searchDAO;
    
    @Test
    public void testAllCardsLoaded() {
        //There are so many different Island cards, that group_concat overflows the default length of 1024 characters.
        //This test should ensure that all cards are loaded.
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island" ) );
        
        //Load Islands from database without group_concat
        List<MagicCard> allIslands = searchDAO.getAllWithSearchParameters( searchParams );
        
        //Extract unique set of expected expansions
        Set<String> expectedExpansions = new HashSet<>();
        for ( MagicCard island : allIslands ) {
            expectedExpansions.add( island.getExpansion() );
        }
        
        List<MagicCard> islandsByName = searchDAO.getAllByNameWithSearchParameters( searchParams );
        //Extract unique set of actual expansions
        Set<String> actualExpansions = new HashSet<>();
        for ( MagicCard island : islandsByName ) {
            actualExpansions.add( island.getExpansion() );
            assertTrue( expectedExpansions.contains( island.getExpansion() ) );
        }
        
        //Ensure no expansions are missing
        assertEquals( expectedExpansions.size(), actualExpansions.size() );
        assertEquals( expectedExpansions, actualExpansions );
    }
    
    @Test
    public void testNumResultsByNameMatchesActualResultsByName() {
        
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island" ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        
        int numGroupedCards = searchDAO.numberOfUniqueNamesWithSearchParameters( searchParams );
        assertEquals( 1, numGroupedCards );
        
        int numTotalCards = searchDAO.numberOfResultsWithSearchParameters( searchParams );
        
        List<MagicCard> cardList = searchDAO.getAllByNameWithSearchParameters( searchParams );
        assertEquals( numTotalCards, cardList.size() );
    }
    
    @Test
    public void testLoadMyCardsIsland() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island" ) );
        searchParams.add( new SearchParameter( FieldName.OWNED, "" ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        
        List<MagicCard> groupedCards = searchDAO.getAllByNameWithSearchParameters( searchParams );
        
        assertNotNull( groupedCards );
        assertFalse( groupedCards.isEmpty() );
        
        for( MagicCard card : groupedCards ) {
            assertTrue( card.getName().equals( "Island" ) );
        }
    }
    
    @Test
    public void testLoadAllIslandByPageWithLanguage() {
        
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island%" ) );
        searchParams.add( new SearchParameter( FieldName.COLOR, Color.COLORLESS ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        
        List<MagicCard> groupedCards = searchDAO.getAllByNameWithSearchParameters( searchParams );

        for( MagicCard card : groupedCards ) {
            assertTrue( card.getName().startsWith( "Island" ) );
            assertTrue( card.getColors().contains( Color.COLORLESS ) );
        }
    }
    
    @Test
    public void getPageByNameWithSearchParametersAndPageSizeTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "%Paladin%" ) );
        searchParams.add( new SearchParameter( FieldName.COLOR, Color.WHITE ) );
        searchParams.add( new SearchParameter( FieldName.TYPE, Type.CREATURE ) );
        searchParams.add( new SearchParameter( FieldName.SUBTYPE, new SubType( "Human" ) ) );
        
        List<MagicCard> groupedCards = searchDAO.getPageByNameWithSearchParametersAndPageSize( 1, 10, searchParams );
        
        //Check if only 10 named cards exist
        List<String> uniqueNames = new ArrayList<>();
        for( MagicCard card : groupedCards ) {
            if ( !uniqueNames.contains( card.getName() ) ) {
                uniqueNames.add( card.getName() );
            }
        }
        assertEquals( 10, uniqueNames.size() );
    }
    
    @Test
    public void numberOfUniqueNamesWithSearchParametersTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "%Paladin%" ) );
        searchParams.add( new SearchParameter( FieldName.COLOR, Color.WHITE ) );
        searchParams.add( new SearchParameter( FieldName.TYPE, Type.CREATURE ) );
        searchParams.add( new SearchParameter( FieldName.SUBTYPE, new SubType( "Human" ) ) );
        
        int numResults = searchDAO.numberOfUniqueNamesWithSearchParameters( searchParams );
        
        assertEquals( 12, numResults );
    }
    
    @Test
    public void numberOfResultsWithSearchParametersTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Fireball" ) );
        
        int numResults = searchDAO.numberOfResultsWithSearchParameters( searchParams );
        assertTrue( numResults > 0 );
        assertEquals( 55, numResults ); //The current number of Fireball cards in the database
    }
    
    @Test
    public void numberOfResultsWithIslandWithSearchParametersTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island" ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        
        int numResults = searchDAO.numberOfResultsWithSearchParameters( searchParams );
        assertTrue( numResults > 0 );
        assertEquals( 360, numResults ); //The current number of English Island cards in the database
    }
    
    @Test
    public void getPageOneWithSearchParametersTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Fireball" ) );
        
        List<MagicCard> fireballCardsPageOne = searchDAO.getPageWithSearchParameters( 1, searchParams );
        assertEquals( searchDAO.numberOfResultsPerPage(), fireballCardsPageOne.size() );

        for( MagicCard card : fireballCardsPageOne ) {
            assertEquals( "Fireball", card.getName() );
        }
    }
    
    @Test
    public void getPageWithSearchParametersTestPaging() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Fireball" ) );
        
        int expectedResultCount = searchDAO.numberOfResultsWithSearchParameters( searchParams );
        
        List<MagicCard> fireballCardsPage = null;
        int actualResultCount = 0;
        int page = 1;
        boolean done = false;
        while ( !done ) {
            fireballCardsPage = searchDAO.getPageWithSearchParameters( page, searchParams );
            actualResultCount += fireballCardsPage.size();
            done = fireballCardsPage.size() == 0;
            page++;
        }
        
        assertEquals( expectedResultCount, actualResultCount );
    }
    
    @Test
    public void numberOfResultsWithSearchParametersCompleteParametersTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "%Paladin%" ) );
        searchParams.add( new SearchParameter( FieldName.COLOR, Color.WHITE ) );
        searchParams.add( new SearchParameter( FieldName.TYPE, Type.CREATURE ) );
        searchParams.add( new SearchParameter( FieldName.SUBTYPE, new SubType( "Human" ) ) );
        
        int resultCount = searchDAO.numberOfResultsWithSearchParameters( searchParams );
        assertEquals( 20, resultCount );
    }
    
    @Test
    public void getAllByExactNameAndLanguageTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.NAME, "Island" ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        List<MagicCard> cardList = searchDAO.getAllWithSearchParameters( searchParams );
        
        assertNotNull( cardList );
        assertFalse( cardList.isEmpty() );
        
        for ( MagicCard card : cardList ) {
            assertEquals( "Island", card.getName() );
            assertEquals( Language.ENGLISH, card.getLanguage() );
        }
    }
    
    @Test
    public void getAllByTypeAndLanguageTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.TYPE, Type.PLANESWALKER ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        List<MagicCard> cardList = searchDAO.getAllWithSearchParameters( searchParams );
        
        assertNotNull( cardList );
        assertFalse( cardList.isEmpty() );

        assertEquals( 4, cardList.size() );
        for ( MagicCard card : cardList ) {
            assertTrue( card.getType().contains( Type.PLANESWALKER.toString() ) );
            assertEquals( Language.ENGLISH, card.getLanguage() );
        }
    }
    
    @Test
    public void getAllByColorAndLanguageTest() {
        List<SearchParameter> searchParams = new ArrayList<>();
        searchParams.add( new SearchParameter( FieldName.COLOR, Color.WHITE ) );
        searchParams.add( new SearchParameter( FieldName.LANGUAGE, Language.ENGLISH ) );
        List<MagicCard> cardList = searchDAO.getAllWithSearchParameters( searchParams );
        assertNotNull( cardList );
        assertFalse( cardList.isEmpty() );
        
        for ( MagicCard card : cardList ) {
            assertTrue( card.getColors().contains( Color.WHITE ) );
            assertEquals( Language.ENGLISH, card.getLanguage() );
        }
    }
    
    protected static boolean searchDAOTesterInitializationComplete = false;
    @Override
    public void additionalSetUp() {
        
        if ( !searchDAOTesterInitializationComplete ) {
            LOG.trace( "Initializing embedded database for SearchDAO tests." );
        
            //Create cards custom to this test
            MagicCard fireball = new MagicCard( OWNED_FIREBALL_ID, "Fireball", "XR", Type.SORCERY.toString(), "Magic 2012" );
            fireball.setLanguage( Language.ENGLISH );
            
            //Insert into database
            assertTrue( cardDAO.addCardToDatabase( fireball ) );
            
            //Add to owned list
            assertTrue( cardDAO.incrementOwnedCard( OWNED_FIREBALL_ID ) );
            
            //Add Islands
            super.addAllIslands();
            
            //Add Paladins
            //20 total, 12 unique names
            super.addPaladins();
            
            //Add some white cards
            super.addWhiteCards();
            
            //Add Planeswalkers to database
            super.addPlaneswalkersNamedChandra();
            
            //Create multiple pages of Fireball for page testing
            super.addFireballs();

            searchDAOTesterInitializationComplete = true;
        }
    }
}
