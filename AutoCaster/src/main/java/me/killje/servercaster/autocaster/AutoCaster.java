package me.killje.servercaster.autocaster;

import java.lang.reflect.Field;
import java.util.logging.Level;
import me.killje.servercaster.core.ServerCaster;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AutoCaster extends JavaPlugin {

    private static JavaPlugin instance;
    private static AutoCastHelper ach;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        if (!getConfig().isSet("Version") || !getConfig().getString("Version").equals("2.0")) {
            new VersionUpdater(this);
        }
        ach = new AutoCastHelper();
        ach.init();
        getServer().getPluginManager().registerEvents(ach, this);
        getCommand("cast").setExecutor(ach);
        getCommand("startAutoCaster").setExecutor(ach);
        getCommand("stopAutoCaster").setExecutor(ach);
        ServerCaster.addReloadListener(ach);
        ach.start(false);
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

    public static void overrideDefaultCaster(AutoCastHelper ach) {
        instance.getServer().getScheduler().cancelTasks(instance);
        HandlerList.unregisterAll(AutoCaster.ach);
        test();
        getInstance().getServer().getPluginManager().registerEvents(ach, instance);
        instance.getCommand("cast").setExecutor(ach);
        instance.getCommand("startAutoCaster").setExecutor(ach);
        instance.getCommand("stopAutoCaster").setExecutor(ach);
        AutoCaster.ach = ach;
        ach.start(false);
    }

    public static void test() {
        try {
            CommandMap cm;
            SimplePluginManager spm = (SimplePluginManager) Bukkit.getServer().getPluginManager();
            Field f = spm.getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            cm = (CommandMap) f.get(spm);
            Bukkit.getPluginCommand("cast").unregister(cm);
            Bukkit.getPluginCommand("startAutoCaster").unregister(cm);
            Bukkit.getPluginCommand("stopAutoCaster").unregister(cm);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            instance.getLogger().log(Level.WARNING, e.getMessage(), e);
        }
    }
}
