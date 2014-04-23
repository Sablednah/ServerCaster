package me.servercaster;

import me.servercaster.converter.Converter;
import me.servercaster.converter.TextConverter;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class Builder {

    private Converter converter;

    public String getProperMessage(String message) {
        FancyMessage fm = new FancyMessage();
        converter = new TextConverter(fm);
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            converter = converter.nextChar(currentChar);
        }
        converter.done();
        return fm.toJSONString();
    }
}
