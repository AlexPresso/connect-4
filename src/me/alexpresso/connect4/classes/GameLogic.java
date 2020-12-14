package me.alexpresso.connect4.classes;

import me.alexpresso.connect4.classes.grid.Grid;
import me.alexpresso.connect4.classes.grid.GridLocation;
import me.alexpresso.connect4.exceptions.LocationException;

import java.util.concurrent.atomic.AtomicBoolean;
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

        final AtomicBoolean prevXLeft = new AtomicBoolean(true);
        final AtomicBoolean prevXRight = new AtomicBoolean(true);
        final AtomicBoolean prevYTop = new AtomicBoolean(true);
        final AtomicBoolean prevYBot = new AtomicBoolean(true);
        final AtomicBoolean prevZTop = new AtomicBoolean(true);
        final AtomicBoolean prevZBot = new AtomicBoolean(true);
        final AtomicBoolean prevATop = new AtomicBoolean(true);
        final AtomicBoolean prevABot = new AtomicBoolean(true);

        for(int i = 1; i <= WIN_ADJACENTS - 1; i++) {
            final int leftX = placedX - i;
            final int rightX = placedX + i;
            final int topY = placedY + i;
            final int botY = placedY - i;

            //Horizontal
            if(prevXLeft.get())
                grid.get(leftX, placedY, whoPlayed).ifPresentOrElse(x -> xAdjacents.getAndIncrement(), () -> prevXLeft.set(false));
            if(prevXRight.get())
                grid.get(rightX, placedY, whoPlayed).ifPresentOrElse(x -> xAdjacents.getAndIncrement(), () -> prevXRight.set(false));

            //Vertical
            if(prevYTop.get())
                grid.get(placedX, topY, whoPlayed).ifPresentOrElse(y -> yAdjacents.getAndIncrement(), () -> prevYTop.set(false));
            if(prevYBot.get())
                grid.get(placedX, botY, whoPlayed).ifPresentOrElse(y -> yAdjacents.getAndIncrement(), () -> prevYBot.set(false));

            //Diagonal A
            if(prevATop.get())
                grid.get(leftX, topY, whoPlayed).ifPresentOrElse(a -> aAdjacents.getAndIncrement(), () -> prevATop.set(false));
            if(prevABot.get())
                grid.get(rightX, botY, whoPlayed).ifPresentOrElse(a -> aAdjacents.getAndIncrement(), () -> prevABot.set(false));

            //Diagonal Z
            if(prevZTop.get())
                grid.get(rightX, topY, whoPlayed).ifPresentOrElse(z -> zAdjacents.getAndIncrement(), () -> prevZTop.set(false));
            if(prevZBot.get())
                grid.get(leftX, botY, whoPlayed).ifPresentOrElse(z -> zAdjacents.getAndIncrement(), () -> prevZBot.set(false));

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
