package me.servercaster.core.converter;

import java.util.ArrayList;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class BracketConverter extends Converter {

    private final ArrayList<CodeAction> emptyCodes;
    
    BracketConverter(FancyMessage fm, ArrayList<CodeAction> emptyCodes) {
        super(fm);
        this.emptyCodes = emptyCodes;
    }

    @Override
    boolean isEndChar(char c) {
        return c == '}';
    }

    @Override
    Converter end() {
        for (CodeAction codeAction : emptyCodes) {
            codeAction.doAction(getSavedString());
        }
        fm.text(getSavedString());
        fm.then();
        return new TextConverter(fm);
    }

}
