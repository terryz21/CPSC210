package ca.ubc.cs.cpsc210.snake.model;

// Represents food.
public class Food {
    public static final int DECAY_AMOUNT = 10;
    public static final int INITIAL_NUTRITIONAL_VALUE = 100;

    // the position of food
    private Cell position;
    // the nutritional value of food
    private int nutritionalValue;

    // EFFECTS: food has INITIAL_NUTRITIONAL_VALUE and is located at given cell
    public Food(Cell position) {
        this.nutritionalValue = Food.INITIAL_NUTRITIONAL_VALUE;
        this.position = position;
    }

    // EFFECTS: food has given nutritional value and is located at given cell
    public Food(Cell position, int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
        this.position = position;
    }

    public Cell getPosition() {
        return new Cell(position.getRow(), position.getColumn());
    }

    // MODIFIES: this
    // EFFECTS:  reduces nutritional value of food by DECAY_AMOUNT while maintaining a minimum
    //           nutritional value of zero
    public void decay() {
        if ((this.nutritionalValue - DECAY_AMOUNT) < 0) {
            this.nutritionalValue = 0;
        }
        else {
            this.nutritionalValue = nutritionalValue - DECAY_AMOUNT;
        }
    }


    public int getNutritionalValue() {
        return this.nutritionalValue;
    }
}
