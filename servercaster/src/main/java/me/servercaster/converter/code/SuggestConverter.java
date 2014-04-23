package me.servercaster.converter.code;

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
    public void doAction(String s) {
        fm.suggest(s);
    }
}
