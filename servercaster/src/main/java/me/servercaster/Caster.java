package me.servercaster;

import java.util.LinkedList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Caster implements Runnable, CommandExecutor, Listener {

    private final JavaPlugin instance = ServerCaster.getInstance();
    private final List<GroupSender> senders = new LinkedList<>();

    public Caster() {
        init();
    }

    private void init() {
        if (instance.getConfig().getBoolean("UseGroups")) {
            List<String> groups = instance.getConfig().getStringList("Messages");
            for (String string : groups) {
                senders.add(new GroupSender("Messages." + string));
            }
        } else {
            senders.add(new GroupSender("Messages"));
        }
        for (Player player : instance.getServer().getOnlinePlayers()) {
            addPlayer(player);
        }
        addToScheduler();
    }
    
    private void addToScheduler(){
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
        instance.reloadConfig();
        instance.getServer().getScheduler().cancelTasks(instance);
        senders.clear();
        init();
    }

    @Override
    public void run() {
        for (GroupSender groupSender : senders) {
            groupSender.run();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reloadservercast")) {
            if (args.length > 0) {
                return false;
            }
            reset();
            sender.sendMessage(ChatColor.GREEN + "Servercast messages have been reloaded");
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

    @EventHandler
    public void loginEvent(PlayerLoginEvent e) {
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
    
    private void addPlayer(Player player){
        for (GroupSender groupSender : senders) {
            if (player.hasPermission("ServerCaster." + groupSender.getGroup())) {
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

}
