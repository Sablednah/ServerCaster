package me.servercaster.main.event;

import java.util.ArrayList;
import java.util.EventObject;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PreSendingJSONToServerEvent extends EventObject{
    
    private ArrayList<String> messages;

    public PreSendingJSONToServerEvent(ArrayList<String> messages, Object source) {
        super(source);
        this.messages = messages;
    }
    
    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
}
