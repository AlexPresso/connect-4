package me.alexpresso.connect4.classes;

import me.alexpresso.connect4.classes.grid.Grid;
import me.alexpresso.connect4.classes.grid.GridLocation;
import me.alexpresso.connect4.exceptions.LocationException;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author AlexPresso
 * @since 1.0
 */
public class GameLogic {
    private GameLogic() {}

    public static final int WIN_ADJACENTS = 4;

    public static boolean hasWon(final Grid grid, final int placedX, final int placedY) throws LocationException {
        final GridLocation location = grid.get(placedX, placedY)
            .orElseThrow(() -> new LocationException("Cannot find location"));

        final Player whoPlayed = location.getPlayer();

        final AtomicInteger xAdjacents = new AtomicInteger(1);
        final AtomicInteger yAdjacents = new AtomicInteger(1);
        final AtomicInteger zAdjacents = new AtomicInteger(1);
        final AtomicInteger aAdjacents = new AtomicInteger(1);

        for(int i = 1; i <= WIN_ADJACENTS - 1; i++) {
            final int leftX = placedX - i;
            final int rightX = placedX + i;
            final int topY = placedY + i;
            final int botY = placedY - i;

            //Horizontal & Vertical
            grid.get(leftX, placedY, whoPlayed).ifPresent(x -> xAdjacents.getAndIncrement());
            grid.get(rightX, placedY, whoPlayed).ifPresent(x -> xAdjacents.getAndIncrement());
            grid.get(placedX, topY, whoPlayed).ifPresent(y -> yAdjacents.getAndIncrement());
            grid.get(placedX, botY, whoPlayed).ifPresent(y -> yAdjacents.getAndIncrement());

            //Diagonals
            grid.get(leftX, topY, whoPlayed).ifPresent(a -> aAdjacents.getAndIncrement());
            grid.get(rightX, botY, whoPlayed).ifPresent(a -> aAdjacents.getAndIncrement());
            grid.get(rightX, topY, whoPlayed).ifPresent(z -> zAdjacents.getAndIncrement());
            grid.get(leftX, botY, whoPlayed).ifPresent(z -> zAdjacents.getAndIncrement());

            if(
                xAdjacents.get() == WIN_ADJACENTS ||
                yAdjacents.get() == WIN_ADJACENTS ||
                zAdjacents.get() == WIN_ADJACENTS ||
                aAdjacents.get() == WIN_ADJACENTS
            )
                return true;
        }

        return false;
    }
}
