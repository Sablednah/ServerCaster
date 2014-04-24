package me.servercaster.main.converter.code;

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
    public void doAction(String argument) {
        getJSONSaver().link(argument.substring(1, argument.length() - 2));
    }

}
