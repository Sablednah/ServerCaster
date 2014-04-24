package me.servercaster.main;

import java.util.ArrayList;
import java.util.Arrays;
import me.servercaster.main.converter.CodeConverter;
import me.servercaster.main.converter.code.ColorConverter;
import me.servercaster.main.converter.code.CommandConverter;
import me.servercaster.main.converter.code.SpecialCodeConverter;
import me.servercaster.main.converter.code.StyleConverter;
import me.servercaster.main.converter.code.SuggestConverter;
import me.servercaster.main.converter.code.UrlConverter;
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
        addSpecialConverter(new CommandConverter());
        addSpecialConverter(new UrlConverter());
        addSpecialConverter(new SuggestConverter());
        addSpecialConverter(new ColorConverter("AQUA", ChatColor.AQUA));
        addSpecialConverter(new ColorConverter("BLACK", ChatColor.BLACK));
        addSpecialConverter(new ColorConverter("BLUE", ChatColor.BLUE));
        addSpecialConverter(new ColorConverter("DARK_AQUA", ChatColor.DARK_AQUA));
        addSpecialConverter(new ColorConverter("DARK_BLUE", ChatColor.DARK_BLUE));
        addSpecialConverter(new ColorConverter("DARK_GRAY", ChatColor.DARK_GRAY));
        addSpecialConverter(new ColorConverter("DARK_GREEN", ChatColor.DARK_GREEN));
        addSpecialConverter(new ColorConverter("DARK_PURPLE", ChatColor.DARK_PURPLE));
        addSpecialConverter(new ColorConverter("DARK_RED", ChatColor.DARK_RED));
        addSpecialConverter(new ColorConverter("GOLD", ChatColor.GOLD));
        addSpecialConverter(new ColorConverter("GRAY", ChatColor.GRAY));
        addSpecialConverter(new ColorConverter("GREEN", ChatColor.GREEN));
        addSpecialConverter(new ColorConverter("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE));
        addSpecialConverter(new ColorConverter("RED", ChatColor.RED));
        addSpecialConverter(new ColorConverter("WHITE", ChatColor.WHITE));
        addSpecialConverter(new ColorConverter("YELLOW", ChatColor.YELLOW));

        addSpecialConverter(new ColorConverter("0", ChatColor.BLACK));
        addSpecialConverter(new ColorConverter("1", ChatColor.DARK_BLUE));
        addSpecialConverter(new ColorConverter("2", ChatColor.DARK_GREEN));
        addSpecialConverter(new ColorConverter("3", ChatColor.DARK_AQUA));
        addSpecialConverter(new ColorConverter("4", ChatColor.DARK_RED));
        addSpecialConverter(new ColorConverter("5", ChatColor.DARK_PURPLE));
        addSpecialConverter(new ColorConverter("6", ChatColor.GOLD));
        addSpecialConverter(new ColorConverter("7", ChatColor.GRAY));
        addSpecialConverter(new ColorConverter("8", ChatColor.DARK_GRAY));
        addSpecialConverter(new ColorConverter("9", ChatColor.BLUE));
        addSpecialConverter(new ColorConverter("a", ChatColor.GREEN));
        addSpecialConverter(new ColorConverter("b", ChatColor.AQUA));
        addSpecialConverter(new ColorConverter("c", ChatColor.RED));
        addSpecialConverter(new ColorConverter("d", ChatColor.LIGHT_PURPLE));
        addSpecialConverter(new ColorConverter("e", ChatColor.YELLOW));
        addSpecialConverter(new ColorConverter("f", ChatColor.WHITE));

        addSpecialConverter(new StyleConverter("MAGIC", ChatColor.MAGIC));
        addSpecialConverter(new StyleConverter("BOLD", ChatColor.BOLD));
        addSpecialConverter(new StyleConverter("STRIKE", ChatColor.STRIKETHROUGH));
        addSpecialConverter(new StyleConverter("UNDERLINE", ChatColor.UNDERLINE));
        addSpecialConverter(new StyleConverter("ITALIC", ChatColor.ITALIC));

        addSpecialConverter(new StyleConverter("k", ChatColor.MAGIC));
        addSpecialConverter(new StyleConverter("l", ChatColor.BOLD));
        addSpecialConverter(new StyleConverter("m", ChatColor.STRIKETHROUGH));
        addSpecialConverter(new StyleConverter("n", ChatColor.UNDERLINE));
        addSpecialConverter(new StyleConverter("o", ChatColor.ITALIC));
        getServer().getPluginManager().registerEvents(anouncer, this);
    }

    void addSpecialConverter(SpecialCodeConverter scc) {
        CodeConverter.addSpecialCode(scc);
    }

    Caster getCaster() {
        return anouncer;
    }

    public static void castMessage(JavaPlugin plugin, String message) {
        ServerCaster.castMessage(plugin, message, plugin.getServer().getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix) {
        ServerCaster.castMessage(plugin, message, prefix, plugin.getServer().getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, Player[] players) {
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        ServerCaster.castMessage(plugin, message, parent.getConfig().getString("Prefix"), players);
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix, Player[] players) {
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        Caster caster = parent.getCaster();
        ArrayList<Player> lPlayers = new ArrayList<>();
        lPlayers.addAll(Arrays.asList(players));
        caster.sendMessage(Caster.ToJsonString(prefix, message), lPlayers);
    }

    public static void addConverter(JavaPlugin plugin, SpecialCodeConverter scc) {
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        parent.addSpecialConverter(scc);
    }
}
