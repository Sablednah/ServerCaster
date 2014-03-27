package broadcaster;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcasterAnnouncer implements Runnable {

    private int lineIndex = 0;
    private final Broadcaster instance;
    private final BroadcastConverter converter;

    BroadcasterAnnouncer(Broadcaster aThis) {
        instance = aThis;
        lineIndex = 0;
        converter = new BroadcastConverter();
    }

    @Override
    public void run() {
        List<String> messages = instance.getConfig().getStringList("Messages");
        if (messages.size() <= lineIndex) {
            lineIndex = 0;
        }
        String prefix = instance.getConfig().getString("Prefix");
        for (String string : messages.get(lineIndex).split("&NEWLINE;")) {
            String properMessages = converter.getProperMessage(string, prefix);
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
