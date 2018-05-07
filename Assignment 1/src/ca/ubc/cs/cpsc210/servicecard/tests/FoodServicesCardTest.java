package ca.ubc.cs.cpsc210.servicecard.tests;

import ca.ubc.cs.cpsc210.servicecard.model.FoodServicesCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// Unit tests for FoodServiceCard class
public class FoodServicesCardTest {
    public static final int INITIAL_BALANCE = 10000;
    private FoodServicesCard testCard;

    @Before
    public void setUp() throws Exception {
        testCard = new FoodServicesCard(INITIAL_BALANCE);
    }

    @Test
    public void testConstructor() {
        assertEquals(INITIAL_BALANCE, testCard.getBalance());
        assertEquals(0, testCard.getRewardPoints());
    }

    @Test
    public void testReload() {
        testCard.reload(1000);
        assertEquals(INITIAL_BALANCE + 1000, testCard.getBalance());
        testCard.reload(0);
        testCard.reload(20000);
        assertEquals(INITIAL_BALANCE + 1000 + 0 + 20000, testCard.getBalance());
    }

    @Test
    public void testMakeNoCashBackPurchase() {
        testCard.makePurchase(900);
        assertEquals(INITIAL_BALANCE - 900,testCard.getBalance());
        assertEquals(0 + 900, testCard.getRewardPoints());
        assertTrue(testCard.makePurchase(900));

    }

    @Test
    public void testMakeCashBackPurchase() {
        testCard.makePurchase(4000);
        assertEquals(INITIAL_BALANCE - 4000 + 20,testCard.getBalance());
        assertEquals(0,testCard.getRewardPoints());
        assertEquals(true, testCard.makePurchase(4000));
    }

    @Test
    public void testMakeInsufficientPurchase() {
        testCard.makePurchase(1000000);
        assertFalse(testCard.makePurchase(1000000));
    }
}