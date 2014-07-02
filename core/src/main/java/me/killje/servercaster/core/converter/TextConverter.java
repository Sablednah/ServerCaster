package me.killje.servercaster.core.converter;

import java.util.Collection;
import mkremins.fanciful.FancyMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class TextConverter extends Converter {
    
    private final Collection<? extends Player> players;

    TextConverter(FancyMessage fm, Collection<? extends Player> players) {
        super(fm);
        this.players = players;
    }

    @Override
    boolean isEndChar(char c) {
        return c == '&';
    }

    @Override
    Converter end() {
        fm.text(getSavedString());
        fm.then();
        return new CodeConverter(fm, players);
    }

}
