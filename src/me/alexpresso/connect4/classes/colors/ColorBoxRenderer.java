package me.alexpresso.connect4.classes.colors;

import javax.swing.*;
import java.awt.*;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class ColorBoxRenderer extends JPanel implements ListCellRenderer<EColor> {


    @Override
    public Component getListCellRendererComponent(JList<? extends EColor> list, EColor value, int index, boolean isSelected, boolean cellHasFocus) {
        final JPanel panel = new JPanel();
        panel.setBackground(value.getColor());

        return panel;
    }
}
