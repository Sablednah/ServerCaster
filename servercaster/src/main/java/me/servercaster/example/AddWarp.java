package me.servercaster.example;

import me.servercaster.main.ServerCaster;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AddWarp extends JavaPlugin{

    @Override
    public void onEnable() {
        ServerCaster.addConverter(this, new WarpConverter());
    }
    
}