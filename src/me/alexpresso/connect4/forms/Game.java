package me.alexpresso.connect4.forms;

import me.alexpresso.connect4.classes.GameLogic;
import me.alexpresso.connect4.classes.Player;
import me.alexpresso.connect4.classes.grid.Grid;
import me.alexpresso.connect4.classes.grid.GridColumn;
import me.alexpresso.connect4.classes.grid.GridLocation;
import me.alexpresso.connect4.exceptions.CheatException;
import me.alexpresso.connect4.exceptions.LocationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Game {
    private final int xSize;
    private final int ySize;
    private final List<Player> players;
    private final int winAdjacents;
    private int turn;
    private Player playing;
    private Grid grid;

    private JPanel mainPanel;


    public Game(final int xSize, final int ySize, final int winAdjacents, final List<Player> players) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.players = players;
        this.turn = 0;
        this.playing = players.get(0);
        this.winAdjacents = winAdjacents;
    }


    private void createUIComponents() {
        this.mainPanel = new JPanel();
        this.grid = new Grid();

        for(int x = 0; x < xSize; x++) {
            final int column = x;
            final JPanel vertical = new JPanel();
            vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

            final JButton button = new JButton(Integer.toString(x+1));
            button.addActionListener(e -> this.selectColumn(column, e));
            vertical.add(button);

            this.grid.getColumns().computeIfAbsent(column, k -> new GridColumn(ySize));

            for(int row = 0; row < ySize; row++) {
                final JButton coinPlace = new JButton("_");
                coinPlace.setEnabled(false);
                coinPlace.setBorderPainted(false);

                vertical.add(coinPlace);

                this.grid.getColumns().get(column).getLocations().add(new GridLocation(coinPlace, column));
            }

            mainPanel.add(vertical);
        }
    }

    private void selectColumn(final int i, final ActionEvent e) {
        try {
            final int lastInserted = this.grid.getColumns().get(i)
                .insertPlayerCoin(this.playing)
                .getLastInsertedIndex();

            if(lastInserted == 0)
                ((JButton) e.getSource()).setEnabled(false);

            if(GameLogic.hasWon(this.grid, i, lastInserted, this.winAdjacents)) {
                JOptionPane.showMessageDialog(this.mainPanel, String.format("%s won in %s rounds !",
                    this.playing.getName(),
                    this.turn
                ));
                System.exit(0);
            }

            this.playing = GameLogic.nextPlayer(this.playing, this.players);
            this.turn++;

            if(this.turn >= (this.xSize * this.ySize)) {
                JOptionPane.showMessageDialog(this.mainPanel, "It was a good game, but no one won.");
                System.exit(0);
            }
        } catch (CheatException | LocationException ex) {
            JOptionPane.showMessageDialog(this.mainPanel, ex.getMessage());
        }
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
