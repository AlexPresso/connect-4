package me.alexpresso.connect4.forms;

import me.alexpresso.connect4.classes.Player;
import me.alexpresso.connect4.classes.PlayersTableModel;
import me.alexpresso.connect4.classes.colors.EColor;

import javax.swing.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Configurator {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField verticalSize;
    private JTextField adjacentsNb;
    private JTextField horizontalSize;
    private JTable playersTable;
    private JButton playButton;
    private JButton addPlayerButton;

    public Configurator(final JFrame frame) {
        this.frame = frame;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    private void createUIComponents() {
        this.playButton = new JButton();
        this.playersTable = new JTable();
        this.addPlayerButton = new JButton();

        final PlayersTableModel model = PlayersTableModel.of(
            new Player("Player 1", EColor.YELLOW),
            new Player("Player 2", EColor.RED)
        );
        this.playersTable.setModel(model);

        this.addPlayerButton.addActionListener(e -> {
            final AddPlayer addPlayer = new AddPlayer(frame, model);
            addPlayer.pack();
            addPlayer.setVisible(true);
        });

        this.playButton.addActionListener(e -> this.play());
    }

    private void play() {
        final int xSize = Integer.parseInt(this.horizontalSize.getText());
        final int ySize = Integer.parseInt(this.verticalSize.getText());
        final int adjacents = Integer.parseInt(this.adjacentsNb.getText());

        final JFrame gameFrame = new JFrame("Connect 4");
        final Game game = new Game(xSize, ySize, adjacents, ((PlayersTableModel) this.playersTable.getModel()).getPlayers());

        gameFrame.setContentPane(game.getMainPanel());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
}
