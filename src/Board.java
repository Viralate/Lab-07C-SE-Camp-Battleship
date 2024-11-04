// Board.java
import java.util.Random;

public class Board {
    private Cell[][] cells;
    private Ship[] ships;
    private final int BOARD_SIZE = 10;

    public Board() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        initializeCells();
        placeShips();
    }

    private void initializeCells() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};
        ships = new Ship[shipSizes.length];
        Random rand = new Random();

        for (int i = 0; i < shipSizes.length; i++) {
            Ship ship = new Ship(shipSizes[i]);
            ships[i] = ship;

            boolean placed = false;
            while (!placed) {
                int row = rand.nextInt(BOARD_SIZE);
                int col = rand.nextInt(BOARD_SIZE);
                boolean horizontal = rand.nextBoolean();
                if (canPlaceShip(shipSizes[i], row, col, horizontal)) {
                    placeShip(shipSizes[i], row, col, horizontal, ship);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int size, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + size > BOARD_SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (!cells[row][col + i].getState().equals(Cell.CellState.BLANK)) return false;
            }
        } else {
            if (row + size > BOARD_SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (!cells[row + i][col].getState().equals(Cell.CellState.BLANK)) return false;
            }
        }
        return true;
    }

    private void placeShip(int size, int row, int col, boolean horizontal, Ship ship) {
        for (int i = 0; i < size; i++) {
            if (horizontal) cells[row][col + i].putClientProperty("ship", ship);
            else cells[row + i][col].putClientProperty("ship", ship);
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
