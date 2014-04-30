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
            if (!reset()) {
                argumentsLeft = arguments;
            }
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

    /**
     * with this function you can set the amount of arguments used in this
     * special code
     *
     * @param arguments how many arguments this class should have
     * @param reset if the remaining arguments should be reset to the given
     * amount
     */
    protected void setArguments(int arguments, boolean reset) {
        this.arguments = arguments;
        if (reset) {
            argumentsLeft = arguments;
        }
    }

    /**
     * when a special code has no arguments left the class wont make a new
     * instance. the number of arguments are automatically set back but if there
     * are any more steps to take before its reset so that the program can
     * continue you can specify them here.
     *
     * @return return true if you don't want to reset argumentsLeft, default is
     * false.
     */
    protected boolean reset() {
        return false;
    }
}
