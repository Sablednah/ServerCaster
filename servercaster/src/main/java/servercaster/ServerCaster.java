/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercaster;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje), Floris Huizinga (Flexo013)
 */
public class ServerCaster extends JavaPlugin {

    private final ServercastAnnouncer anouncer = new ServercastAnnouncer(this);

    private int getIntervalInTicks() {
        int interval = getConfig().getInt("Interval");
        if (!getConfig().getBoolean("InSeconds")) {
            interval *= 60;
        }
        return 20 * interval;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        int intervalInTicks = getIntervalInTicks();
        getServer().getScheduler().scheduleSyncRepeatingTask(this, anouncer, intervalInTicks, intervalInTicks);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reloadservercast")) {
            reloadConfig();
            getServer().getScheduler().cancelTasks(this);
            int intervalInTicks = getIntervalInTicks();
            getServer().getScheduler().scheduleSyncRepeatingTask(this, anouncer, intervalInTicks, intervalInTicks);
            sender.sendMessage(ChatColor.GREEN + "Servercast messages have been reloaded");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("cast")) {
            getServer().getScheduler().cancelTasks(this);
            int intervalInTicks = getIntervalInTicks();
            int message = 0;
            if (args.length > 1) {
                return false;
            }
            if (args.length == 1) {
                message = Integer.parseInt(args[0]);
                if (message <= 0) {
                    sender.sendMessage(ChatColor.RED + "the number needs to be greater as zero");
                    return false;
                }

            }
            message--;
            getServer().getScheduler().scheduleSyncRepeatingTask(this, anouncer.getRunning(message), intervalInTicks, intervalInTicks);
            return true;
        }
        return false;
    }

}
