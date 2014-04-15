package me.servercaster.converter.code;

import me.servercaster.BuilderPart;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlConverter extends SpecialCodeConverter {

    private final BuilderPart sm;
    private int arguments = 1;

    public UrlConverter(BuilderPart sm) {
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
