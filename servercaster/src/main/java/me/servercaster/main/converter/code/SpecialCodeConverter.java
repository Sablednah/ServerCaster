package me.servercaster.main.converter.code;

import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class SpecialCodeConverter {

    private FancyMessage fm;
    private int arguments;
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

    public boolean isEnd(String argument) {
        doAction(argument);
        argumentsLeft--;
        if (!hasArgumentsLeft()) {
            argumentsLeft = arguments;
            return true;
        } else {
            return false;
        }
    }

    public void addBuilder(FancyMessage fm) {
        this.fm = fm;
    }

    public boolean isEndChar(char c) {
        return c == '}';
    }

    protected FancyMessage getJSONSaver() {
        return fm;
    }

    protected void setArguments(int arguments, boolean reset) {
        this.arguments = arguments;
        if (reset) {
            argumentsLeft = arguments;
        }
    }
}
