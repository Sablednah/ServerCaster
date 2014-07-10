package me.killje.servercaster.core;

import java.util.Collection;
import me.killje.servercaster.core.converter.CodeAction;
import me.killje.servercaster.core.converter.CodeConverter;
import me.killje.servercaster.core.converter.action.ColorAction;
import me.killje.servercaster.core.converter.action.CommandAction;
import me.killje.servercaster.core.converter.action.StyleAction;
import me.killje.servercaster.core.converter.action.SuggestAction;
import me.killje.servercaster.core.converter.action.UrlAction;
import me.killje.servercaster.core.event.CastListener;
import me.killje.servercaster.core.event.CastReloadListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServerCaster extends JavaPlugin {

    private Caster announcer;
    private static ServerCaster instance;
    private final MessageHandler messageHandler = new MessageHandler();
    private final ReloadHandler reloadHandler = new ReloadHandler();

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        announcer = new Caster(messageHandler, reloadHandler);
        getCommand("reloadservercaster").setExecutor(announcer);
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
    }

    void addCodeAction(CodeAction ca) {
        CodeConverter.addCodeAction(ca);
    }

    void removeCodeAction(CodeAction ca) {
        CodeConverter.removeCodeAction(ca);
    }

    MessageHandler getMessageHandler() {
        return messageHandler;
    }

    ReloadHandler getReloadHandler() {
        return reloadHandler;
    }

    Caster getCaster() {
        return announcer;
    }

    public static void castMessage(JavaPlugin plugin, String message) {
        ServerCaster.castMessage(plugin, message, (Collection<Player>) Bukkit.getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix) {
        ServerCaster.castMessage(plugin, message, prefix, (Collection<Player>) Bukkit.getOnlinePlayers());
    }

    public static void castMessage(JavaPlugin plugin, String message, Collection<Player> players) {
        ServerCaster.castMessage(plugin, message, instance.getConfig().getString("Prefix"), players);
    }

    public static void castMessage(JavaPlugin plugin, String message, String prefix, Collection<Player> players) {
        instance.getCaster().sendMessage(Caster.ToJsonString(prefix, message, players), players);
    }

    public static void addAction(JavaPlugin plugin, CodeAction ca) {
        instance.addCodeAction(ca);
    }

    public static void removeAction(JavaPlugin plugin, CodeAction ca) {
        instance.removeCodeAction(ca);
    }

    public static void addMessageListener(JavaPlugin plugin, CastListener listener) {
        instance.getMessageHandler().addEventListener(listener);
    }

    public static void removeMessageListener(JavaPlugin plugin, CastListener listener) {
        instance.getMessageHandler().removeEventListener(listener);
    }

    public static void addReloadListener(JavaPlugin plugin, CastReloadListener listener) {
        instance.getReloadHandler().addEventListener(listener);
    }

    public static void removeReloadListener(JavaPlugin plugin, CastReloadListener listener) {
        instance.getReloadHandler().removeEventListener(listener);
    }
}
