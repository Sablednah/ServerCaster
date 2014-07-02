package me.killje.servercaster.core.converter;

import java.util.ArrayList;
import java.util.Collection;
import mkremins.fanciful.FancyMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class BracketConverter extends Converter {

    private final ArrayList<CodeAction> emptyCodes;
    private final Collection<Player> players;
    
    BracketConverter(FancyMessage fm, ArrayList<CodeAction> emptyCodes, Collection<Player> players) {
        super(fm);
        this.emptyCodes = emptyCodes;
        this.players = players;
    }

    @Override
    boolean isEndChar(char c) {
        return c == '}';
    }

    @Override
    Converter end() {
        for (CodeAction codeAction : emptyCodes) {
            codeAction.doAction(getSavedString());
        }
        fm.text(getSavedString());
        fm.then();
        return new TextConverter(fm, players);
    }

}
