package me.servercaster.core;

import java.util.ArrayList;
import java.util.Arrays;
import me.servercaster.core.converter.CodeConverter;
import me.servercaster.core.converter.action.ColorAction;
import me.servercaster.core.converter.action.CommandAction;
import me.servercaster.core.converter.CodeAction;
import me.servercaster.core.converter.action.StyleAction;
import me.servercaster.core.converter.action.SuggestAction;
import me.servercaster.core.converter.action.UrlAction;
import me.servercaster.core.event.CastListener;
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
    private final MessageHandler messageHandler = new MessageHandler();

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        instance.saveDefaultConfig();
        anouncer = new Caster(messageHandler);
        getCommand("cast").setExecutor(anouncer);
        getCommand("reloadservercaster").setExecutor(anouncer);
        addCodeAction(new CommandAction());
        addCodeAction(new UrlAction());
        addCodeAction(new SuggestAction());
        addCodeAction(new ColorAction("AQUA", ChatColor.AQUA));
        addCodeAction(new ColorAction("BLACK", ChatColor.BLACK));
        addCodeAction(new ColorAction("BLUE", ChatColor.BLUE));
        addCodeAction(new ColorAction("DARK_AQUA", ChatColor.DARK_AQUA));
        addCodeAction(new ColorAction("DARK_BLUE", ChatColor.DARK_BLUE));
        addCodeAction(new ColorAction("DARK_GRAY", ChatColor.DARK_GRAY));
        addCodeAction(new ColorAction("DARK_GREEN", ChatColor.DARK_GREEN));
        addCodeAction(new ColorAction("DARK_PURPLE", ChatColor.DARK_PURPLE));
        addCodeAction(new ColorAction("DARK_RED", ChatColor.DARK_RED));
        addCodeAction(new ColorAction("GOLD", ChatColor.GOLD));
        addCodeAction(new ColorAction("GRAY", ChatColor.GRAY));
        addCodeAction(new ColorAction("GREEN", ChatColor.GREEN));
        addCodeAction(new ColorAction("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE));
        addCodeAction(new ColorAction("RED", ChatColor.RED));
        addCodeAction(new ColorAction("WHITE", ChatColor.WHITE));
        addCodeAction(new ColorAction("YELLOW", ChatColor.YELLOW));

        addCodeAction(new ColorAction("0", ChatColor.BLACK));
        addCodeAction(new ColorAction("1", ChatColor.DARK_BLUE));
        addCodeAction(new ColorAction("2", ChatColor.DARK_GREEN));
        addCodeAction(new ColorAction("3", ChatColor.DARK_AQUA));
        addCodeAction(new ColorAction("4", ChatColor.DARK_RED));
        addCodeAction(new ColorAction("5", ChatColor.DARK_PURPLE));
        addCodeAction(new ColorAction("6", ChatColor.GOLD));
        addCodeAction(new ColorAction("7", ChatColor.GRAY));
        addCodeAction(new ColorAction("8", ChatColor.DARK_GRAY));
        addCodeAction(new ColorAction("9", ChatColor.BLUE));
        addCodeAction(new ColorAction("a", ChatColor.GREEN));
        addCodeAction(new ColorAction("b", ChatColor.AQUA));
        addCodeAction(new ColorAction("c", ChatColor.RED));
        addCodeAction(new ColorAction("d", ChatColor.LIGHT_PURPLE));
        addCodeAction(new ColorAction("e", ChatColor.YELLOW));
        addCodeAction(new ColorAction("f", ChatColor.WHITE));

        addCodeAction(new StyleAction("MAGIC", ChatColor.MAGIC));
        addCodeAction(new StyleAction("BOLD", ChatColor.BOLD));
        addCodeAction(new StyleAction("STRIKE", ChatColor.STRIKETHROUGH));
        addCodeAction(new StyleAction("UNDERLINE", ChatColor.UNDERLINE));
        addCodeAction(new StyleAction("ITALIC", ChatColor.ITALIC));

        addCodeAction(new StyleAction("k", ChatColor.MAGIC));
        addCodeAction(new StyleAction("l", ChatColor.BOLD));
        addCodeAction(new StyleAction("m", ChatColor.STRIKETHROUGH));
        addCodeAction(new StyleAction("n", ChatColor.UNDERLINE));
        addCodeAction(new StyleAction("o", ChatColor.ITALIC));
        getServer().getPluginManager().registerEvents(anouncer, this);
    }

    void addCodeAction(CodeAction scc) {
        CodeConverter.addCodeAction(scc);
    }
    
    MessageHandler getMessageHandler(){
        return messageHandler;
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

    public static void addConverter(JavaPlugin plugin, CodeAction scc) {
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        parent.addCodeAction(scc);
    }
    
    public static void addMessageListener(JavaPlugin plugin, CastListener listener){
        ServerCaster parent = (ServerCaster) plugin.getServer().getPluginManager().getPlugin("ServerCaster");
        parent.getMessageHandler().addEventListener(listener);
    }
}
