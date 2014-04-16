package me.servercaster.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import me.servercaster.BuilderPart;
import me.servercaster.converter.code.SpecialCodeConverter;
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

    public CodeConverter(FancyMessage fm, BuilderPart bp) {
        super(fm, bp);
        for (SpecialCodeConverter specialCodeConverter : specialCode) {
            specialCodeConverter.addBuilders(fm, bp);
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
        if (codes.containsKey(savedString)) {
            specialCode.add(codes.get(savedString));
        } else {
            bp.addAdition(savedString);
        }
        clearSavedString();
        return this;
    }

    @Override
    public Converter nextChar(char c) {
        if (fm == null || bp == null) {
            throw new NullPointerException("FancyMessage or ServercastMessage not declared");
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
                    return new BracketConverter(fm, bp);
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
