package me.servercaster.main.event;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public interface CastListener {

    public void castHandler(PreCastEvent e);

    public void castPlayerHandler(PreCastPlayerEvent e);

}
