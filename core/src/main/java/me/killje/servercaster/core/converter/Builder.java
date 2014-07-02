package me.killje.servercaster.core.converter;

import java.util.Collection;
import mkremins.fanciful.FancyMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Builder {

    private Converter converter;
    private final Collection<? extends Player> players;

    public Builder(Collection<? extends Player> players) {
        this.players = players;
    }

    public String getProperMessage(String message) {
        FancyMessage fm = new FancyMessage();
        converter = new TextConverter(fm, players);
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            converter = converter.nextChar(currentChar);
        }
        converter.done();
        return fm.toJSONString();
    }
}
