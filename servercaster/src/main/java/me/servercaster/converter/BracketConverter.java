package me.servercaster.converter;

import me.servercaster.ServercastMessage;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class BracketConverter extends Converter {

    public BracketConverter(FancyMessage fm, ServercastMessage sm) {
        super(fm, sm);
    }
    
    @Override
    protected boolean isEndChar(char c) {
        return c == '}';
    }

    @Override
    protected Converter end() {
        sm.setText(getSavedString());
        sm.getMessage(fm);
        sm.empty();
        return new TextConverter(fm, sm);
    }

}
