package me.servercaster.autocaster;

import me.servercaster.core.ServerCaster;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AutoCaster extends JavaPlugin {

    public static JavaPlugin getInstance() {
        return instance;
    }

    private static JavaPlugin instance;
    
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        AutoCastHelper broadCastHelper = new AutoCastHelper();
        getServer().getPluginManager().registerEvents(broadCastHelper, this);
        getCommand("cast").setExecutor(broadCastHelper);
        ServerCaster.addReloadListener(this, broadCastHelper);
    }
   
}
