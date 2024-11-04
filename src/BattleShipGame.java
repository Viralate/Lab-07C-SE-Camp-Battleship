// BattleShipGame.java
import javax.swing.*;
import java.awt.*;

public class BattleShipGame extends JFrame {
    public BattleShipGame() {
        setTitle("Battleship Game");
        Board board = new Board();
        GameController controller = new GameController(board);

        JPanel grid = new JPanel(new GridLayout(10, 10));
        for (Cell[] row : board.getCells()) {
            for (Cell cell : row) {
                cell.addActionListener(e -> controller.processMove(cell));
                grid.add(cell);
            }
        }
        add(grid);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BattleShipGame::new);
    }
}
