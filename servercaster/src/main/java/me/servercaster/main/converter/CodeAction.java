package me.servercaster.main.converter;

import mkremins.fanciful.FancyMessage;

/**
 * This class can be used to create new Converters
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class CodeAction {

    private FancyMessage fm;
    private int arguments;
    private int argumentsLeft;

    /**
     *
     * @param arguments the amount of arguments this code needs (how many
     * brackets it needs)
     */
    public CodeAction(int arguments) {
        this.arguments = arguments;
        this.argumentsLeft = arguments;
    }

    /**
     *
     * @return String that represent the code word that needs to be found in the
     * config.yml
     */
    protected abstract String getKeyword();

    /**
     * when a bracket is totally read doAction is called
     *
     * @param argument the text inside a bracket (is KeyWord found when
     * arguments are 0)
     */
    public abstract void doAction(String argument);

    String getCode() {
        return getKeyword().toLowerCase();
    }

    boolean hasArgumentsLeft() {
        return argumentsLeft != 0;
    }

    boolean isEnd(String argument) {
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

    void addBuilder(FancyMessage fm) {
        this.fm = fm;
    }

    boolean isEndChar(char c) {
        return c == '}';
    }

    /**
     *
     * @return the JSON lib handler
     */
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
