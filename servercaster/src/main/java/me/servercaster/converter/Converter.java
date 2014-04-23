package me.servercaster.converter;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class Converter {

    private String saver = "";
    protected final FancyMessage fm;

    public Converter(FancyMessage fm) {
        this.fm = fm;
    }

    protected abstract Converter end();

    protected abstract boolean isEndChar(char c);

    public Converter nextChar(char c) {
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

    public void done() {
        fm.then(saver);
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
