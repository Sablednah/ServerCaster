package me.servercaster.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.servercaster.core.event.CastListener;
import me.servercaster.core.event.PreCastEvent;
import me.servercaster.core.event.PreCastPlayerEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617)
 */
public class Variables implements CastListener {
    
    private final JavaPlugin instance;

    public Variables(JavaPlugin instance) {
        this.instance = instance;
    }
    

    @Override
    public void castHandler(PreCastEvent e) {
        ArrayList<String> messages = e.getMessages();
        ArrayList<String> newMessages = new ArrayList<>();
        for (String string : messages) {
            Random rand = new Random();
            Player[] onlinePlayers = instance.getServer().getOnlinePlayers();
            if (string.toLowerCase().contains(("%PLING%").toLowerCase())) {
                for (Player player : e.getPlayers()) {
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 100f, 100f);
                }
                string = string.replaceAll("(?i)%PLING%", "");
            }
            if (onlinePlayers.length != 0) {
                string = string.replaceAll("(?i)%RDMPLAYER%", onlinePlayers[rand.nextInt(onlinePlayers.length)].getName());
            }
            string = string.replaceAll("(?i)%SLOTS%", instance.getServer().getMaxPlayers() + "");
            string = string.replaceAll("(?i)%PLAYERS%", onlinePlayers.length + "");
            if (string.toLowerCase().contains(("%ONLINEPLAYERS%").toLowerCase())) {
                String players = "";
                for (Player player : onlinePlayers) {
                    players += player.getDisplayName() + ", ";
                }
                if (!players.equals("")) {
                    players = players.substring(0, players.length() - 2);
                }
                string = string.replaceAll("(?i)%ONLINEPLAYERS%", players);
            }
            List<String> staff = instance.getConfig().getStringList("Staff");
            if (string.toLowerCase().contains(("%LISTALLSTAFF%").toLowerCase())) {
                String allStaff = "";
                for (String staffName : staff) {
                    allStaff += staffName + ", ";
                }
                if (!allStaff.equals("")) {
                    allStaff = allStaff.substring(0, allStaff.length() - 2);
                }
                string = string.replaceAll("(?i)%LISTALLSTAFF%", allStaff);
            }
            if (string.toLowerCase().contains(("%ONLINESTAFF%").toLowerCase())) {
                String onlineStaff = "";
                for (String staffName : staff) {
                    if (instance.getServer().getPlayer(staffName) != null && instance.getServer().getPlayer(staffName).isOnline()) {
                        onlineStaff += staffName + ", ";
                    }
                }
                if (!onlineStaff.equals("")) {
                    onlineStaff = onlineStaff.substring(0, onlineStaff.length() - 2);
                }
                string = string.replaceAll("(?i)%ONLINESTAFF%", onlineStaff);
            }
            newMessages.add(string);
        }
        e.setMessages(newMessages);
    }

    @Override
    public void castPlayerHandler(PreCastPlayerEvent e) {
        ArrayList<String> messages = e.getMessages();
        ArrayList<String> newMessages = new ArrayList<>();
        for (String string : messages) {
            string = string.replaceAll("(?i)%PLAYER%", e.getPlayer().getName());
            string = string.replaceAll("(?i)%PING%", getPing(e.getPlayer()) + "");
            newMessages.add(string);
        }
        e.setMessages(newMessages);
    }

    private int getPing(Player p) {
        return 1;
//        Class<?> cp = Reflection.getOBCClass("entity.CraftPlayer").cast(p).getClass();
//        int returnvalue = -1;
//        Object ep;
//        try {
//            ep = Reflection.getMethod(cp, "getHandler").invoke(null);
//            returnvalue = Reflection.getField(ep.getClass(), "ping").getInt(ep);
//        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            Logger.getLogger(ServerExtension.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return returnvalue;
    }
}
