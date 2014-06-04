package me.killje.servercaster.example;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Example extends JavaPlugin{

    @Override
    public void onEnable() {
        new AddWarp(this);
        new VersionVariable(this);
    }
    
}