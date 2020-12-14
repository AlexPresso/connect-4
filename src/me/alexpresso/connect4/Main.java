package me.alexpresso.connect4;

import me.alexpresso.connect4.forms.Configurator;

import javax.swing.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class Main {
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        final JFrame frame = new JFrame("Connect 4 - Settings");
        final Configurator configurator = new Configurator(frame);

        frame.setContentPane(configurator.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
