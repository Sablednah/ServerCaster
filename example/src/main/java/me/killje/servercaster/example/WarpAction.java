package me.killje.servercaster.example;

import me.killje.servercaster.core.converter.CodeAction;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class WarpAction extends CodeAction {

    public WarpAction() {
        super(1); //amount of arguments this converter needs
    }

    @Override
    protected String getKeyword() {
        return "WARP";
    }

    @Override
    public void doAction(String argument) {
        getJSONSaver().command("/warp " + argument);
    }

}
