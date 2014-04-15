package me.servercaster;

import me.servercaster.converter.CodeConverter;
import me.servercaster.converter.Converter;
import me.servercaster.converter.TextConverter;
import me.servercaster.converter.code.CommandConverter;
import me.servercaster.converter.code.UrlConverter;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServercastConverter {

    private Converter converter;

    public String getProperMessage(String message) {
        FancyMessage fm = new FancyMessage("");
        ServercastMessage sm = new ServercastMessage();
        converter = new TextConverter(fm, sm);
        init(sm);
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            converter = converter.nextChar(currentChar);
        }
        converter.done();
        return fm.toJSONString();
    }

    private void init(ServercastMessage sm) {
        CodeConverter.addSpecialCode(new CommandConverter(sm));
        CodeConverter.addSpecialCode(new UrlConverter(sm));
    }
}
