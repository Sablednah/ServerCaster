package me.servercaster.converter;

import me.servercaster.ServerCaster;
import me.servercaster.ServercastMessage;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CodeConverter extends Converter {

    public CodeConverter(FancyMessage fm, ServercastMessage sm) {
        super(fm, sm);
        ServerCaster.getInstance().getLogger().info("i get initialized \n");
    }

    @Override
    protected boolean isEndChar(char c) {
        ServerCaster.getInstance().getLogger().info("end char reached on char " + c + "\n");
        return c == ';';
    }

    @Override
    protected Converter end() {
        String savedString = getSavedString();
        if (savedString.equalsIgnoreCase("URL")) {
            WaitConverter wc = new WaitConverter(fm, sm);
            wc.isUrl(true);
            return wc;
        }
        if (savedString.equalsIgnoreCase("COMMAND")) {
            WaitConverter wc = new WaitConverter(fm, sm);
            wc.isCommand(true);
            return wc;
        }
        sm.addAdition(savedString);
        return new WaitConverter(fm, sm);
    }

}
