package me.servercaster.autocaster;

import java.util.ArrayList;
import me.servercaster.core.ServerCaster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupSender {

    private int lineIndex = 0;
    private final JavaPlugin instance = AutoCaster.getInstance();
    private final String path;
    private final int totalMessages;
    private final ArrayList<Player> players = new ArrayList<>();

    public GroupSender(String path) {
        totalMessages = instance.getConfig().getStringList(path).size();
        this.path = path;
    }

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    public void run() {
        if (totalMessages <= lineIndex) {
            lineIndex = 0;
        }
        ArrayList<String> storedMessages = new ArrayList<>(instance.getConfig().getStringList(path));
        ServerCaster.castMessage(instance, storedMessages.get(lineIndex), players.toArray(new Player[players.size()]));
        lineIndex++;
    }

    public String getGroup() {
        return path;
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void setLineIndex(int message) {
        lineIndex = message;
    }
}
