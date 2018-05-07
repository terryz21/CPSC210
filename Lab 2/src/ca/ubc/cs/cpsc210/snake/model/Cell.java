package ca.ubc.cs.cpsc210.snake.model;

// Represents a cell - a location on the board.
public class Cell {
    // size of cell in screen coordinates
    public static final int CELL_PIXELS = 30;

    private int column;
    private int row;

    // EFFECTS: constructs cell at given row and column on board
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // EFFECTS: returns column in which cell is located
    public int getColumn() {
        return column;
    }

    // EFFECTS: returns row in which cell is located
    public int getRow() {
        return row;
    }

    // EFFECTS: returns horizontal screen coordinate of top-left corner of cell
    public int getScreenHorizontalCoord() {
        return column * CELL_PIXELS;
    }

    // EFFECTS: returns vertical screen coordinate of top-left corner of cell
    public int getScreenVerticalCoord() {
        return row * CELL_PIXELS;
    }

    /// NOTE: don't concern yourselves with the equals() and hashCode() methods for now.
    /// We'll talk about what they do later in the course.

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Cell otherCell = (Cell) other;

        if (column != otherCell.column) return false;
        return row == otherCell.row;

    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
