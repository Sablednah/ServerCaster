package me.servercaster.core.converter.action;

import me.servercaster.core.converter.CodeAction;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class SuggestAction extends CodeAction {

    public SuggestAction() {
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
