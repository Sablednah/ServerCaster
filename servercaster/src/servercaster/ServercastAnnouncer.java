package servercaster;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje), Floris Huizinga (Flexo013)
 */
public class ServercastAnnouncer implements Runnable {

    private int lineIndex = 0;
    private final ServerCaster instance;
    private final ServercastConverter converter;

    ServercastAnnouncer(ServerCaster aThis) {
        instance = aThis;
        lineIndex = 0;
        converter = new ServercastConverter();
    }

    @Override
    public void run() {
        List<String> messages = instance.getConfig().getStringList("Messages");
        if (messages.size() <= lineIndex) {
            lineIndex = 0;
        }
        String prefix = instance.getConfig().getString("Prefix");
        if (!prefix.equals("")) {
            prefix = prefix + " ";
        }
        for (String string : messages.get(lineIndex).split("&NEWLINE;")) {
            String properMessages = converter.getProperMessage(prefix + string);
            if (instance.getConfig().getBoolean("Debug")) {
                instance.getLogger().info(properMessages);
            }
            sendmessage(instance.getServer().getOnlinePlayers(), properMessages);
        }
        lineIndex++;
    }

    private void sendmessage(Player[] players, String message) {

        for (Player player : players) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getPlayerListName() + " " + message);

        }
    }

    Runnable getRunning(int message) {
        if (message == -1) {
            run();
        } else {
            lineIndex = message;
            run();
        }
        return this;
    }
}
