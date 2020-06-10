package life;

public class Main {
    public static final int DEFAULT_SIZE = 30;
    private static Evolution evolution;

    public static void main(String[] args) {
        Universe universe = new Universe(DEFAULT_SIZE, 0);
        evolution = new Evolution(universe, new GameOfLife());
    }

    public static Evolution getEvolution() {
        return evolution;
    }
}
