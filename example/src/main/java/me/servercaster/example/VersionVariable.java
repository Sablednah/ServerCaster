package me.servercaster.example;

import java.util.ArrayList;
import me.servercaster.core.ServerCaster;
import me.servercaster.core.event.CastListener;
import me.servercaster.core.event.PreCastEvent;
import me.servercaster.core.event.PreCastPlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class VersionVariable implements CastListener {

    private final JavaPlugin instance;

    public VersionVariable(JavaPlugin instance) {
        this.instance = instance;
        ServerCaster.addMessageListener(instance, this);
    }

    @Override
    public void castHandler(PreCastEvent pce) {
        ArrayList<String> messages = pce.getMessages();
        ArrayList<String> newMessages = new ArrayList<>();
        for (String string : messages) {
            string = string.replaceAll("(?i)%VERSION%", instance.getServer().getVersion());
            newMessages.add(string);
        }
        pce.setMessages(messages);
    }

    @Override
    public void castPlayerHandler(PreCastPlayerEvent pcpe) {
    }
}
