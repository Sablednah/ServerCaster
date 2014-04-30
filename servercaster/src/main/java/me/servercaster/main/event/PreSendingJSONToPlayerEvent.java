package me.servercaster.main.event;

import java.util.ArrayList;
import java.util.EventObject;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PreSendingJSONToPlayerEvent extends EventObject{
    
    private ArrayList<String> messages;
    private final Player player;

    public PreSendingJSONToPlayerEvent(ArrayList<String> messages, Player player, Object source) {
        super(source);
        this.messages = messages;
        this.player = player;
    }
    
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    public Player getPlayer(){
        return player;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
