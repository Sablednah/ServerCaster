package me.killje.servercaster.groups;

import java.util.ArrayList;
import java.util.logging.Level;
import me.killje.servercaster.autocaster.GroupSender;
import me.killje.servercaster.core.ServerCaster;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupsGroupSender extends GroupSender {

    public GroupsGroupSender(String path) {
        super(path);
    }

    @Override
    protected void init() {
        totalMessages = Groups.getInstance().getConfig().getStringList(path).size();
    }

    @Override
    public void run() {
        if (totalMessages <= lineIndex) {
            lineIndex = 0;
        }
        ArrayList<String> storedMessages = new ArrayList<>(Groups.getInstance().getConfig().getStringList(path));
        if (storedMessages.isEmpty()) {
            Groups.getInstance().getLogger().log(Level.INFO, "{0} contains no messages", path);
            return;
        }
        ServerCaster.castMessage(storedMessages.get(lineIndex), players);
        lineIndex++;
    }

}
