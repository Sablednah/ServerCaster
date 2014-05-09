package me.servercaster.core.converter;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
abstract class Converter {

    private String saver = "";
    protected final FancyMessage fm;

    Converter(FancyMessage fm) {
        this.fm = fm;
    }

    abstract Converter end();

    abstract boolean isEndChar(char c);

    Converter nextChar(char c) {
        if (fm == null) {
            throw new NullPointerException("FancyMessage not declared");
        }
        if (isEndChar(c)) {
            return end();
        } else {
            addChar(c);
            return this;
        }
    }

    void done() {
        fm.text(saver);
    }

    protected String getSavedString() {
        return saver;
    }

    protected void addChar(char c) {
        saver = saver + c;
    }

    protected void clearSavedString() {
        saver = "";
    }
}
