package ca.ubc.cs.cpsc210.microwave.model;

// Represents a microwave oven
public class MicrowaveOvenStateMachine {
    private static final int SECS_PER_MIN = 60;
    private static final int DEFAULT_POWER_LEVEL = 10;
    private MicrowaveOvenState currentState;
    private int cookingTimeInSeconds;
    private int powerLevel;

    // EFFECTS: constructs microwave oven in idle state with no cooking time and default power level
    public MicrowaveOvenStateMachine() {
        currentState = MicrowaveOvenState.IDLE;
        cookingTimeInSeconds = 0;
        powerLevel = DEFAULT_POWER_LEVEL;
    }

    // EFFECTS: returns current state
    public MicrowaveOvenState getCurrentState() {
        return currentState;
    }

    // REQUIRES: minutes >= 0, 0 <= seconds < 60
    // MODIFIES: this
    // EFFECTS: if current state is IDLE, sets the cooking time, transitions to PROGRAMMED state and returns new state;
    //          otherwise returns null
    public MicrowaveOvenState setTime(int minutes, int seconds) {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case IDLE:
                cookingTimeInSeconds = minutes * SECS_PER_MIN + seconds;
                transitionState = MicrowaveOvenState.PROGRAMMED;
                currentState = transitionState;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    // REQUIRES: 1 <= level <= 10
    // MODIFIES: this
    // EFFECTS: if current state is PROGRAMMED, sets the power level, stays in current state and returns current state;
    //          otherwise returns null
    public MicrowaveOvenState setPowerLevel(int powerLevel) {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                this.powerLevel = powerLevel;
                transitionState = MicrowaveOvenState.PROGRAMMED;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;  // state does not change
    }

    // MODIFIES: this
    // EFFECTS: if current state is PROGRAMMED, starts cooking, transitions to COOKING state and returns new state;
    //          otherwise returns null
    public MicrowaveOvenState start() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                transitionState = MicrowaveOvenState.COOKING;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case COOKING:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    // MODIFIES: this
    // EFFECTS: if current state is PAUSED or PROGRAMMED, sets cooking time to 0, sets power level to default,
    //          transitions to IDLE state and returns new state; otherwise returns null
    public MicrowaveOvenState cancel() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PROGRAMMED:
                cookingTimeInSeconds = 0;
                powerLevel = DEFAULT_POWER_LEVEL;
                transitionState = MicrowaveOvenState.IDLE;
                currentState = transitionState;
                break;
            case PAUSED:
                cookingTimeInSeconds = 0;
                powerLevel = DEFAULT_POWER_LEVEL;
                transitionState = MicrowaveOvenState.IDLE;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            default:  // COOKING
                transitionState = null;
        }

        return transitionState;
    }

    // MODIFIES: this
    // EFFECTS: if current state is COOKING, transitions to PAUSED state and returns new state;
    //          otherwise returns null
    public MicrowaveOvenState pause() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case COOKING:
                transitionState = MicrowaveOvenState.PAUSED;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            default:  // PAUSED
                transitionState = null;
        }

        return transitionState;
    }

    // MODIFIES: this
    // EFFECTS: if current state is PAUSED, transitions to COOKING state and returns new state;
    //          otherwise returns null
    public MicrowaveOvenState resume() {
        MicrowaveOvenState transitionState;

        switch(currentState) {
            case PAUSED:
                transitionState = MicrowaveOvenState.COOKING;
                currentState = transitionState;
                break;
            case IDLE:
                transitionState = null;
                break;
            case PROGRAMMED:
                transitionState = null;
                break;
            default:  // COOKING
                transitionState = null;
        }

        return transitionState;
    }
}
