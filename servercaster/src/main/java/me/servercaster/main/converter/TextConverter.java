package me.servercaster.main.converter;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class TextConverter extends Converter {

    public TextConverter(FancyMessage fm) {
        super(fm);
    }

    @Override
    protected boolean isEndChar(char c) {
        return c == '&';
    }

    @Override
    protected Converter end() {
        fm.text(getSavedString());
        fm.then();
        return new CodeConverter(fm);
    }

}
