package me.servercaster.example;

import me.servercaster.main.converter.SpecialCodeConverter;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class WarpConverter extends SpecialCodeConverter {

    public WarpConverter() {
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
