package me.servercaster.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.servercaster.core.event.CastListener;
import me.servercaster.core.event.CastReloadListener;
import me.servercaster.core.event.ReloadEvent;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ReloadHandler {

    private final List _listeners = new ArrayList();

    public synchronized void addEventListener(CastListener listener) {
        _listeners.add(listener);
    }

    public synchronized void removeEventListener(CastListener listener) {
        _listeners.remove(listener);
    }

    public void fireEvent(CommandSender commandSender) {
        fireReloadEvent(commandSender);
    }

    private synchronized boolean fireReloadEvent(CommandSender commandSender) {
        ReloadEvent event = new ReloadEvent(commandSender, this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((CastReloadListener) i.next()).castReloadHandler(event);
            if (event.isCancelled()) {
                return false;
            }
        }
        return true;
    }

}
