package me.killje.servercaster.example;

import me.killje.servercaster.core.converter.CodeConverter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class AddWarp {

    public AddWarp(JavaPlugin instance) {
        CodeConverter.addCodeAction(new WarpAction());
    }
}
