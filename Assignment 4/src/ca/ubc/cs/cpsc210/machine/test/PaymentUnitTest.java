package ca.ubc.cs.cpsc210.machine.test;

import ca.ubc.cs.cpsc210.machine.model.Coin;
import ca.ubc.cs.cpsc210.machine.model.PaymentUnit;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PaymentUnitTest {
    private PaymentUnit testPayment;

    @Before
    public void runBefore() {
        testPayment = new PaymentUnit();

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.LOONIE));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.DIME));
        assertEquals(0, testPayment.getValueOfCoinsInserted());
    }

    @Test
    public void testAddCoinsToBanked() {
        testPayment.addCoinsToBanked(Coin.LOONIE, 3);
        assertEquals(3, testPayment.getNumberOfCoinsBankedOfType(Coin.LOONIE));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(0, testPayment.getNumberOfCoinsBankedOfType(Coin.DIME));
        assertEquals(300, testPayment.getValueOfCoinsBanked());

        testPayment.addCoinsToBanked(Coin.QUARTER, 4);
        testPayment.addCoinsToBanked(Coin.DIME, 10);
        testPayment.addCoinsToBanked(Coin.NICKEL,20);
        assertEquals(4, testPayment.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(10, testPayment.getNumberOfCoinsBankedOfType(Coin.DIME));
        assertEquals(20, testPayment.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(600, testPayment.getValueOfCoinsBanked());

        testPayment.clearCoinsBanked();
        assertEquals(0, testPayment.getValueOfCoinsBanked());
        testPayment.clearCoinsBanked();
        assertEquals(0, testPayment.getValueOfCoinsBanked());
    }


    @Test
    public void testCancelTranscation() {
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.LOONIE);
        assertEquals(2, testPayment.cancelTransaction().size());
    }

    @Test
    public void testCancelTranscation1() {
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.LOONIE);
        assertEquals(Coin.QUARTER, testPayment.cancelTransaction().get(0));



    }

    @Test
    public void testCancelTranscation2() {
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.LOONIE);
        assertEquals(Coin.LOONIE, testPayment.cancelTransaction().get(1));
    }


    @Test
    public void testMakePurchaseZeroChange() {
        testPayment.addCoinsToBanked(Coin.QUARTER, 4);
        testPayment.insertCoin(Coin.QUARTER);
        assertEquals(25, testPayment.getValueOfCoinsInserted());
        assertEquals(0, testPayment.makePurchase(25).size());
    }

    @Test
    public void testMakePurchaseZeroChangeWithZeroBanked() {
        testPayment.insertCoin(Coin.QUARTER);
        assertEquals(25, testPayment.getValueOfCoinsInserted());
        assertEquals(0, testPayment.makePurchase(25).size());
        assertEquals(0, testPayment.getValueOfCoinsBanked());
    }


    @Test
    public void testMakePurchase() {
        testPayment.addCoinsToBanked(Coin.LOONIE, 3);
        testPayment.addCoinsToBanked(Coin.QUARTER, 4);
        testPayment.addCoinsToBanked(Coin.DIME, 10);
        testPayment.addCoinsToBanked(Coin.NICKEL,20);

        testPayment.insertCoin(Coin.LOONIE);
        testPayment.insertCoin(Coin.LOONIE);
        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.DIME);
        assertEquals(215, testPayment.getValueOfCoinsInserted());
        assertEquals(2, testPayment.makePurchase(180).size());
        assertEquals(0, testPayment.getValueOfCoinsInserted());
    }

    @Test
    public void testMakePurchaseLoonieChange() {
        testPayment.addCoinsToBanked(Coin.LOONIE, 3);
        testPayment.addCoinsToBanked(Coin.QUARTER, 4);
        testPayment.addCoinsToBanked(Coin.DIME, 10);
        testPayment.addCoinsToBanked(Coin.NICKEL,20);

        testPayment.insertCoin(Coin.LOONIE);
        testPayment.insertCoin(Coin.LOONIE);
        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.DIME);
        assertEquals(215, testPayment.getValueOfCoinsInserted());
        assertTrue(testPayment.makePurchase(35).contains(Coin.LOONIE));
    }

    @Test
    public void testMakePurchaseNoLoonieChange() {
        testPayment.addCoinsToBanked(Coin.QUARTER, 4);
        testPayment.addCoinsToBanked(Coin.DIME, 10);
        testPayment.addCoinsToBanked(Coin.NICKEL,20);

        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.DIME);
        assertEquals(115, testPayment.getValueOfCoinsInserted());
        assertFalse(testPayment.makePurchase(15).contains(Coin.LOONIE));
    }

    @Test
    public void testMakePurchaseNoDimeChange() {
        testPayment.addCoinsToBanked(Coin.NICKEL,20);

        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.NICKEL);
        testPayment.insertCoin(Coin.NICKEL);
        assertEquals(20, testPayment.getValueOfCoinsInserted());
        assertFalse(testPayment.makePurchase(10).contains(Coin.DIME));

        //assertFalse(testPayment.makePurchase(20).contains(Coin.DIME));
    }

    @Test
    public void testMakePurchaseNotEnoughChange() {
        testPayment.addCoinsToBanked(Coin.LOONIE,1);
        testPayment.addCoinsToBanked(Coin.DIME, 1);

        testPayment.insertCoin(Coin.DIME);
        testPayment.insertCoin(Coin.DIME);
        testPayment.insertCoin(Coin.DIME);
        testPayment.insertCoin(Coin.DIME);
        testPayment.insertCoin(Coin.DIME);
        assertEquals(50, testPayment.getValueOfCoinsInserted());
        assertEquals(4, testPayment.makePurchase(5).size());
    }

    @Test
    public void testMakePurchaseMoreThanBanked() {
        testPayment.insertCoin(Coin.DIME);
        testPayment.insertCoin(Coin.DIME);
        assertEquals(20,testPayment.getValueOfCoinsInserted());
        assertEquals(0, testPayment.makePurchase(20).size());
        assertEquals(0, testPayment.getValueOfCoinsInserted());

        testPayment.addCoinsToBanked(Coin.LOONIE, 3);
        testPayment.insertCoin(Coin.LOONIE);
        testPayment.insertCoin(Coin.QUARTER);
        testPayment.makePurchase(115);
        assertEquals(0, testPayment.getValueOfCoinsInserted());

    }


}
