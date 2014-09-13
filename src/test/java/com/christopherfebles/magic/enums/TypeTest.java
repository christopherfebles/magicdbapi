package com.christopherfebles.magic.enums;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.christopherfebles.magic.testsupport.UnitTest;

@Category( UnitTest.class )
public class TypeTest {

    @Test
    public void testContainsLand() {
        String land = "LAND";

        assertTrue( Type.contains( land ) );
    }

}
