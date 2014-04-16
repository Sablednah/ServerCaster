package me.servercaster.converter;

import me.servercaster.BuilderPart;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class BracketConverter extends Converter {

    public BracketConverter(FancyMessage fm, BuilderPart bp) {
        super(fm, bp);
    }
    
    @Override
    protected boolean isEndChar(char c) {
        return c == '}';
    }

    @Override
    protected Converter end() {
        bp.setText(getSavedString());
        bp.getMessage(fm);
        bp.empty();
        return new TextConverter(fm, bp);
    }

}
