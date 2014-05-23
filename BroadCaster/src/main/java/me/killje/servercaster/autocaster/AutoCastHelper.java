package me.killje.servercaster.autocaster;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import me.killje.servercaster.core.event.CastReloadListener;
import me.killje.servercaster.core.event.ReloadEvent;
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
public class AutoCastHelper implements Listener, Runnable, CommandExecutor, CastReloadListener {

    private final JavaPlugin instance = AutoCaster.getInstance();
    private final List<GroupSender> senders = new LinkedList<>();
    private boolean firstRun = true;

    public AutoCastHelper() {
        init();
    }

    private void init() {
        if (instance.getConfig().getBoolean("UseGroups")) {
            Set<String> groups = instance.getConfig().getConfigurationSection("Messages").getKeys(false);
            for (String string : groups) {
                senders.add(new GroupSender("Messages." + string));
            }
        } else {
            senders.add(new GroupSender("Messages"));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void loginEvent(PlayerJoinEvent e) {
        if (firstRun) {
            return;
        }
        addPlayer(e.getPlayer());
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
        for (GroupSender groupSender : senders) {
            if (player.hasPermission("ServerCaster." + groupSender.getGroup())) {
                groupSender.addPlayer(player);
            }
        }
    }

    private void removePlayer(Player player) {
        for (GroupSender groupSender : senders) {
            groupSender.removePlayer(player);
        }
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
    public void castReloadHandler(ReloadEvent e) {
        stop();
        firstRun = true;
        senders.clear();
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cast")) {
            if (args.length > 0) {
                return false;
            }
            stop();
            run();
            sender.sendMessage(ChatColor.GREEN + "All messages have been send");
            start(false);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("startAutoCaster")) {
            if (args.length > 1) {
                return false;
            }
            boolean reset = false;
            if (args.length == 1) {
                reset = Boolean.parseBoolean(args[0]);
            }
            start(reset);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("stopAutoCaster")) {
            if (args.length > 0) {
                return false;
            }
            stop();
            return true;
        }
        return false;
    }

    void start(boolean reset) {
        if (reset) {
            for (GroupSender groupSender : senders) {
                groupSender.setLineIndex(0);
            }
        }
        addToScheduler();
    }

    void stop() {
        instance.getServer().getScheduler().cancelTasks(instance);
    }

}
