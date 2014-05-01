package me.servercaster.core;

import me.servercaster.core.converter.Builder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Caster implements Runnable, CommandExecutor, Listener {

    public void sendMessage(ArrayList<String> message, ArrayList<Player> players) {
        sendingMessageHandler.sendMessages(players, message);
    }

    private final JavaPlugin instance = ServerCaster.getInstance();
    private final List<GroupSender> senders = new LinkedList<>();
    private boolean firstRun = true;
    private final MessageHandler sendingMessageHandler;

    public Caster(MessageHandler sendingMessageHandler) {
        this.sendingMessageHandler = sendingMessageHandler;
        init();
    }

    private void init() {
        if (instance.getConfig().getBoolean("UseGroups")) {
            Set<String> groups = instance.getConfig().getConfigurationSection("Messages").getKeys(false);
            for (String string : groups) {
                senders.add(new GroupSender("Messages." + string, this));
            }
        } else {
            senders.add(new GroupSender("Messages", this));
        }
        addToScheduler();
    }

    private void addToScheduler() {
        int intervalInTicks = getIntervalInTicks();
        instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, this, intervalInTicks, intervalInTicks);
    }

    private int getIntervalInTicks() {
        int interval = instance.getConfig().getInt("Interval");
        if (!instance.getConfig().getBoolean("InSeconds")) {
            interval *= 60;
        }
        return 20 * interval;
    }

    private void reset() {
        instance.saveDefaultConfig();
        instance.reloadConfig();
        instance.getServer().getScheduler().cancelTasks(instance);
        senders.clear();
        firstRun = true;
        init();
    }

    @Override
    public void run() {
        if (firstRun) {
            firstRun = false;
            for (Player player : instance.getServer().getOnlinePlayers()) {
                addPlayer(player);
            }
        }
        for (GroupSender groupSender : senders) {
            groupSender.run();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reloadservercaster")) {
            if (args.length > 0) {
                return false;
            }
            reset();
            sender.sendMessage(ChatColor.GREEN + "ServerCaster messages have been reloaded");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("cast")) {
            if (args.length > 0) {
                return false;
            }
            instance.getServer().getScheduler().cancelTasks(instance);
            run();
            sender.sendMessage(ChatColor.GREEN + "All messages have been send");
            addToScheduler();
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void loginEvent(PlayerJoinEvent e) {
        if (firstRun) {
            return;
        }
        Player player = e.getPlayer();
        addPlayer(player);
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent e) {
        removePlayer(e.getPlayer());
    }

    @EventHandler
    public void kickEvent(PlayerKickEvent e) {
        removePlayer(e.getPlayer());
    }

    private void addPlayer(Player player) {
        instance.getLogger().info(player.getName() + " | " + player.getDisplayName());
        for (GroupSender groupSender : senders) {
            instance.getLogger().info(groupSender.getGroup());
            instance.getLogger().info(player.hasPermission("ServerCaster." + groupSender.getGroup())+ "");
            if (player.hasPermission("ServerCaster." + groupSender.getGroup())) {
                instance.getLogger().info("player joined");
                groupSender.addPlayer(player);
                return;
            }
        }
    }

    private void removePlayer(Player player) {
        for (GroupSender groupSender : senders) {
            groupSender.removePlayer(player);
        }
    }

    public static ArrayList<String> ToJsonString(String prefix, String message) {
        Builder builder = new Builder();
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
