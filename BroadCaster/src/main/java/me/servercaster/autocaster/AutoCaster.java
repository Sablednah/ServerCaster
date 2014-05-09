package me.servercaster.autocaster;

import me.servercaster.core.ServerCaster;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AutoCaster extends JavaPlugin {

    private static JavaPlugin instance;
    private static final AutoCastHelper ach = new AutoCastHelper();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(ach, this);
        getCommand("cast").setExecutor(ach);
        getCommand("startAutoCaster").setExecutor(ach);
        getCommand("stopAutoCaster").setExecutor(ach);
        ServerCaster.addReloadListener(this, ach);
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static void startAutoCaster(boolean reset) {
        ach.start(reset);
    }

    public static void stopAutoCaster() {
        ach.stop();
    }

}
