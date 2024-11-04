// Ship.java
public class Ship {
    private int size;
    private int hits;

    public Ship(int size) {
        this.size = size;
        this.hits = 0;
    }

    public boolean isSunk() {
        return hits == size;
    }

    public void hit() {
        hits++;
    }
}
