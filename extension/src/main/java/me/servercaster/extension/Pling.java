package me.servercaster.extension;

import me.servercaster.core.converter.CodeAction;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Fleox013)
 */
public class Pling extends CodeAction{
    
    private final JavaPlugin instance;

    public Pling(JavaPlugin instance) {
        super(0);
        this.instance = instance;
    }

    @Override
    protected String getKeyword() {
        return "PLING";
    }

    @Override
    public void doAction(String string) {
        Player[] players = instance.getServer().getOnlinePlayers();
        for (Player player : players) {
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 100f, 100f);
        }
    }
}
