package me.servercaster.converter;

import me.servercaster.ServercastMessage;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class UrlConverter extends Converter {
    
    public UrlConverter(FancyMessage fm, ServercastMessage sm) {
        super(fm, sm);
    }
    
    @Override
    protected Converter end() {
        sm.addUrl(getSavedString().substring(1));
        return new WaitConverter(fm, sm);
    }
    
    @Override
    protected boolean isEndChar(char c) {
        return c == '"';
    }
    
}
