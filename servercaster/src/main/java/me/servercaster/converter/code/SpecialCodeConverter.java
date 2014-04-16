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
    private final int arguments;
    private int argumentsLeft;

    public SpecialCodeConverter(int arguments) {
        this.arguments = arguments;
        this.argumentsLeft = arguments;
    }

    public abstract String getCode();

    public abstract void doAction(String s);

    public boolean isEnd(String s) {
        doAction(s);
        argumentsLeft--;
        if (argumentsLeft == 0) {
            argumentsLeft = arguments;
            return true;
        } else {
            return false;
        }
    }

    public void addBuilders(FancyMessage fm, BuilderPart bp) {
        this.fm = fm;
        this.bp = bp;
    }

    public boolean isEndChar(char c) {
        return c == '}';
    }
}
