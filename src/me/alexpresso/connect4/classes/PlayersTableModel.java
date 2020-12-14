package me.alexpresso.connect4.classes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author AlexPresso
 * @since 1.0
 */
public class PlayersTableModel extends AbstractTableModel {
    private final List<Player> players;


    public PlayersTableModel() {
        this.players = new ArrayList<>();
    }

    public static PlayersTableModel of(final Player ...players) {
        final PlayersTableModel model = new PlayersTableModel();

        Arrays.stream(players).forEach(model::add);

        return model;
    }

    public PlayersTableModel add(final Player player) {
        this.players.add(player);
        return this;
    }
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public int getRowCount() {
        return this.players.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return "Name";
            case 1:
                return "Color";
            default:
                return "Unknown";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Player player = players.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return player.getName();
            case 1:
                return player.getColor().name();
            default:
                return null;
        }
    }
}
