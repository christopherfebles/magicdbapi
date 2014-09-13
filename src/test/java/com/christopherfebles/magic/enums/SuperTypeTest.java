package com.christopherfebles.magic.enums;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.christopherfebles.magic.testsupport.UnitTest;

@Category( UnitTest.class )
public class SuperTypeTest {

    @Test
    public void testContainsBasic() {
        String basic = "BASIC";

        assertTrue( SuperType.contains( basic ) );
    }

}
