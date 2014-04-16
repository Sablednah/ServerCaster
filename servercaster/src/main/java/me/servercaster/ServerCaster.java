package me.servercaster;

import me.servercaster.converter.CodeConverter;
import me.servercaster.converter.code.CommandConverter;
import me.servercaster.converter.code.UrlConverter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServerCaster extends JavaPlugin {

    private Caster anouncer;
    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        anouncer = new Caster();
        getCommand("cast").setExecutor(anouncer);
        getCommand("reloadservercaster").setExecutor(anouncer);
        CodeConverter.addSpecialCode(new CommandConverter());
        CodeConverter.addSpecialCode(new UrlConverter());
        getServer().getPluginManager().registerEvents(anouncer, this);
    }

}
