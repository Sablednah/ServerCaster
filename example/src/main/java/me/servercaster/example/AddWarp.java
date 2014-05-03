package me.servercaster.example;

import me.servercaster.core.ServerCaster;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AddWarp {

    public AddWarp(JavaPlugin instance) {
        ServerCaster.addConverter(instance, new WarpAction());
    }
}
