package me.servercaster.converter;

import me.servercaster.BuilderPart;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class Converter {

    private String saver = "";
    protected final FancyMessage fm;
    protected final BuilderPart bp;

    public Converter(FancyMessage fm, BuilderPart bp) {
        this.fm = fm;
        this.bp = bp;
    }
    
    public Converter nextChar(char c) {
        if (fm == null || bp == null) {
            throw new NullPointerException("FancyMessage or ServercastMessage not declared");
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

    protected abstract Converter end();
    protected abstract boolean isEndChar(char c);
    
    protected String getSavedString() {
        return saver;
    }

    protected void addChar(char c) {
        saver = saver + c;
    }
    
    protected void clearSavedString(){
        saver = "";
    }
}