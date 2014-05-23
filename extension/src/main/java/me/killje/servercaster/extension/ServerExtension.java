package me.killje.servercaster.extension;

import me.killje.servercaster.core.ServerCaster;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServerExtension extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        ServerCaster.addMessageListener(this, new Variables(this));
    }
}
