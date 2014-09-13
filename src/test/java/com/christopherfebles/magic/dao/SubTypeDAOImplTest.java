package com.christopherfebles.magic.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.christopherfebles.magic.enums.CardType;
import com.christopherfebles.magic.enums.SubType;
import com.christopherfebles.magic.enums.SuperType;
import com.christopherfebles.magic.enums.Type;
import com.christopherfebles.magic.testsupport.DAOTester;
import com.christopherfebles.magic.testsupport.UnitTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:/applicationContext-test.xml" } )
@Category( UnitTest.class )
public class SubTypeDAOImplTest extends DAOTester {

    @Autowired
    private SubTypeDAO typeDAO;

    @Test
    public void getAllSubTypesOnlySubTypesTest() {
        
        List<SubType> allSubTypes = typeDAO.getAllSubTypes();
        assertNotNull( allSubTypes );
        assertFalse( allSubTypes.isEmpty() );
        
        List<CardType> nonSubTypes = new ArrayList<>();
        nonSubTypes.addAll( Arrays.asList( SuperType.values() ) );
        nonSubTypes.addAll( Arrays.asList( Type.values() ) );
        
        for ( CardType type : nonSubTypes ) {
            for ( SubType subType: allSubTypes ) {
                assertNotEquals( subType.toString(), type.toString() );
            }
        }
    }
    
    @Test
    public void getSubTypesAndFrequencyTestOrdering() {
        Map<SubType, Integer> subTypesMap = typeDAO.getSubTypesAndFrequency();
        assertNotNull( subTypesMap );
        assertFalse( subTypesMap.isEmpty() );
        
        SubType previous = null;
        for ( SubType type : subTypesMap.keySet() ) {
            if ( previous != null ) {
                assertTrue( subTypesMap.get( type ) <= subTypesMap.get( previous ) );
            }
            previous = type;
        }
    }
    
}
