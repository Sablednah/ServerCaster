package broadcaster;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcastConverter {

    private Map<String, String> codeReplacement;

    public BroadcastConverter() {
    }

    public void populateList() {
        codeReplacement = new HashMap<>();
        codeReplacement.put("&AQUA;", ChatColor.AQUA.toString());
        codeReplacement.put("&BLACK;", ChatColor.BLACK.toString());
        codeReplacement.put("&BLUE;", ChatColor.BLUE.toString());
        codeReplacement.put("&DARK_AQUA;", ChatColor.DARK_AQUA.toString());
        codeReplacement.put("&DARK_BLUE;", ChatColor.DARK_BLUE.toString());
        codeReplacement.put("&DARK_GRAY;", ChatColor.DARK_GRAY.toString());
        codeReplacement.put("&DARK_GREEN;", ChatColor.DARK_GREEN.toString());
        codeReplacement.put("&DARK_PURPLE;", ChatColor.DARK_PURPLE.toString());
        codeReplacement.put("&DARK_RED;", ChatColor.DARK_RED.toString());
        codeReplacement.put("&GOLD;", ChatColor.GOLD.toString());
        codeReplacement.put("&GRAY;", ChatColor.GRAY.toString());
        codeReplacement.put("&GREEN;", ChatColor.GREEN.toString());
        codeReplacement.put("&LIGHT_PURPLE;", ChatColor.LIGHT_PURPLE.toString());
        codeReplacement.put("&RED;", ChatColor.RED.toString());
        codeReplacement.put("&WHITE;", ChatColor.WHITE.toString());
        codeReplacement.put("&YELLOW;", ChatColor.YELLOW.toString());
        codeReplacement.put("&MAGIC;", ChatColor.MAGIC.toString());
        codeReplacement.put("&BOLD;", ChatColor.BOLD.toString());
        codeReplacement.put("&STRIKE;", ChatColor.STRIKETHROUGH.toString());
        codeReplacement.put("&UNDERLINE;", ChatColor.UNDERLINE.toString());
        codeReplacement.put("&ITALIC;", ChatColor.ITALIC.toString());
        codeReplacement.put("&RESET;", ChatColor.RESET.toString());

        codeReplacement.put("&0", ChatColor.BLACK.toString());
        codeReplacement.put("&1", ChatColor.DARK_BLUE.toString());
        codeReplacement.put("&2", ChatColor.DARK_GREEN.toString());
        codeReplacement.put("&3", ChatColor.DARK_AQUA.toString());
        codeReplacement.put("&4", ChatColor.DARK_RED.toString());
        codeReplacement.put("&5", ChatColor.DARK_PURPLE.toString());
        codeReplacement.put("&6", ChatColor.GOLD.toString());
        codeReplacement.put("&7", ChatColor.GRAY.toString());
        codeReplacement.put("&8", ChatColor.DARK_GRAY.toString());
        codeReplacement.put("&9", ChatColor.BLUE.toString());
        codeReplacement.put("&a", ChatColor.GREEN.toString());
        codeReplacement.put("&b", ChatColor.AQUA.toString());
        codeReplacement.put("&c", ChatColor.RED.toString());
        codeReplacement.put("&d", ChatColor.LIGHT_PURPLE.toString());
        codeReplacement.put("&e", ChatColor.YELLOW.toString());
        codeReplacement.put("&f", ChatColor.WHITE.toString());
        codeReplacement.put("&k", ChatColor.MAGIC.toString());
        codeReplacement.put("&l", ChatColor.BOLD.toString());
        codeReplacement.put("&m", ChatColor.STRIKETHROUGH.toString());
        codeReplacement.put("&n", ChatColor.UNDERLINE.toString());
        codeReplacement.put("&o", ChatColor.ITALIC.toString());
        codeReplacement.put("&r", ChatColor.RESET.toString());
    }

    public String[] getProperMessage(String message) {
        for (Map.Entry<String, String> entry : codeReplacement.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            message = message.replaceAll(key, value);
        }
        return message.split("&NEW_LINE");
    }
}
