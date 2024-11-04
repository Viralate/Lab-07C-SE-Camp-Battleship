// Cell.java
import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    enum CellState { BLANK, MISS, HIT }
    private CellState state;

    public Cell() {
        super("~");
        this.state = CellState.BLANK;
        setBackground(Color.CYAN);
    }

    public void setState(CellState state) {
        this.state = state;
        switch (state) {
            case MISS -> setText("M");
            case HIT -> setText("X");
            default -> setText("~");
        }
        setEnabled(false); // Disable the cell once clicked
    }

    public CellState getState() {
        return state;
    }
}
