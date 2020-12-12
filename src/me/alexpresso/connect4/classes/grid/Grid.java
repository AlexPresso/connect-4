package me.alexpresso.connect4.classes.grid;

import me.alexpresso.connect4.classes.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Grid {
    private final Map<Integer, GridColumn> columns;


    public Grid() {
        this.columns = new HashMap<>();
    }


    public Map<Integer, GridColumn> getColumns() {
        return this.columns;
    }

    public Optional<GridLocation> get(final int x, final int y) {
        return this.get(x, y, null);
    }
    public Optional<GridLocation> get(final int x, final int y, final Player player) {
        try {
            final GridLocation location = this.columns.get(x).getLocations().get(y);

            if(player != null && location.getPlayer() != player)
                return Optional.empty();

            return Optional.of(location);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
