package me.killje.servercaster.autocaster;

import java.io.File;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class VersionUpdater {

    public VersionUpdater(JavaPlugin instance) {
        FileConfiguration fconf = instance.getConfig();
        List<String> messages = fconf.getStringList("Messages");
        int interval = fconf.getInt("Interval");
        boolean inSec = fconf.getBoolean("InSeconds");
        boolean debug = fconf.getBoolean("Debug");
        File configFile = new File(instance.getDataFolder(), "config.yml");
        configFile.delete();
        instance.saveDefaultConfig();
        instance.reloadConfig();
        fconf = instance.getConfig();
        fconf.set("Interval", interval);
        fconf.set("InSeconds", inSec);
        fconf.set("Debug", debug);
        fconf.set("Messages", messages);
        instance.saveConfig();
    }
}
