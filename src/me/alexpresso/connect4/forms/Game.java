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
    private int turn;
    private Grid grid;

    private JPanel buttonsPanel;
    private JPanel gridPanel;
    private JPanel mainPanel;


    public Game(final int xSize, final int ySize, final List<Player> players) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.players = players;
        this.turn = 0;
    }


    private void createUIComponents() {
        this.buttonsPanel = new JPanel();
        this.gridPanel = new JPanel();
        this.grid = new Grid();

        for(int x = 0; x < xSize; x++) {
            final int column = x;
            final JButton button = new JButton(Integer.toString(x+1));

            button.addActionListener(e -> this.selectColumn(column, e));
            buttonsPanel.add(button);

            final JPanel vertical = new JPanel(new GridBagLayout());
            final GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.VERTICAL;
            constraints.gridx = column;
            constraints.gridy = 0;

            this.grid.getColumns().computeIfAbsent(column, k -> new GridColumn(ySize));

            for(int row = 0; row < ySize; row++) {
                final JCheckBox checkBox = new JCheckBox("", false);
                checkBox.setEnabled(false);
                checkBox.setBorderPainted(false);

                constraints.gridy = row;
                vertical.add(checkBox, constraints);

                this.grid.getColumns().get(column).getLocations().add(new GridLocation(checkBox, column));
            }

            gridPanel.add(vertical);
        }
    }

    private void selectColumn(final int i, final ActionEvent e) {
        final Player playing = this.players.get((this.turn + 1) % 2);

        try {
            final int lastInserted = this.grid.getColumns().get(i)
                .insertPlayerCoin(playing)
                .getLastInsertedIndex();

            if(lastInserted == 0)
                ((JButton) e.getSource()).setEnabled(false);

            if(GameLogic.hasWon(this.grid, i, lastInserted)) {
                JOptionPane.showMessageDialog(this.mainPanel, String.format("%s won !", playing.getName()));
                System.exit(0);
            }

            this.turn++;
        } catch (CheatException | LocationException ex) {
            JOptionPane.showMessageDialog(this.mainPanel, ex.getMessage());
        }
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
