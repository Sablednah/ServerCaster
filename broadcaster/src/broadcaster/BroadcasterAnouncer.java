package broadcaster;

import java.util.List;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcasterAnouncer implements Runnable {

    private int lineIndex = 0;
    private final Broadcaster instance;
    private final BroadcastConverter converter = new BroadcastConverter();

    BroadcasterAnouncer(Broadcaster aThis) {
        instance = aThis;
        converter.populateList();
    }

    @Override
    public void run() {
        List<String> messages = instance.getConfig().getStringList("Messages");
        if (messages.size() == lineIndex) {
            lineIndex = 0;
        }
        String[] properMessages = converter.getProperMessage(messages.get(lineIndex));
        String prefix = instance.getConfig().getString("Prefix");
        if (!prefix.equals("")) {
            prefix = prefix + ": ";
        }
        for (String message : properMessages) {
            instance.getServer().broadcastMessage(prefix + message);
        }
        lineIndex++;
    }
}
