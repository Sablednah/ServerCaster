package me.servercaster.main.converter.action;

import me.servercaster.main.converter.CodeAction;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlAction extends CodeAction {

    public UrlAction() {
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
