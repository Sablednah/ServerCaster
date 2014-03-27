package mkremins.fanciful;

import org.bukkit.ChatColor;

final class MessagePart {

    ChatColor color = null;
    ChatColor[] styles = null;
    String clickActionName = null, clickActionData = null,
            hoverActionName = null, hoverActionData = null;
    final String text;

    MessagePart(final String text) {
        this.text = text;
    }

    String toJSONString() {
        final StringBuilder JSON = new StringBuilder();
        JSON.append("{text:'").append(text).append("'");
        if (color != null) {
            JSON.append(",color:'").append(color.name().toLowerCase()).append("'");
        }
        if (styles != null) {
            for (final ChatColor style : styles) {
                if (style == ChatColor.UNDERLINE) {
                    JSON.append(",").append("underlined").append(":true");

                } else {
                    JSON.append(",").append(style.name().toLowerCase()).append(":true");

                }
            }
        }
        if (clickActionName != null && clickActionData != null) {
            JSON.append(",")
                    .append("clickEvent:{")
                    .append("action:'").append(clickActionName).append("',")
                    .append("value:'").append(clickActionData).append("'")
                    .append("}");
        }
        if (hoverActionName != null && hoverActionData != null) {
            JSON.append(",")
                    .append("hoverEvent:{")
                    .append("action:'").append(hoverActionName).append("',")
                    .append("value:'").append(hoverActionData).append("'")
                    .append("}");
        }
        JSON.append("}");
        return JSON.toString();
    }

}
