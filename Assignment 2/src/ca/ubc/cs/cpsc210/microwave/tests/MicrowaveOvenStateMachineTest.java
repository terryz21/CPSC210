package ca.ubc.cs.cpsc210.microwave.tests;

import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenState;
import ca.ubc.cs.cpsc210.microwave.model.MicrowaveOvenStateMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// unit tests for MicrowaveOvenStateMachine
public class MicrowaveOvenStateMachineTest {
    private MicrowaveOvenStateMachine testOven;

    @Before
    public void runBefore() {
        testOven = new MicrowaveOvenStateMachine();
    }

    @Test
    public void testConstructor() {
        assertEquals(MicrowaveOvenState.IDLE, testOven.getCurrentState());
    }

    @Test
    public void testSetTimeWhenIdle() {
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setTime(5,25));
    }

    @Test
    public void testSetPowerLevelWhenProgrammed() {
        testOven.setTime(5, 25);
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.getCurrentState());
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.setPowerLevel(9));
    }

    @Test
    public void testStartWhenProgrammed() {
        testOven.setTime(5, 25);
        assertEquals(MicrowaveOvenState.COOKING, testOven.start());
    }

    @Test
    public void testCancelWhenProgrammed() {
        testOven.setTime(5, 25);
        assertEquals(MicrowaveOvenState.IDLE, testOven.cancel());
    }

    @Test
    public void testCancelWhenPaused() {
        testOven.setTime(5, 25);
        testOven.start();
        testOven.pause();
        assertEquals(MicrowaveOvenState.IDLE, testOven.cancel());
    }

    @Test
    public void testPauseWhenCooking() {
        testOven.setTime(5, 25);
        testOven.start();
        assertEquals(MicrowaveOvenState.PAUSED, testOven.pause());
    }

    @Test
    public void testResumeWhenPaused() {
        testOven.setTime(5, 25);
        testOven.start();
        testOven.pause();
        assertEquals(MicrowaveOvenState.COOKING, testOven.resume());
    }



    @Test
    public void testNullReturnsWhenProgrammed() {
        testOven.setTime(5, 25);
        assertEquals(MicrowaveOvenState.PROGRAMMED, testOven.getCurrentState());
        assertEquals(null, testOven.setTime(5,5));
        assertEquals(null, testOven.pause());
        assertEquals(null, testOven.resume());
    }

    @Test
    public void testNullReturnsWhenCooking() {
        testOven.setTime(5, 25);
        testOven.start();
        assertEquals(MicrowaveOvenState.COOKING, testOven.getCurrentState());
        assertEquals(null, testOven.setTime(5,5));
        assertEquals(null, testOven.setPowerLevel(9));
        assertEquals(null, testOven.start());
        assertEquals(null, testOven.cancel());
        assertEquals(null, testOven.resume());
    }

    @Test
    public void testNullReturnsWhenPaused() {
        testOven.setTime(5, 25);
        testOven.start();
        testOven.pause();
        assertEquals(MicrowaveOvenState.PAUSED, testOven.getCurrentState());
        assertEquals(null, testOven.setTime(5,5));
        assertEquals(null, testOven.setPowerLevel(8));
        assertEquals(null, testOven.start());
        assertEquals(null, testOven.pause());
    }

    @Test
    public void testNullReturnsWhenIdle() {
        assertEquals(null, testOven.setPowerLevel(9));
        assertEquals(null, testOven.start());
        assertEquals(null, testOven.cancel());
        assertEquals(null, testOven.pause());
        assertEquals(null, testOven.resume());
    }
}