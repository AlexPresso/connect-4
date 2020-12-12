package me.alexpresso.connect4;

import me.alexpresso.connect4.classes.Player;
import me.alexpresso.connect4.forms.Game;

import javax.swing.*;
import java.util.List;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Main {
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            final JFrame frame = new JFrame("Connect 4");
            final Game game = new Game(7, 6, List.of(
                new Player("Player 1", "#ff0000"),
                new Player("Player 2", "#ffff00")
            ));

            frame.setContentPane(game.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
