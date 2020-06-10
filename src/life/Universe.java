package life;

import java.util.Random;

public class Universe {
    private final boolean[][] grid;
    private final int gen;

    public Universe(boolean[][] grid, int gen) {
        this.grid = grid;
        this.gen = gen;
    }

    public Universe(int size, int gen) {
        Random random = new Random();
        boolean[][] grid = new boolean[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = random.nextBoolean();
            }
        }

        this.grid = grid;
        this.gen = gen;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public int getGen() {
        return gen;
    }

    public int countLiveCells() {
        int size = grid.length;
        int liveCells = 0;

        for (boolean[] booleans : grid) {
            for (int col = 0; col < size; col++) {
                if (booleans[col]) {
                    liveCells += 1;
                }
            }
        }

        return liveCells;
    }

    public int getSize() {
        return grid.length;
    }
}
