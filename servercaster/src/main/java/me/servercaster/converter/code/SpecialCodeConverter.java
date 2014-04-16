package me.servercaster.converter.code;

import me.servercaster.BuilderPart;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class SpecialCodeConverter {

    protected FancyMessage fm;
    protected BuilderPart bp;

    public abstract String getCode();

    public abstract void doAction(String s);

    protected abstract int getArgumentsLeft();

    public boolean isEnd(String s) {
        doAction(s);
        return getArgumentsLeft() == 0;
    }

    public void addBuilders(FancyMessage fm, BuilderPart bp) {
        this.fm = fm;
        this.bp = bp;
    }

    public boolean isEndChar(char c) {
        return c == '}';
    }
}
