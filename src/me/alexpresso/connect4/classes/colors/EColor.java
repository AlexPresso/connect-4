package me.alexpresso.connect4.classes.colors;

import java.awt.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public enum EColor {
    RED(Color.RED),
    YELLOW(Color.YELLOW),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    MAGENTA(Color.MAGENTA),
    PINK(Color.PINK);

    private final Color color;

    EColor(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
