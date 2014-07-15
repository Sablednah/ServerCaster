package me.killje.servercaster.core.converter.action;

import java.util.Collection;
import me.killje.servercaster.core.converter.CodeAction;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CommandAction extends CodeAction {

    public CommandAction() {
        super(1);
    }

    @Override
    protected String getKeyword() {
        return "COMMAND";
    }

    @Override
    public void doAction(String argument, Collection<Player> players) {
        getJSONSaver().command("/" + argument);
    }
}
