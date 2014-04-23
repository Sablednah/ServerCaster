package me.servercaster.converter.code;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlConverter extends SpecialCodeConverter {

    public UrlConverter() {
        super(1);
    }

    @Override
    protected String getKeyword() {
        return "URL";
    }

    @Override
    public void doAction(String s) {
        fm.link(s.substring(1, s.length() - 2));
    }

}
