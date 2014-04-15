package me.servercaster.converter.code;

import me.servercaster.BuilderPart;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CommandConverter extends SpecialCodeConverter {

    private final BuilderPart sm;
    private int arguments = 1;

    public CommandConverter(BuilderPart sm) {
        this.sm = sm;
    }

    @Override
    public String getCode() {
        return "COMMAND";
    }

    @Override
    public void doAction(String s) {
        sm.addCommand("/" + s);
        arguments--;
    }

    @Override
    protected int getArgumentsLeft() {
        return arguments;
    }

}
