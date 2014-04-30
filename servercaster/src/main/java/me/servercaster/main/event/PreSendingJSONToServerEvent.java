package me.servercaster.main.event;

import java.util.ArrayList;
import java.util.EventObject;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PreSendingJSONToServerEvent extends EventObject{
    
    private ArrayList<String> messages;
    private final ArrayList<Player> players;

    public PreSendingJSONToServerEvent(ArrayList<String> messages, ArrayList<Player> players, Object source) {
        super(source);
        this.messages = messages;
        this.players = players;
    }
    
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
