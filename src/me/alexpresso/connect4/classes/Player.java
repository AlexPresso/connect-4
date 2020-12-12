package me.alexpresso.connect4.classes;

import java.awt.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Player {
    private final String name;
    private final Color color;


    public Player(final String name, final String color) {
        this.name = name;
        this.color = Color.decode(color);
    }


    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }
}
