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
    protected final int totalMessages;
    protected final ArrayList<Player> players = new ArrayList<>();

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
        ServerCaster.castMessage(instance, storedMessages.get(lineIndex), players);
        lineIndex++;
    }

    public String getGroup() {
        return path;
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public void setLineIndex(int line) {
        lineIndex = line;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroupSender) {
            return super.equals(obj);
        } else if (obj instanceof String) {
            if (((String) obj).equals(path)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

}
