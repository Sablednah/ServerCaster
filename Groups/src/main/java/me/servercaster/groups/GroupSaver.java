package me.servercaster.groups;

import java.util.ArrayList;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public interface GroupSaver {

    public ArrayList<Player> getPlayers();

    public boolean newPlayer(Player player);

    public boolean leavePlayer(Player player);

}
