package me.servercaster.converter.code;

import me.servercaster.ServercastMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlConverter extends SpecialCodeConverter {

    private final ServercastMessage sm;
    private int arguments = 1;

    public UrlConverter(ServercastMessage sm) {
        this.sm = sm;
    }

    @Override
    public String getCode() {
        return "URL";
    }

    @Override
    public void doAction(String s) {
        sm.addUrl(s.substring(1, s.length() - 2));
        arguments--;
    }

    @Override
    protected int getArgumentsLeft() {
        return arguments;
    }

}
