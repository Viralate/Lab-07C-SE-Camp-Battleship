// GameController.java
import javax.swing.*;

public class GameController {
    private Board board;
    private int missCounter;
    private int strikeCounter;
    private int totalMissCounter;
    private int totalHitCounter;

    public GameController(Board board) {
        this.board = board;
        resetCounters();
    }

    public void processMove(Cell cell) {
        if (cell.getState() == Cell.CellState.BLANK) {
            Ship ship = (Ship) cell.getClientProperty("ship");
            if (ship != null) {
                cell.setState(Cell.CellState.HIT);
                ship.hit();
                totalHitCounter++;
                missCounter = 0;
                if (ship.isSunk()) JOptionPane.showMessageDialog(null, "Ship Sunk!");
                checkWin();
            } else {
                cell.setState(Cell.CellState.MISS);
                missCounter++;
                totalMissCounter++;
                if (missCounter == 5) {
                    strikeCounter++;
                    missCounter = 0;
                }
                checkLoss();
            }
        }
    }

    private void checkWin() {
        if (totalHitCounter == 17) {
            JOptionPane.showMessageDialog(null, "You won!");
            resetGame();
        }
    }

    private void checkLoss() {
        if (strikeCounter == 3) {
            JOptionPane.showMessageDialog(null, "You lost!");
            resetGame();
        }
    }

    private void resetCounters() {
        missCounter = 0;
        strikeCounter = 0;
        totalMissCounter = 0;
        totalHitCounter = 0;
    }

    private void resetGame() {
        resetCounters();
        board = new Board();
    }
}
