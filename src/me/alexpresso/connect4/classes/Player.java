package me.alexpresso.connect4.classes;

import me.alexpresso.connect4.classes.colors.EColor;

import java.awt.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Player {
    private String name;
    private EColor color;


    public Player(final String name, final EColor color) {
        this.name = name;
        this.color = color;
    }


    public String getName() {
        return this.name;
    }
    public Player setName(final String name) {
        this.name = name;
        return this;
    }

    public EColor getColor() {
        return this.color;
    }
    public Player setColor(final EColor color) {
        this.color = color;
        return this;
    }
}
