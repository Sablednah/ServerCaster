package me.servercaster.core.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CodeConverter extends Converter {

    private static final Map<String, CodeAction> codes = new HashMap<>();
    private final ArrayList<CodeAction> actionCode = new ArrayList<>();
    private boolean nextChar = false;
    private boolean inBracket = false;

    public CodeConverter(FancyMessage fm) {
        super(fm);
        for (Map.Entry<String, CodeAction> entry : codes.entrySet()) {
            CodeAction codeAction = entry.getValue();
            codeAction.addBuilder(fm);
        }
    }

    @Override
    boolean isEndChar(char c) {
        return c == ';';
    }

    @Override
    Converter end() {
        nextChar = true;
        String savedString = getSavedString();
        if (codes.containsKey(savedString.toLowerCase())) {
            CodeAction ca = codes.get(savedString.toLowerCase());
            if (ca.hasArgumentsLeft()) {
                actionCode.add(ca);
            } else {
                ca.doAction(savedString);
            }
        } else {
            throw new IllegalArgumentException("Code unknown");
        }
        clearSavedString();
        return this;
    }

    @Override
    Converter nextChar(char c) {
        if (fm == null) {
            throw new NullPointerException("FancyMessage not declared");
        }
        if (inBracket) {
            if (actionCode.get(0).isEndChar(c)) {
                if (actionCode.get(0).isEnd(getSavedString())) {
                    actionCode.remove(0);
                    inBracket = false;
                }
            }
            addChar(c);
            return this;
        }
        if (nextChar) {
            if (c == '{') {
                if (actionCode.isEmpty()) {
                    return new BracketConverter(fm);
                }
                inBracket = true;
                return this;
            } else {
                nextChar = false;
                return this;
            }
        } else {
            return super.nextChar(c);
        }
    }

    public static void addCodeAction(CodeAction ca) {
        codes.put(ca.getCode(), ca);
    }
}
