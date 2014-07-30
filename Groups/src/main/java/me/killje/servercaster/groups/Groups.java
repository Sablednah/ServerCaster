package me.killje.servercaster.groups;

import me.killje.servercaster.autocaster.AutoCaster;
import me.killje.servercaster.groups.groupmanager.GroupManagerHelper;
import me.killje.servercaster.groups.permissionsex.PermissionsExHelper;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Groups extends JavaPlugin {

    private static JavaPlugin instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if (getServer().getPluginManager().isPluginEnabled("GroupManager")) {
            AutoCaster.overrideDefaultCaster(new GroupManagerHelper());
        }else if(getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
            AutoCaster.overrideDefaultCaster(new PermissionsExHelper());
        }else{
            getLogger().info("No group permission systems found\n be sure to have PermissionsEx or GroupManager running");
        }
    }

    public static JavaPlugin getInstance() {
        return instance;
    }
}
