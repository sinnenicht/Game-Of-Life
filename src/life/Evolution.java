package life;

import javax.swing.*;
import java.awt.*;

public class Evolution {
    private Universe model;
    private final GameOfLife view;

    public Evolution(Universe model, GameOfLife view) {
        this.model = model;
        this.view = view;
        updateView();
    }

    public void evolve() {
        boolean[][] lastGen = model.getGrid();
        int size = model.getSize();
        boolean[][] nextGen = new boolean[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int neighbors = countNeighbors(row, col, lastGen);

                if (lastGen[row][col]) {
                    nextGen[row][col] = neighbors >= 2 && neighbors <= 3;
                } else {
                    nextGen[row][col] = neighbors == 3;
                }
            }
        }

        this.model = new Universe(nextGen, model.getGen() + 1);
        updateView();
    }

    private int countNeighbors(int row, int col, boolean[][] grid) {
        int neighbors = 0;

        if (checkNorth(row, col, grid)) {
            neighbors += 1;
        }

        if (checkNorthEast(row, col, grid)) {
            neighbors += 1;
        }

        if (checkEast(row, col, grid)) {
            neighbors += 1;
        }

        if (checkSouthEast(row, col, grid)) {
            neighbors += 1;
        }

        if (checkSouth(row, col, grid)) {
            neighbors += 1;
        }

        if (checkSouthWest(row, col, grid)) {
            neighbors += 1;
        }

        if (checkWest(row, col, grid)) {
            neighbors += 1;
        }

        if (checkNorthWest(row, col, grid)) {
            neighbors += 1;
        }

        return neighbors;
    }

    private boolean checkNorth(int rowIndex, int colIndex, boolean[][] grid) {
        if (rowIndex == 0) {
            return grid[grid.length - 1][colIndex];
        } else {
            return grid[rowIndex - 1][colIndex];
        }
    }

    private boolean checkNorthEast(int rowIndex, int colIndex, boolean[][] grid) {
        int lastIndex = grid.length - 1;

        if (rowIndex == 0 || colIndex == lastIndex) {
            if (rowIndex == 0 && colIndex == lastIndex) {
                return grid[lastIndex][0];
            } else if (rowIndex == 0) {
                return grid[lastIndex][colIndex + 1];
            } else {
                return grid[rowIndex - 1][0];
            }
        } else {
            return grid[rowIndex - 1][colIndex + 1];
        }
    }

    private boolean checkEast(int rowIndex, int colIndex, boolean[][] grid) {
        if (colIndex == grid.length - 1) {
            return grid[rowIndex][0];
        } else {
            return grid[rowIndex][colIndex + 1];
        }
    }

    private boolean checkSouthEast(int rowIndex, int colIndex, boolean[][] grid) {
        int lastIndex = grid.length - 1;

        if (rowIndex == lastIndex || colIndex == lastIndex) {
            if (rowIndex == lastIndex && colIndex == lastIndex) {
                return grid[0][0];
            } else if (rowIndex == lastIndex) {
                return grid[0][colIndex + 1];
            } else {
                return grid[rowIndex + 1][0];
            }
        } else {
            return grid[rowIndex + 1][colIndex + 1];
        }
    }

    private boolean checkSouth(int rowIndex, int colIndex, boolean[][] grid) {
        if (rowIndex == grid.length - 1) {
            return grid[0][colIndex];
        } else {
            return grid[rowIndex + 1][colIndex];
        }
    }

    private boolean checkSouthWest(int rowIndex, int colIndex, boolean[][] grid) {
        int lastIndex = grid.length - 1;

        if (rowIndex == lastIndex || colIndex == 0) {
            if (rowIndex == lastIndex && colIndex == 0) {
                return grid[0][lastIndex];
            } else if (rowIndex == lastIndex) {
                return grid[0][colIndex - 1];
            } else {
                return grid[rowIndex + 1][lastIndex];
            }
        } else {
            return grid[rowIndex + 1][colIndex - 1];
        }
    }

    private boolean checkWest(int rowIndex, int colIndex, boolean[][] grid) {
        if (colIndex == 0) {
            return grid[rowIndex][grid.length - 1];
        } else {
            return grid[rowIndex][colIndex - 1];
        }
    }

    private boolean checkNorthWest(int rowIndex, int colIndex, boolean[][] grid) {
        int lastIndex = grid.length - 1;

        if (rowIndex == 0 || colIndex == 0) {
            if (rowIndex == 0 && colIndex == 0) {
                return grid[lastIndex][lastIndex];
            } else if (rowIndex == 0) {
                return grid[lastIndex][colIndex - 1];
            } else {
                return grid[rowIndex - 1][lastIndex];
            }
        } else {
            return grid[rowIndex - 1][colIndex - 1];
        }
    }

    public void updateView() {
        JPanel side = view.getSidePanel();
        JPanel buttons = view.getButtonsPanel();
        JPanel resizing = view.getResizingPanel();
        JTextField sizeField = view.getSizeField();
        int size = model.getSize();
        JPanel stats = view.getStatPanel();
        JLabel genLabel = view.getGenLabel();
        JLabel aliveLabel = view.getAliveLabel();

        sizeField.setText(String.valueOf(size));
        genLabel.setText(" Generation #" + model.getGen());
        aliveLabel.setText(" Alive: " + model.countLiveCells());

        resizing.add(sizeField);
        stats.add(genLabel);
        stats.add(aliveLabel);

        view.getContentPane().removeAll();
        view.revalidate();
        view.repaint();

        side.add(buttons);
        side.add(resizing);
        side.add(stats);
        view.add(side, BorderLayout.LINE_START);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(size, size, 1, 1));
        board.setBackground(Color.GRAY);

        boolean[][] grid = model.getGrid();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JPanel cell = new JPanel();

                if (grid[row][col]) {
                    cell.setBackground(Color.BLACK);
                } else {
                    cell.setBackground(Color.WHITE);
                }

                board.add(cell);
            }
        }

        view.add(board);

        view.setVisible(true);
    }

    public void reset() {
        this.model = new Universe(view.getGridSize(), 0);
        updateView();
    }
}
