package com.christopherfebles.magic.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.christopherfebles.magic.testsupport.DAOTester;
import com.christopherfebles.magic.testsupport.UnitTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:/applicationContext-test.xml" } )
@Category( UnitTest.class )
public class ExpansionDAOImplTest extends DAOTester {

    @Autowired
    private ExpansionDAO expansionDAO;
    
    @Test
    public void getAllExpansionsTest() {
        List<String> allExpansions = expansionDAO.getAllExpansions();

        assertNotNull( allExpansions );
        assertFalse( allExpansions.isEmpty() );
    }
    
}
