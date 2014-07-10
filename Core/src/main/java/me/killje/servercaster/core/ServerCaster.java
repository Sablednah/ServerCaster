package me.killje.servercaster.core;

import java.util.ArrayList;
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
        Collection<CodeAction> codeActions = new ArrayList<>();
        codeActions.add(new CommandAction());
        codeActions.add(new UrlAction());
        codeActions.add(new SuggestAction());
        codeActions.add(new ColorAction("AQUA", ChatColor.AQUA));
        codeActions.add(new ColorAction("BLACK", ChatColor.BLACK));
        codeActions.add(new ColorAction("BLUE", ChatColor.BLUE));
        codeActions.add(new ColorAction("DARK_AQUA", ChatColor.DARK_AQUA));
        codeActions.add(new ColorAction("DARK_BLUE", ChatColor.DARK_BLUE));
        codeActions.add(new ColorAction("DARK_GRAY", ChatColor.DARK_GRAY));
        codeActions.add(new ColorAction("DARK_GREEN", ChatColor.DARK_GREEN));
        codeActions.add(new ColorAction("DARK_PURPLE", ChatColor.DARK_PURPLE));
        codeActions.add(new ColorAction("DARK_RED", ChatColor.DARK_RED));
        codeActions.add(new ColorAction("GOLD", ChatColor.GOLD));
        codeActions.add(new ColorAction("GRAY", ChatColor.GRAY));
        codeActions.add(new ColorAction("GREEN", ChatColor.GREEN));
        codeActions.add(new ColorAction("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE));
        codeActions.add(new ColorAction("RED", ChatColor.RED));
        codeActions.add(new ColorAction("WHITE", ChatColor.WHITE));
        codeActions.add(new ColorAction("YELLOW", ChatColor.YELLOW));

        codeActions.add(new ColorAction("0", ChatColor.BLACK));
        codeActions.add(new ColorAction("1", ChatColor.DARK_BLUE));
        codeActions.add(new ColorAction("2", ChatColor.DARK_GREEN));
        codeActions.add(new ColorAction("3", ChatColor.DARK_AQUA));
        codeActions.add(new ColorAction("4", ChatColor.DARK_RED));
        codeActions.add(new ColorAction("5", ChatColor.DARK_PURPLE));
        codeActions.add(new ColorAction("6", ChatColor.GOLD));
        codeActions.add(new ColorAction("7", ChatColor.GRAY));
        codeActions.add(new ColorAction("8", ChatColor.DARK_GRAY));
        codeActions.add(new ColorAction("9", ChatColor.BLUE));
        codeActions.add(new ColorAction("a", ChatColor.GREEN));
        codeActions.add(new ColorAction("b", ChatColor.AQUA));
        codeActions.add(new ColorAction("c", ChatColor.RED));
        codeActions.add(new ColorAction("d", ChatColor.LIGHT_PURPLE));
        codeActions.add(new ColorAction("e", ChatColor.YELLOW));
        codeActions.add(new ColorAction("f", ChatColor.WHITE));

        codeActions.add(new StyleAction("MAGIC", ChatColor.MAGIC));
        codeActions.add(new StyleAction("BOLD", ChatColor.BOLD));
        codeActions.add(new StyleAction("STRIKE", ChatColor.STRIKETHROUGH));
        codeActions.add(new StyleAction("UNDERLINE", ChatColor.UNDERLINE));
        codeActions.add(new StyleAction("ITALIC", ChatColor.ITALIC));

        codeActions.add(new StyleAction("k", ChatColor.MAGIC));
        codeActions.add(new StyleAction("l", ChatColor.BOLD));
        codeActions.add(new StyleAction("m", ChatColor.STRIKETHROUGH));
        codeActions.add(new StyleAction("n", ChatColor.UNDERLINE));
        codeActions.add(new StyleAction("o", ChatColor.ITALIC));

        CodeConverter.addCodeActions(codeActions);
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

    /**
     *
     * converts a string to a ServerCast message.
     *
     * @param plugin The JavaPlugin creating the message.
     * @param message The message you want to convert.
     * @param prefix [optional] The string you want in front of every message
     * and after new line's. This will be converted to a ServerCast message.
     * When not specified the one inside ServerCast config is used.
     * @param players [optional] The players you want to send the message to.
     * When not specified the message will be send to all online players.
     */
    public static void castMessage(JavaPlugin plugin, String message) {
        ServerCaster.castMessage(plugin, message, Bukkit.getOnlinePlayers());
    }

    /**
     *
     * converts a string to a ServerCast message.
     *
     * @param plugin The JavaPlugin creating the message.
     * @param message The message you want to convert.
     * @param prefix [optional] The string you want in front of every message
     * and after new line's. This will be converted to a ServerCast message.
     * When not specified the one inside ServerCast config is used.
     * @param players [optional] The players you want to send the message to.
     * When not specified the message will be send to all online players.
     */
    public static void castMessage(JavaPlugin plugin, String message, String prefix) {
        ServerCaster.castMessage(plugin, message, prefix, Bukkit.getOnlinePlayers());
    }

    /**
     *
     * converts a string to a ServerCast message.
     *
     * @param plugin The JavaPlugin creating the message.
     * @param message The message you want to convert.
     * @param prefix [optional] The string you want in front of every message
     * and after new line's. This will be converted to a ServerCast message.
     * When not specified the one inside ServerCast config is used.
     * @param players [optional] The players you want to send the message to.
     * When not specified the message will be send to all online players.
     */
    public static void castMessage(JavaPlugin plugin, String message, Collection<? extends Player> players) {
        ServerCaster.castMessage(plugin, message, instance.getConfig().getString("Prefix"), players);
    }

    /**
     *
     * converts a string to a ServerCast message.
     *
     * @param plugin The JavaPlugin creating the message.
     * @param message The message you want to convert.
     * @param prefix [optional] The string you want in front of every message
     * and after new line's. This will be converted to a ServerCast message.
     * When not specified the one inside ServerCast config is used.
     * @param players [optional] The players you want to send the message to.
     * When not specified the message will be send to all online players.
     */
    public static void castMessage(JavaPlugin plugin, String message, String prefix, Collection<? extends Player> players) {
        instance.getCaster().sendMessage(Caster.ToJsonString(prefix, message, players), players);
    }

    /**
     *
     * @param listener The message listener you want to add.
     */
    public static void addMessageListener(CastListener listener) {
        instance.getMessageHandler().addEventListener(listener);
    }

    /**
     *
     * @param listener The message listener you want to remove.
     */
    public static void removeMessageListener(CastListener listener) {
        instance.getMessageHandler().removeEventListener(listener);
    }

    /**
     *
     * @param listener The reload listener you want to add.
     */
    public static void addReloadListener(CastReloadListener listener) {
        instance.getReloadHandler().addEventListener(listener);
    }

    /**
     *
     * @param listener The reload listener you want to remove.
     */
    public static void removeReloadListener(CastReloadListener listener) {
        instance.getReloadHandler().removeEventListener(listener);
    }
}
