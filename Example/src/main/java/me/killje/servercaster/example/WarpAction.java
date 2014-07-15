package me.killje.servercaster.example;

import java.util.Collection;
import me.killje.servercaster.core.converter.CodeAction;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class WarpAction extends CodeAction {

    public WarpAction() {
        super(1); //amount of arguments this converter needs
    }

    @Override
    protected String getKeyword() {
        return "WARP";
    }

    @Override
    public void doAction(String argument, Collection<Player> players) {
        getJSONSaver().command("/warp " + argument);
    }

}
