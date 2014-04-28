package me.servercaster.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.servercaster.main.event.SendingJSONEvent;
import me.servercaster.main.event.SendingJSONListner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class SendingMessage {

    private final List _listeners = new ArrayList();
    private ArrayList<String> messages;

    public synchronized void addEventListener(SendingJSONListner listener) {
        _listeners.add(listener);
    }

    public synchronized void removeEventListener(SendingJSONListner listener) {
        _listeners.remove(listener);
    }

    public void sendMessages(ArrayList<Player> players, ArrayList<String> messages){
        this.messages = messages;
        for (Player player : players) {
            fireEvent(player);
        }
        for (Player player : players) {
            for (String string : messages) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + string);
            }
        }
    }
    //call this method whenever you want to notify
    //the event listeners of the particular event
    private synchronized void fireEvent(Player player) {
        SendingJSONEvent event = new SendingJSONEvent(messages, player, this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((SendingJSONListner) i.next()).sendingJsonHandler(event);
        }
    }
}
