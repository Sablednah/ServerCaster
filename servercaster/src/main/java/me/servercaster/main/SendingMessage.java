package me.servercaster.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.servercaster.main.event.PreSendingJSONToPlayerEvent;
import me.servercaster.main.event.PreSendingJSONToServerEvent;
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
    private ArrayList<String> old;

    public synchronized void addEventListener(SendingJSONListner listener) {
        _listeners.add(listener);
    }

    public synchronized void removeEventListener(SendingJSONListner listener) {
        _listeners.remove(listener);
    }

    public void sendMessages(ArrayList<Player> players, ArrayList<String> messages) {
        this.messages = messages;
        this.old = messages;
        firePreServer(players);
        for (Player player : players) {
            firePrePlayer(player);
            for (String string : this.messages) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + string);
            }
            this.messages = this.old;
        }
    }

    private synchronized void firePrePlayer(Player player) {
        PreSendingJSONToPlayerEvent event = new PreSendingJSONToPlayerEvent(messages, player, this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((SendingJSONListner) i.next()).sendingPrePlayerHandler(event);
        }
        this.messages = event.getMessages();
    }

    private synchronized void firePreServer(ArrayList<Player> players) {
        PreSendingJSONToServerEvent event = new PreSendingJSONToServerEvent(messages, players, this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((SendingJSONListner) i.next()).sendingPreServerHandler(event);
        }
        this.messages = event.getMessages();
    }
}
