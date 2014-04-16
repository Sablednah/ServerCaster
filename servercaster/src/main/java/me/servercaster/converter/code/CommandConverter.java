package me.servercaster.converter.code;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CommandConverter extends SpecialCodeConverter {

    private int arguments = 1;

    @Override
    public String getCode() {
        return "COMMAND";
    }

    @Override
    public void doAction(String s) {
        bp.addCommand("/" + s);
        arguments--;
    }

    @Override
    protected int getArgumentsLeft() {
        return arguments;
    }

}
