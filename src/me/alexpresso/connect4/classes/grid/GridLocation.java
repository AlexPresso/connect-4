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
    private final JButton coinPlace;


    public GridLocation(final JButton coinPlace, final int column) {
        this.coinPlace = coinPlace;
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
        this.coinPlace.setBackground(player.getColor().getColor());

        return this;
    }

    public int getColumn() {
        return this.column;
    }
}
