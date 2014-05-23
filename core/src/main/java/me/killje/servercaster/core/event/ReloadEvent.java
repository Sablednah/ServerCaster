package me.killje.servercaster.core.event;

import java.util.EventObject;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ReloadEvent extends EventObject{
    private Boolean cancelled = false;
    private final CommandSender commandSender;

    public ReloadEvent(CommandSender commandSender, Object source) {
        super(source);
        this.commandSender = commandSender;
    }

    public void setCancelled(Boolean cancelled){
        this.cancelled = cancelled;
    }

    public Boolean isCancelled() {
        return cancelled;
    }

    public CommandSender getCommandSender() {
        return commandSender;
    }
    
}
