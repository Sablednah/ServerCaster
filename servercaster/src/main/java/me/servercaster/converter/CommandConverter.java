package me.servercaster.converter;

import me.servercaster.ServercastMessage;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class CommandConverter extends Converter {

    public CommandConverter(FancyMessage fm, ServercastMessage sm) {
        super(fm, sm);
    }

    @Override
    protected Converter end() {
        sm.addCommand(getSavedString());
        return new WaitConverter(fm, sm);
    }

    @Override
    protected boolean isEndChar(char c) {
        return c == '}';
    }
}
