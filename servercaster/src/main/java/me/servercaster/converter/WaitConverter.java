package me.servercaster.converter;

import me.servercaster.ServercastMessage;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class WaitConverter extends Converter {

    private char c;
    private boolean url = false;
    private boolean command = false;

    public WaitConverter(FancyMessage fm, ServercastMessage sm) {
        super(fm, sm);
    }

    @Override
    protected Converter end() {
        if (c == '{') {
            if (url) {
                return new UrlConverter(fm, sm);
            } else if (command) {
                return new CommandConverter(fm, sm);
            } else {
                return new BracketConverter(fm, sm);
            }
        } else if (c == '&') {
            return new CodeConverter(fm, sm);
        }
        return null;
    }

    @Override
    protected boolean isEndChar(char c) {
        this.c = c;
        return true;
    }

    void isUrl(boolean b) {
        url = b;
    }

    void isCommand(boolean b) {
        command = b;
    }

}
