package me.servercaster.core.converter;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class TextConverter extends Converter {

    TextConverter(FancyMessage fm) {
        super(fm);
    }

    @Override
    boolean isEndChar(char c) {
        return c == '&';
    }

    @Override
    Converter end() {
        fm.text(getSavedString());
        fm.then();
        return new CodeConverter(fm);
    }

}
