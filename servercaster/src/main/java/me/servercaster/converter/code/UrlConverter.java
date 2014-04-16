package me.servercaster.converter.code;

import me.servercaster.BuilderPart;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlConverter extends SpecialCodeConverter {

    private int arguments = 1;

    @Override
    public String getCode() {
        return "URL";
    }

    @Override
    public void doAction(String s) {
        bp.addUrl(s.substring(1, s.length() - 2));
        arguments--;
    }

    @Override
    protected int getArgumentsLeft() {
        return arguments;
    }

}
