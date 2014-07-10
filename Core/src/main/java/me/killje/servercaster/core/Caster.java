package me.killje.servercaster.core;

import me.killje.servercaster.core.converter.Builder;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class Caster implements CommandExecutor {

    private final JavaPlugin instance = ServerCaster.getInstance();
    private final MessageHandler sendingMessageHandler;
    private final ReloadHandler reloadHandler;

    Caster(MessageHandler sendingMessageHandler, ReloadHandler reloadHandler) {
        this.sendingMessageHandler = sendingMessageHandler;
        this.reloadHandler = reloadHandler;
        this.reloadHandler.fireEvent(instance.getServer().getConsoleSender());
    }

    void sendMessage(ArrayList<String> message, Collection<? extends Player> players) {
        sendingMessageHandler.sendMessages(players, message);
    }

    private void reset(CommandSender sender) {
        instance.saveDefaultConfig();
        instance.reloadConfig();
        instance.getServer().getScheduler().cancelTasks(instance);
        this.reloadHandler.fireEvent(sender);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reloadservercaster")) {
            if (args.length > 0) {
                return false;
            }
            reset(sender);
            sender.sendMessage(ChatColor.GREEN + "ServerCaster messages have been reloaded");
            return true;
        }
        return false;
    }

    static ArrayList<String> ToJsonString(String prefix, String message, Collection<? extends Player> players) {
        Builder builder = new Builder(players);
        if (!prefix.equals("")) {
            prefix = prefix + " ";
        }
        ArrayList<String> newMessage = new ArrayList<>();
        for (String string : message.split("(?i)&NEWLINE;")) {
            String properMessages = builder.getProperMessage(prefix + string);
            newMessage.add(properMessages);
        }
        return newMessage;
    }
}
