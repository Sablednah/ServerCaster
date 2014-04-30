package me.servercaster.main.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mkremins.fanciful.FancyMessage;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class CodeConverter extends Converter {

    private static final Map<String, SpecialCodeConverter> codes = new HashMap<>();
    private final ArrayList<SpecialCodeConverter> specialCode = new ArrayList<>();
    private boolean nextChar = false;
    private boolean inBracket = false;

    public CodeConverter(FancyMessage fm) {
        super(fm);
        for (Map.Entry<String, SpecialCodeConverter> entry : codes.entrySet()) {
            SpecialCodeConverter specialCodeConverter = entry.getValue();
            specialCodeConverter.addBuilder(fm);
        }
    }

    @Override
    protected boolean isEndChar(char c) {
        return c == ';';
    }

    @Override
    protected Converter end() {
        nextChar = true;
        String savedString = getSavedString();
        if (codes.containsKey(savedString.toLowerCase())) {
            SpecialCodeConverter scc = codes.get(savedString.toLowerCase());
            if (scc.hasArgumentsLeft()) {
                specialCode.add(scc);
            } else {
                scc.doAction(savedString);
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
            if (specialCode.get(0).isEndChar(c)) {
                if (specialCode.get(0).isEnd(getSavedString())) {
                    specialCode.remove(0);
                    inBracket = false;
                }
            }
            addChar(c);
            return this;
        }
        if (nextChar) {
            if (c == '{') {
                if (specialCode.isEmpty()) {
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

    public static void addSpecialCode(SpecialCodeConverter scc) {
        codes.put(scc.getCode(), scc);
    }
}
