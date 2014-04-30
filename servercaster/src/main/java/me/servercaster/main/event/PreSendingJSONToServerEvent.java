package me.servercaster.main.event;

import java.util.ArrayList;
import java.util.EventObject;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PreSendingJSONToServerEvent extends EventObject {

    private ArrayList<String> messages;
    private final ArrayList<Player> players;

    /**
     *
     * @param messages The messages that are going to be send
     * @param players the players that will receive the messages
     * @param source source class of the event
     */
    public PreSendingJSONToServerEvent(ArrayList<String> messages, ArrayList<Player> players, Object source) {
        super(source);
        this.messages = messages;
        this.players = players;
    }

    /**
     *
     * @return the messages after they are converted. This event is fired once
     * every broadcast.
     */
    public ArrayList<String> getMessages() {
        return messages;
    }
    
    /**
     *
     * @return players that these messages are going to receive
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * If you have edited the messages send to the players you can set them with
     * this function again
     *
     * @param messages the new messages
     */
    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
