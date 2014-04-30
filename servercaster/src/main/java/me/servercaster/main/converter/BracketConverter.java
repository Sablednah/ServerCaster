package me.servercaster.main.converter;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class BracketConverter extends Converter {

    public BracketConverter(FancyMessage fm) {
        super(fm);
    }

    @Override
    boolean isEndChar(char c) {
        return c == '}';
    }

    @Override
    Converter end() {
        fm.text(getSavedString());
        fm.then();
        return new TextConverter(fm);
    }

}
