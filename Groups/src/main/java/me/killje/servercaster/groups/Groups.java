package me.killje.servercaster.groups;

import me.killje.servercaster.autocaster.AutoCaster;
import me.killje.servercaster.groups.groupmanager.GroupManagerHelper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Groups extends JavaPlugin{

    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        AutoCaster.overrideDefaultCaster(new GroupManagerHelper());
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
