package me.servercaster.main.converter.code;

import me.servercaster.main.converter.SpecialCodeConverter;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class SuggestConverter extends SpecialCodeConverter {

    public SuggestConverter() {
        super(1);
    }    
    
    @Override
    protected String getKeyword() {
        return "SUGGEST";
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().suggest(argument);
    }
}
