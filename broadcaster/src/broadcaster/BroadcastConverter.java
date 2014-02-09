package broadcaster;

import java.util.HashMap;
import java.util.Map;
import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcastConverter {

    private Map<String, ChatColor> codeReplacement;

    public BroadcastConverter() {
    }

    public void populateList() {
        codeReplacement = new HashMap<>();
        codeReplacement.put("AQUA", ChatColor.AQUA);
        codeReplacement.put("BLACK", ChatColor.BLACK);
        codeReplacement.put("BLUE", ChatColor.BLUE);
        codeReplacement.put("DARK_AQUA", ChatColor.DARK_AQUA);
        codeReplacement.put("DARK_BLUE", ChatColor.DARK_BLUE);
        codeReplacement.put("DARK_GRAY", ChatColor.DARK_GRAY);
        codeReplacement.put("DARK_GREEN", ChatColor.DARK_GREEN);
        codeReplacement.put("DARK_PURPLE", ChatColor.DARK_PURPLE);
        codeReplacement.put("DARK_RED", ChatColor.DARK_RED);
        codeReplacement.put("GOLD", ChatColor.GOLD);
        codeReplacement.put("GRAY", ChatColor.GRAY);
        codeReplacement.put("GREEN", ChatColor.GREEN);
        codeReplacement.put("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE);
        codeReplacement.put("RED", ChatColor.RED);
        codeReplacement.put("WHITE", ChatColor.WHITE);
        codeReplacement.put("YELLOW", ChatColor.YELLOW);
        codeReplacement.put("MAGIC", ChatColor.MAGIC);
        codeReplacement.put("BOLD", ChatColor.BOLD);
        codeReplacement.put("STRIKE", ChatColor.STRIKETHROUGH);
        codeReplacement.put("UNDERLINE", ChatColor.UNDERLINE);
        codeReplacement.put("ITALIC", ChatColor.ITALIC);
        codeReplacement.put("RESET", ChatColor.RESET);

        codeReplacement.put("0", ChatColor.BLACK);
        codeReplacement.put("1", ChatColor.DARK_BLUE);
        codeReplacement.put("2", ChatColor.DARK_GREEN);
        codeReplacement.put("3", ChatColor.DARK_AQUA);
        codeReplacement.put("4", ChatColor.DARK_RED);
        codeReplacement.put("5", ChatColor.DARK_PURPLE);
        codeReplacement.put("6", ChatColor.GOLD);
        codeReplacement.put("7", ChatColor.GRAY);
        codeReplacement.put("8", ChatColor.DARK_GRAY);
        codeReplacement.put("9", ChatColor.BLUE);
        codeReplacement.put("a", ChatColor.GREEN);
        codeReplacement.put("b", ChatColor.AQUA);
        codeReplacement.put("c", ChatColor.RED);
        codeReplacement.put("d", ChatColor.LIGHT_PURPLE);
        codeReplacement.put("e", ChatColor.YELLOW);
        codeReplacement.put("f", ChatColor.WHITE);
        codeReplacement.put("k", ChatColor.MAGIC);
        codeReplacement.put("l", ChatColor.BOLD);
        codeReplacement.put("m", ChatColor.STRIKETHROUGH);
        codeReplacement.put("n", ChatColor.UNDERLINE);
        codeReplacement.put("o", ChatColor.ITALIC);
        codeReplacement.put("r", ChatColor.RESET);
    }

    public String[] getProperMessage(String message) {
        FancyMessage fm = new FancyMessage("");
        int codeMessage = message.indexOf('&');
        while (codeMessage != -1) {
            fm.then(message.substring(0, codeMessage - 1));
            int i = codeMessage + 1;
            String code = "";
            while (message.charAt(i)!=';') {
                code += message.charAt(i);
                i++;
            }
            for (Map.Entry<String, ChatColor> entry : codeReplacement.entrySet()) {
                String string = entry.getKey();
                ChatColor chatColor = entry.getValue();
                if (string.equals(code)) {
                    fm.then().color(chatColor);
                }
            }
            codeMessage = message.indexOf('&');
        }
        int urlStart = message.indexOf("&URL");
        while (urlStart != -1) {
            int urlEnd = message.indexOf("&URLEND");
            if (urlEnd != -1) {
                int urlName = message.indexOf("&URLNAME");
                String beforeUrl = message.substring(0, urlStart);
                String afterUrl = message.substring(urlEnd + 7);
                String urlText = "";
                if (urlName != -1 && urlName < urlEnd) {
                    String urlNameString = message.substring(urlName + 8, urlEnd);
                    String urlString = message.substring(urlStart + 4, urlName);
                    urlText = new FancyMessage(urlNameString).link(urlString).toJSONString();
                } else {
                    String urlString = message.substring(urlStart + 4, urlEnd);
                    urlText = new FancyMessage(urlString).link(urlString).toJSONString();
                }
                message = beforeUrl + urlText + afterUrl;
            }
            urlStart = message.indexOf("&URL");
        }
        return message.split("&NEW_LINE");
    }
}
