package ca.ubc.cs.cpsc210.snake.tests;

import ca.ubc.cs.cpsc210.snake.model.Cell;
import ca.ubc.cs.cpsc210.snake.model.SnakeGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// jUnit tests for the Cell class
public class CellTest {
    private Cell testCell;

    @Before
    public void runBefore() {
        testCell = new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2);
    }

    @Test
    public void testScreenHorizontalCoord() {
        assertEquals(testCell.getColumn() * Cell.CELL_PIXELS, testCell.getScreenHorizontalCoord());
    }

    @Test
    public void testScreenVerticalCoord() {
        assertEquals(testCell.getRow() * Cell.CELL_PIXELS, testCell.getScreenVerticalCoord());
    }

    @Test
    public void testEquals() {
        Cell other = new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2);
        assertEquals(testCell, other);
    }

    @Test
    public void testHashCode() {
        Cell other = new Cell(SnakeGame.BOARD_ROWS / 2, SnakeGame.BOARD_COLS / 2);
        assertEquals(testCell.hashCode(), other.hashCode());
    }
}