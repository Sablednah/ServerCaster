package me.killje.servercaster.example;

import java.util.ArrayList;
import me.killje.servercaster.core.ServerCaster;
import me.killje.servercaster.core.event.CastListener;
import me.killje.servercaster.core.event.PreCastEvent;
import me.killje.servercaster.core.event.PreCastPlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
class VersionVariable implements CastListener {

    private final JavaPlugin instance;

    public VersionVariable(JavaPlugin instance) {
        this.instance = instance;
        ServerCaster.addMessageListener(this);
    }

    @Override
    public void castHandler(PreCastEvent pce) {
        ArrayList<String> messages = pce.getMessages();
        ArrayList<String> newMessages = new ArrayList<>();
        for (String string : messages) {
            string = string.replaceAll("(?i)%VERSION%", instance.getServer().getVersion());
            newMessages.add(string);
        }
        pce.setMessages(newMessages);
    }

    @Override
    public void castPlayerHandler(PreCastPlayerEvent pcpe) {
    }
}
