package me.servercaster;

import java.util.ArrayList;
import java.util.Arrays;
import me.servercaster.converter.CodeConverter;
import me.servercaster.converter.code.ColorConverter;
import me.servercaster.converter.code.CommandConverter;
import me.servercaster.converter.code.SpecialCodeConverter;
import me.servercaster.converter.code.StyleConverter;
import me.servercaster.converter.code.SuggestConverter;
import me.servercaster.converter.code.UrlConverter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
        addConverter(new CommandConverter());
        addConverter(new UrlConverter());
        addConverter(new SuggestConverter());
        addConverter(new ColorConverter("AQUA", ChatColor.AQUA));
        addConverter(new ColorConverter("BLACK", ChatColor.BLACK));
        addConverter(new ColorConverter("BLUE", ChatColor.BLUE));
        addConverter(new ColorConverter("DARK_AQUA", ChatColor.DARK_AQUA));
        addConverter(new ColorConverter("DARK_BLUE", ChatColor.DARK_BLUE));
        addConverter(new ColorConverter("DARK_GRAY", ChatColor.DARK_GRAY));
        addConverter(new ColorConverter("DARK_GREEN", ChatColor.DARK_GREEN));
        addConverter(new ColorConverter("DARK_PURPLE", ChatColor.DARK_PURPLE));
        addConverter(new ColorConverter("DARK_RED", ChatColor.DARK_RED));
        addConverter(new ColorConverter("GOLD", ChatColor.GOLD));
        addConverter(new ColorConverter("GRAY", ChatColor.GRAY));
        addConverter(new ColorConverter("GREEN", ChatColor.GREEN));
        addConverter(new ColorConverter("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE));
        addConverter(new ColorConverter("RED", ChatColor.RED));
        addConverter(new ColorConverter("WHITE", ChatColor.WHITE));
        addConverter(new ColorConverter("YELLOW", ChatColor.YELLOW));

        addConverter(new ColorConverter("0", ChatColor.BLACK));
        addConverter(new ColorConverter("1", ChatColor.DARK_BLUE));
        addConverter(new ColorConverter("2", ChatColor.DARK_GREEN));
        addConverter(new ColorConverter("3", ChatColor.DARK_AQUA));
        addConverter(new ColorConverter("4", ChatColor.DARK_RED));
        addConverter(new ColorConverter("5", ChatColor.DARK_PURPLE));
        addConverter(new ColorConverter("6", ChatColor.GOLD));
        addConverter(new ColorConverter("7", ChatColor.GRAY));
        addConverter(new ColorConverter("8", ChatColor.DARK_GRAY));
        addConverter(new ColorConverter("9", ChatColor.BLUE));
        addConverter(new ColorConverter("a", ChatColor.GREEN));
        addConverter(new ColorConverter("b", ChatColor.AQUA));
        addConverter(new ColorConverter("c", ChatColor.RED));
        addConverter(new ColorConverter("d", ChatColor.LIGHT_PURPLE));
        addConverter(new ColorConverter("e", ChatColor.YELLOW));
        addConverter(new ColorConverter("f", ChatColor.WHITE));

        addConverter(new StyleConverter("MAGIC", ChatColor.MAGIC));
        addConverter(new StyleConverter("BOLD", ChatColor.BOLD));
        addConverter(new StyleConverter("STRIKE", ChatColor.STRIKETHROUGH));
        addConverter(new StyleConverter("UNDERLINE", ChatColor.UNDERLINE));
        addConverter(new StyleConverter("ITALIC", ChatColor.ITALIC));

        addConverter(new StyleConverter("k", ChatColor.MAGIC));
        addConverter(new StyleConverter("l", ChatColor.BOLD));
        addConverter(new StyleConverter("m", ChatColor.STRIKETHROUGH));
        addConverter(new StyleConverter("n", ChatColor.UNDERLINE));
        addConverter(new StyleConverter("o", ChatColor.ITALIC));
        getServer().getPluginManager().registerEvents(anouncer, this);
    }
    
    void addConverter(SpecialCodeConverter scc){
        CodeConverter.addSpecialCode(scc);
    }

    Caster getCaster() {
        return anouncer;
    }

    public static void castMessage(JavaPlugin plugin, String message) {
        ServerCaster.castMessage(plugin, message, "", plugin.getServer().getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix) {
        ServerCaster.castMessage(plugin, message, prefix, plugin.getServer().getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, Player[] players) {
        ServerCaster.castMessage(plugin, message, "", players);
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix, Player[] players) {
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        Caster caster = parent.getCaster();
        ArrayList<Player> lPlayers = new ArrayList<>();
        lPlayers.addAll(Arrays.asList(players));
        caster.sendMessage(Caster.ToJsonString(prefix, message), lPlayers);
    }

    public static void addSpecialCode(JavaPlugin plugin, SpecialCodeConverter scc){
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        parent.addConverter(scc);
    }
}
