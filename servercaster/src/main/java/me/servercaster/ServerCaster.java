package me.servercaster;

import me.servercaster.converter.CodeConverter;
import me.servercaster.converter.code.ColorConverter;
import me.servercaster.converter.code.CommandConverter;
import me.servercaster.converter.code.StyleConverter;
import me.servercaster.converter.code.SuggestConverter;
import me.servercaster.converter.code.UrlConverter;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServerCaster extends JavaPlugin {

    private Caster anouncer;
    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        instance.saveDefaultConfig();
        anouncer = new Caster();
        getCommand("cast").setExecutor(anouncer);
        getCommand("reloadservercaster").setExecutor(anouncer);
        CodeConverter.addSpecialCode(new CommandConverter());
        CodeConverter.addSpecialCode(new UrlConverter());
        CodeConverter.addSpecialCode(new SuggestConverter());
        CodeConverter.addSpecialCode(new ColorConverter("AQUA", ChatColor.AQUA));
        CodeConverter.addSpecialCode(new ColorConverter("BLACK", ChatColor.BLACK));
        CodeConverter.addSpecialCode(new ColorConverter("BLUE", ChatColor.BLUE));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_AQUA", ChatColor.DARK_AQUA));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_BLUE", ChatColor.DARK_BLUE));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_GRAY", ChatColor.DARK_GRAY));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_GREEN", ChatColor.DARK_GREEN));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_PURPLE", ChatColor.DARK_PURPLE));
        CodeConverter.addSpecialCode(new ColorConverter("DARK_RED", ChatColor.DARK_RED));
        CodeConverter.addSpecialCode(new ColorConverter("GOLD", ChatColor.GOLD));
        CodeConverter.addSpecialCode(new ColorConverter("GRAY", ChatColor.GRAY));
        CodeConverter.addSpecialCode(new ColorConverter("GREEN", ChatColor.GREEN));
        CodeConverter.addSpecialCode(new ColorConverter("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE));
        CodeConverter.addSpecialCode(new ColorConverter("RED", ChatColor.RED));
        CodeConverter.addSpecialCode(new ColorConverter("WHITE", ChatColor.WHITE));
        CodeConverter.addSpecialCode(new ColorConverter("YELLOW", ChatColor.YELLOW));

        CodeConverter.addSpecialCode(new ColorConverter("0", ChatColor.BLACK));
        CodeConverter.addSpecialCode(new ColorConverter("1", ChatColor.DARK_BLUE));
        CodeConverter.addSpecialCode(new ColorConverter("2", ChatColor.DARK_GREEN));
        CodeConverter.addSpecialCode(new ColorConverter("3", ChatColor.DARK_AQUA));
        CodeConverter.addSpecialCode(new ColorConverter("4", ChatColor.DARK_RED));
        CodeConverter.addSpecialCode(new ColorConverter("5", ChatColor.DARK_PURPLE));
        CodeConverter.addSpecialCode(new ColorConverter("6", ChatColor.GOLD));
        CodeConverter.addSpecialCode(new ColorConverter("7", ChatColor.GRAY));
        CodeConverter.addSpecialCode(new ColorConverter("8", ChatColor.DARK_GRAY));
        CodeConverter.addSpecialCode(new ColorConverter("9", ChatColor.BLUE));
        CodeConverter.addSpecialCode(new ColorConverter("a", ChatColor.GREEN));
        CodeConverter.addSpecialCode(new ColorConverter("b", ChatColor.AQUA));
        CodeConverter.addSpecialCode(new ColorConverter("c", ChatColor.RED));
        CodeConverter.addSpecialCode(new ColorConverter("d", ChatColor.LIGHT_PURPLE));
        CodeConverter.addSpecialCode(new ColorConverter("e", ChatColor.YELLOW));
        CodeConverter.addSpecialCode(new ColorConverter("f", ChatColor.WHITE));

        CodeConverter.addSpecialCode(new StyleConverter("MAGIC", ChatColor.MAGIC));
        CodeConverter.addSpecialCode(new StyleConverter("BOLD", ChatColor.BOLD));
        CodeConverter.addSpecialCode(new StyleConverter("STRIKE", ChatColor.STRIKETHROUGH));
        CodeConverter.addSpecialCode(new StyleConverter("UNDERLINE", ChatColor.UNDERLINE));
        CodeConverter.addSpecialCode(new StyleConverter("ITALIC", ChatColor.ITALIC));

        CodeConverter.addSpecialCode(new StyleConverter("k", ChatColor.MAGIC));
        CodeConverter.addSpecialCode(new StyleConverter("l", ChatColor.BOLD));
        CodeConverter.addSpecialCode(new StyleConverter("m", ChatColor.STRIKETHROUGH));
        CodeConverter.addSpecialCode(new StyleConverter("n", ChatColor.UNDERLINE));
        CodeConverter.addSpecialCode(new StyleConverter("o", ChatColor.ITALIC));
        getServer().getPluginManager().registerEvents(anouncer, this);
    }

}
