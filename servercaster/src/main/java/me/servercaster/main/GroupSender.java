package me.servercaster.main;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupSender {

    private int lineIndex = 0;
    private final JavaPlugin instance = ServerCaster.getInstance();
    private final String path;
    private final int totalMessages;
    private final ArrayList<Player> players = new ArrayList<>();
    private final Caster caster;

    public GroupSender(String path, Caster caster) {
        totalMessages = instance.getConfig().getStringList(path).size();
        this.path = path;
        this.caster = caster;
    }

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    public void run() {
        if (totalMessages <= lineIndex) {
            lineIndex = 0;
        }
        List<String> storedMessages = instance.getConfig().getStringList(path);
        String prefix = instance.getConfig().getString("Prefix");
        ArrayList<String> JSONStrings = Caster.ToJsonString(prefix, storedMessages.get(lineIndex));
        if (instance.getConfig().getBoolean("Debug")) {
            for (String string : JSONStrings) {
                instance.getLogger().info(string);
            }
        }
        caster.sendMessage(JSONStrings, players);
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
