package me.killje.servercaster.core.converter.action;

import me.killje.servercaster.core.converter.CodeAction;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CommandAction extends CodeAction {

    public CommandAction() {
        super(1);
    }    
    
    @Override
    protected String getKeyword() {
        return "COMMAND";
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().command("/" + argument);
    }
}
