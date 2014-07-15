package me.killje.servercaster.core.converter.action;

import java.util.Collection;
import me.killje.servercaster.core.converter.CodeAction;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class SuggestAction extends CodeAction {

    public SuggestAction() {
        super(1);
    }

    @Override
    protected String getKeyword() {
        return "SUGGEST";
    }

    @Override
    public void doAction(String argument, Collection<Player> players) {
        getJSONSaver().suggest(argument);
    }
}
