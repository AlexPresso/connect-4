package me.alexpresso.connect4.classes.grid;

import me.alexpresso.connect4.classes.Player;
import me.alexpresso.connect4.exceptions.CheatException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class GridColumn {
    private int lastInsertedIndex;
    private final List<GridLocation> locations;


    public GridColumn(final int ySize) {
        this.lastInsertedIndex = ySize;
        this.locations = new ArrayList<>();
    }


    public int getLastInsertedIndex() {
        return this.lastInsertedIndex;
    }

    public GridColumn insertPlayerCoin(final Player player) throws CheatException {
        final int newIndex = this.lastInsertedIndex - 1;

        this.locations.get(newIndex).setPlayer(player);
        this.lastInsertedIndex = newIndex;

        return this;
    }

    public List<GridLocation> getLocations() {
        return this.locations;
    }
}
