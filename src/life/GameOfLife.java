package life;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameOfLife extends JFrame {
    private final JPanel sidePanel;
    private final JPanel buttonsPanel;
    private final JPanel resizingPanel;
    private final JTextField sizeField;
    private final JPanel statPanel;
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private int gridSize = Main.DEFAULT_SIZE;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        Font font = new Font("Courier", Font.BOLD, 14);

        JPanel side = new JPanel();
        side.setMaximumSize(new Dimension(250, 600));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        this.sidePanel = side;

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 3, 0, 0));
        buttons.setMaximumSize(new Dimension(250, 40));

        JButton advance = new JButton("Advance");
        advance.setName("AdvanceButton");
        advance.setBackground(Color.YELLOW);
        advance.addActionListener(event -> Main.getEvolution().evolve());
        buttons.add(advance);

        final Timer timer = new Timer(1000, event -> Main.getEvolution().evolve());

        JToggleButton play = new JToggleButton("Play");
        play.setName("PlayToggleButton");
        play.setBackground(Color.GREEN);
        play.addActionListener(event -> {
            if (play.isSelected()) {
                play.setText("Pause");
                timer.restart();
            } else {
                play.setText("Play");
                play.setBackground(Color.GREEN);
                timer.stop();
            }
        });
        buttons.add(play);

        JButton reset = new JButton("Reset");
        reset.setName("ResetButton");
        reset.setBackground(Color.RED);
        reset.addActionListener(event -> Main.getEvolution().reset());
        buttons.add(reset);

        this.buttonsPanel = buttons;

        JPanel resizingPanel = new JPanel();
        resizingPanel.setLayout(new GridLayout(2, 0, 0, 5));
        resizingPanel.setMaximumSize(new Dimension(200, 60));

        JLabel resizeLabel = new JLabel(" Set new size:");
        resizeLabel.setName("ResizeLabel");
        resizeLabel.setFont(font);
        resizeLabel.setFont(resizeLabel.getFont().deriveFont(12f));
        resizingPanel.add(resizeLabel);
        this.resizingPanel = resizingPanel;

        JTextField sizeField = new JTextField();
        sizeField.setName("SizeField");
        sizeField.setBackground(Color.WHITE);
        sizeField.setText(String.valueOf(Main.DEFAULT_SIZE));
        sizeField.setPreferredSize(new Dimension(150, 24));
        sizeField.addActionListener(event -> {
            Pattern number = Pattern.compile("[1-9]+[0-9]*");
            String input = sizeField.getText();
            Matcher num = number.matcher(input);

            if (num.matches()) {
                this.gridSize = Integer.parseInt(sizeField.getText());
            }
        });
        resizingPanel.add(sizeField);
        this.sizeField = sizeField;

        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(2, 0, 0, 1));
        statPanel.setMaximumSize(new Dimension(200, 50));
        this.statPanel = statPanel;

        JLabel genLabel = new JLabel("Generation #0");
        genLabel.setName("GenerationLabel");
        genLabel.setFont(font);
        statPanel.add(genLabel);
        this.genLabel = genLabel;

        JLabel aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setFont(font);
        statPanel.add(aliveLabel);
        this.aliveLabel = aliveLabel;

        side.add(buttons);
        side.add(resizingPanel);
        side.add(statPanel);

        add(side);

        setVisible(true);
    }

    public JPanel getSidePanel() {
        return sidePanel;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public JPanel getResizingPanel() {
        return resizingPanel;
    }

    public JTextField getSizeField() {
        return sizeField;
    }

    public JPanel getStatPanel() {
        return statPanel;
    }

    public JLabel getGenLabel() {
        return genLabel;
    }

    public JLabel getAliveLabel() {
        return aliveLabel;
    }

    public int getGridSize() {
        return gridSize;
    }
}
