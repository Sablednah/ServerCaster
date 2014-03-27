package broadcaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;

/**
 *
 * @author Patrick Beuks (s2288842), Floris Huizinga (s2397617) and
 * @author Timo Smit (s2337789)
 */
public class BroadcastMessage {

    private final Map<String, ChatColor> colors = new HashMap<>();
    private final Map<String, ChatColor> styles = new HashMap<>();
    private String text;
    private final ArrayList<String> textAditions = new ArrayList<>();
    private boolean url;
    private boolean command;
    private String link;
    private String commandString;

    public BroadcastMessage() {
        colors.put("AQUA", ChatColor.AQUA);
        colors.put("BLACK", ChatColor.BLACK);
        colors.put("BLUE", ChatColor.BLUE);
        colors.put("DARK_AQUA", ChatColor.DARK_AQUA);
        colors.put("DARK_BLUE", ChatColor.DARK_BLUE);
        colors.put("DARK_GRAY", ChatColor.DARK_GRAY);
        colors.put("DARK_GREEN", ChatColor.DARK_GREEN);
        colors.put("DARK_PURPLE", ChatColor.DARK_PURPLE);
        colors.put("DARK_RED", ChatColor.DARK_RED);
        colors.put("GOLD", ChatColor.GOLD);
        colors.put("GRAY", ChatColor.GRAY);
        colors.put("GREEN", ChatColor.GREEN);
        colors.put("LIGHT_PURPLE", ChatColor.LIGHT_PURPLE);
        colors.put("RED", ChatColor.RED);
        colors.put("WHITE", ChatColor.WHITE);
        colors.put("YELLOW", ChatColor.YELLOW);

        colors.put("0", ChatColor.BLACK);
        colors.put("1", ChatColor.DARK_BLUE);
        colors.put("2", ChatColor.DARK_GREEN);
        colors.put("3", ChatColor.DARK_AQUA);
        colors.put("4", ChatColor.DARK_RED);
        colors.put("5", ChatColor.DARK_PURPLE);
        colors.put("6", ChatColor.GOLD);
        colors.put("7", ChatColor.GRAY);
        colors.put("8", ChatColor.DARK_GRAY);
        colors.put("9", ChatColor.BLUE);
        colors.put("a", ChatColor.GREEN);
        colors.put("b", ChatColor.AQUA);
        colors.put("c", ChatColor.RED);
        colors.put("d", ChatColor.LIGHT_PURPLE);
        colors.put("e", ChatColor.YELLOW);
        colors.put("f", ChatColor.WHITE);

        styles.put("MAGIC", ChatColor.MAGIC);
        styles.put("BOLD", ChatColor.BOLD);
        styles.put("STRIKE", ChatColor.STRIKETHROUGH);
        styles.put("UNDERLINE", ChatColor.UNDERLINE);
        styles.put("ITALIC", ChatColor.ITALIC);

        styles.put("k", ChatColor.MAGIC);
        styles.put("l", ChatColor.BOLD);
        styles.put("m", ChatColor.STRIKETHROUGH);
        styles.put("n", ChatColor.UNDERLINE);
        styles.put("o", ChatColor.ITALIC);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addAdition(String addition) {
        textAditions.add(addition);
    }

    public void addUrl(String urlLink) {
        url = true;
        link = urlLink;
    }

    public void addCommand(String command) {
        this.command = true;
        this.commandString = command;
    }

    public void getMessage(FancyMessage fm) {
        fm.then(text);
        ArrayList<ChatColor> stylesSaver = new ArrayList<>();
        for (String string : textAditions) {
            if (colors.containsKey(string)) {
                fm.color(colors.get(string));
            } else if (styles.containsKey(string)) {
                stylesSaver.add(styles.get(string));
            }
        }
        if (!styles.isEmpty()) {
            ChatColor[] styleArray = new ChatColor[stylesSaver.size()];
            for (int i = 0; i < stylesSaver.size(); i++) {
                styleArray[i] = stylesSaver.get(i);
            }
            fm.style(styleArray);
        }
        if (url) {
            fm.link(link);
        } else if (command) {
            fm.command(this.commandString);
        }
    }
}
