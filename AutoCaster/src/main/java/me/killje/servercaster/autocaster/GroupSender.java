package me.killje.servercaster.autocaster;

import java.util.ArrayList;
import me.killje.servercaster.core.ServerCaster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupSender {

    protected int lineIndex = 0;
    private final JavaPlugin instance = AutoCaster.getInstance();
    protected final String path;
    protected int totalMessages;
    protected final ArrayList<Player> players = new ArrayList<>();

    public GroupSender(String path) {
        this.path = path;
        init();
    }

    protected void init() {
        totalMessages = instance.getConfig().getStringList(path).size();
    }

    public synchronized boolean addPlayer(Player player) {
        if (!players.contains(player)) {
            return players.add(player);
        }
        return false;
    }

    public void run() {
        if (totalMessages <= lineIndex) {
            lineIndex = 0;
        }
        ArrayList<String> storedMessages = new ArrayList<>(instance.getConfig().getStringList(path));
        ServerCaster.castMessage(instance, storedMessages.get(lineIndex), players);
        lineIndex++;
    }

    public String getGroup() {
        return path;
    }

    public synchronized boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public void setLineIndex(int line) {
        lineIndex = line;
    }

    public boolean hasPlayers() {
        return !players.isEmpty();
    }

    public String getPath() {
        return path;
    }

}
