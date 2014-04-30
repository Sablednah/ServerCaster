package me.servercaster.main.converter.code;

import me.servercaster.main.converter.SpecialCodeConverter;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (killje) And Floris Huizinga(Flexo013)
 */
public class ColorConverter extends SpecialCodeConverter{

    private final String name;
    private final ChatColor color;

    public ColorConverter(String name, ChatColor color) {
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
