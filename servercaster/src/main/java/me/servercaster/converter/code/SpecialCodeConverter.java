package me.servercaster.converter.code;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public abstract class SpecialCodeConverter{

    public abstract String getCode();
    public abstract void doAction(String s);
    protected abstract int getArgumentsLeft();

    public boolean isEnd(String s) {
        doAction(s);
        return getArgumentsLeft() == 0;
    }

    public boolean isEndChar(char c) {
        return c == '}';
    }
}
