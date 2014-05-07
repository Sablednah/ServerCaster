package me.servercaster.broadcaster;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class BroadCaster extends JavaPlugin {

    public static JavaPlugin getInstance() {
        return instance;
    }

    private static JavaPlugin instance;
    
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        BroadCastHelper broadCastHelper = new BroadCastHelper();
        getServer().getPluginManager().registerEvents(broadCastHelper, this);
        getCommand("cast").setExecutor(broadCastHelper);
    }
   
}
