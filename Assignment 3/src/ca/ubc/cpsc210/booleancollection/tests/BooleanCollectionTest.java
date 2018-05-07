package ca.ubc.cpsc210.booleancollection.tests;

import ca.ubc.cpsc210.booleancollection.model.BooleanCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// unit tests for the BooleanCollection class
public class BooleanCollectionTest {
    private BooleanCollection testCollection;


    @Before
    public void runBefore() {
        testCollection = new BooleanCollection();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCollection.getNumberOfItems());
    }

    @Test
    public void testGet() {
        testCollection.add(true);
        testCollection.add(false);
        testCollection.add(true);
        testCollection.add(true);
        testCollection.add(true);
        testCollection.add(false);
        assertEquals(6, testCollection.getNumberOfItems());
        assertTrue(testCollection.get(0));
        assertFalse(testCollection.get(5));
        assertTrue(testCollection.get(3));
    }

    @Test
    public void testAreAllTrue() {
        testCollection.add(true);
        testCollection.add(true);
        assertTrue(testCollection.areAllTrue());

        testCollection.add(false);
        assertFalse(testCollection.areAllTrue());
    }

    @Test
    public void testCountTrue() {
        testCollection.add(true);
        testCollection.add(true);
        assertEquals(2, testCollection.countTrue());

        testCollection.add(false);
        assertEquals(2, testCollection.countTrue());
    }


    //--------------------------------------------------------


    @Test
    public void testAreAllTrueRecursive() {
        assertTrue(testCollection.areAllTrueRecursively());

        testCollection.add(true);
        testCollection.add(true);
        assertTrue(testCollection.areAllTrueRecursively());

        testCollection.add(false);
        assertFalse(testCollection.areAllTrueRecursively());


    }
}