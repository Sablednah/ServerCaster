package me.servercaster.main.event;

import java.util.ArrayList;
import java.util.EventObject;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PreSendingJSONToPlayerEvent extends EventObject {

    private ArrayList<String> messages;
    private final Player player;

    /**
     *
     * @param messages The messages that are going to be send
     * @param player the player that will receive the messages
     * @param source source class of the event
     */
    public PreSendingJSONToPlayerEvent(ArrayList<String> messages, Player player, Object source) {
        super(source);
        this.messages = messages;
        this.player = player;
    }

    /**
     *
     * @return the messages after they have been edited by
     * PreSendingJSONToServerEvent note that with every fire of this event the
     * messages are reset to the one just after PreSendingJSONToServerEvent
     */
    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     *
     * @return player that these messages are going to be send to
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * If you have edited the messages send to the player you can set them with
     * this function
     *
     * @param messages the new messages
     */
    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
