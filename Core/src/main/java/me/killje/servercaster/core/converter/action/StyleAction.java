package me.killje.servercaster.core.converter.action;

import me.killje.servercaster.core.converter.CodeAction;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class StyleAction extends CodeAction {

    private final String name;
    private final ChatColor style;

    public StyleAction(String name, ChatColor style) {
        super(0);
        this.name = name;
        this.style = style;
    }

    @Override
    protected String getKeyword() {
        return name;
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().style(style);
    }

}
