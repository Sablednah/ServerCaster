package me.servercaster.converter;

import me.servercaster.BuilderPart;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class TextConverter extends Converter {

    public TextConverter(FancyMessage fm, BuilderPart sm) {
        super(fm, sm);
    }

    @Override
    protected boolean isEndChar(char c) {
        return c == '&';
    }

    @Override
    protected Converter end() {
        fm.then(getSavedString());
        return new CodeConverter(fm, sm);
    }

}
