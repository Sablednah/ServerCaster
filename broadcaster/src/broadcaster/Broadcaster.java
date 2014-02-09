/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcaster;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (s2288842) and Floris Huizinga (s2397617)
 */
public class Broadcaster extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        int intervalInMin = getConfig().getInt("Interval");
        int intervalInTicks = 20 * 60 * intervalInMin;
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new BroadcasterAnnouncer(this), intervalInTicks, intervalInTicks);
    }

}
