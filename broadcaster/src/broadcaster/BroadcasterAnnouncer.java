package broadcaster;

import java.util.List;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcasterAnnouncer implements Runnable {

    private int lineIndex = 0;
    private final Broadcaster instance;
    private final BroadcastConverter converter = new BroadcastConverter();

    BroadcasterAnnouncer(Broadcaster aThis) {
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
            prefix = prefix + " ";
        }
        String test = new FancyMessage("").then("url").link(null).toJSONString();
        for (String message : properMessages) {
            instance.getServer().broadcastMessage(prefix + message);
        }
        lineIndex++;
    }
}
