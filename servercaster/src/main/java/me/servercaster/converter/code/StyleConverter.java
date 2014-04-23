package me.servercaster.converter.code;

import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (killje) And Floris Huizinga(Flexo013)
 */
public class StyleConverter extends SpecialCodeConverter{

    private final String name;
    private final ChatColor style;

    public StyleConverter(String name, ChatColor style) {
        super(0);
        this.name = name;
        this.style = style;
    }

    @Override
    protected String getKeyword() {
        return name;
    }

    @Override
    public void doAction(String s) {
        fm.style(style);
    }
    
    
}
