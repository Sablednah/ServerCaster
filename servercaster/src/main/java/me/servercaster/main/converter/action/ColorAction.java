package me.servercaster.main.converter.action;

import me.servercaster.main.converter.CodeAction;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (killje) And Floris Huizinga(Flexo013)
 */
public class ColorAction extends CodeAction{

    private final String name;
    private final ChatColor color;

    public ColorAction(String name, ChatColor color) {
        super(0);
        this.name = name;
        this.color = color;
    }

    @Override
    protected String getKeyword() {
        return name;
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().color(color);
    }
    
    
}
