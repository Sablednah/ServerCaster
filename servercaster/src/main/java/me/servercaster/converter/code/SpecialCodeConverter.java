package me.servercaster.converter.code;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class SpecialCodeConverter {

    protected FancyMessage fm;
    private final int arguments;
    private int argumentsLeft;

    public SpecialCodeConverter(int arguments) {
        this.arguments = arguments;
        this.argumentsLeft = arguments;
    }

    protected abstract String getKeyword();

    public abstract void doAction(String s);

    public String getCode() {
        return getKeyword().toLowerCase();
    }

    public boolean hasArgumentsLeft() {
        return argumentsLeft != 0;
    }

    public boolean isEnd(String s) {
        doAction(s);
        argumentsLeft--;
        if (!hasArgumentsLeft()) {
            argumentsLeft = arguments;
            return true;
        } else {
            return false;
        }
    }

    public void addBuilders(FancyMessage fm) {
        this.fm = fm;
    }

    public boolean isEndChar(char c) {
        return c == '}';
    }
}
