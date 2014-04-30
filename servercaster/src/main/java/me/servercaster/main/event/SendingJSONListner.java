package me.servercaster.main.event;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public interface SendingJSONListner {
    public void sendingPrePlayerHandler(PreSendingJSONToPlayerEvent e);
    public void sendingPreServerHandler(PreSendingJSONToServerEvent e);
}
