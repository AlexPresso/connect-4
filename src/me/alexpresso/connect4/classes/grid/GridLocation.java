package me.alexpresso.connect4.classes.grid;

import me.alexpresso.connect4.classes.Player;
import me.alexpresso.connect4.exceptions.CheatException;

import javax.swing.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class GridLocation {
    private Player player;
    private int column;
    private final JCheckBox checkBox;


    public GridLocation(final JCheckBox checkBox, final int column) {
        this.checkBox = checkBox;
        this.player = null;
        this.column = column;
    }


    public Player getPlayer() {
        return this.player;
    }

    public GridLocation setPlayer(final Player player) throws CheatException {
        if(this.player != null)
            throw new CheatException("Cheater !");

        this.player = player;
        this.checkBox.setForeground(player.getColor());
        this.checkBox.setBackground(player.getColor());

        return this;
    }

    public int getColumn() {
        return this.column;
    }
}
