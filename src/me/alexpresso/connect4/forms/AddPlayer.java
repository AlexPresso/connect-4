package me.alexpresso.connect4.forms;

import me.alexpresso.connect4.classes.PlayersTableModel;
import me.alexpresso.connect4.classes.colors.ColorBoxRenderer;
import me.alexpresso.connect4.classes.colors.EColor;
import me.alexpresso.connect4.classes.Player;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;

public class AddPlayer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox<EColor> playerColor;
    private JTextField playerName;

    private PlayersTableModel model;

    public AddPlayer(final JFrame frame, final PlayersTableModel model) {
        super(frame);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.model = model;

        buttonOK.addActionListener(e -> this.onOK());
    }

    private void onOK() {
        if(this.playerName.getText().isEmpty() || this.playerName.getText().isBlank()) {
            JOptionPane.showMessageDialog(this.contentPane, "Player name cannot be empty.");
            return;
        }

        if(this.playerColor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this.contentPane, "You must choose a player color");
            return;
        }

        final List<Player> players = model.getPlayers();

        players.add(new Player(this.playerName.getText(), ((EColor) this.playerColor.getSelectedItem())));

        this.model.fireTableRowsInserted(players.size() - 1, players.size() - 1);
        dispose();
    }

    private void createUIComponents() {
        this.playerColor = new JComboBox<>();
        this.playerColor.setRenderer(new ColorBoxRenderer());

        Arrays.stream(EColor.values()).forEach(this.playerColor::addItem);
    }
}
