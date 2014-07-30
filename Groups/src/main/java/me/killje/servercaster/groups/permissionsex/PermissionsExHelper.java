package me.killje.servercaster.groups.permissionsex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.killje.servercaster.autocaster.AutoCastHelper;
import me.killje.servercaster.autocaster.GroupSender;
import me.killje.servercaster.groups.Groups;
import me.killje.servercaster.groups.GroupsGroupSender;
import net.minecraft.util.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.tehkode.permissions.PermissionEntity;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.tehkode.permissions.events.PermissionEntityEvent;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PermissionsExHelper extends AutoCastHelper {

    private final Map<World, PexTree> wg = new HashMap<>();

    public PermissionsExHelper() {
        PermissionsEx pex = (PermissionsEx) Groups.getInstance().getServer().getPluginManager().getPlugin("PermissionsEx");
        initWorlds(pex);
    }

    private void initWorlds(PermissionsEx pex) {
        List<PermissionGroup> pgl = pex.getPermissionsManager().getGroupList();
        List<World> worlds = Groups.getInstance().getServer().getWorlds();
        for (World world : worlds) {
            wg.put(world, new PexTree(world.toString(), pgl));
        }
    }

    @Override
    public void init() {
    }

    @Override
    protected synchronized void addPlayer(Player player) {
        PermissionsEx pex = (PermissionsEx) Groups.getInstance().getServer().getPluginManager().getPlugin("PermissionsEx");
        List<PermissionGroup> pgl = pex.getPermissionsManager().getUser(player.getUniqueId()).getParents();
        
        String permission;
        Pair<Integer, String> topPermission = null;
        for (PermissionGroup permissionGroup : pgl) {
            Pair<Integer, String> newPermission = wg.get(player.getWorld()).getTopPermission(permissionGroup.getName(), 0);
            if (topPermission == null || newPermission.getKey() < topPermission.getKey()){
                topPermission = newPermission;
            }
        }
        if (topPermission == null) {
            Groups.getInstance().getLogger().info("This player does not have a permission to view messages");
        } else {
            permission = topPermission.getValue();
            permission = permission.replaceFirst("ServerCaster\\.", "");
            boolean hasGroup = false;
            for (GroupSender groupSender : senders) {
                if (groupSender.getPath().equals(permission)) {
                    hasGroup = true;
                }
            }
            if (!hasGroup) {
                GroupSender gs = new GroupsGroupSender(permission);
                gs.addPlayer(player);
                senders.add(gs);
                return;
            }
            for (GroupSender groupSender : senders) {
                if (player.hasPermission("ServerCaster." + groupSender.getGroup())) {
                    groupSender.addPlayer(player);
                }
            }
        }
    }

    @Override
    protected synchronized void removePlayer(Player player) {
        for (Iterator<GroupSender> it = senders.iterator(); it.hasNext();) {
            GroupSender groupSender = it.next();
            if (groupSender.removePlayer(player) && !groupSender.hasPlayers()) {
                it.remove();
            }
        }
    }

    @EventHandler
    public void userChangeEvent(PermissionEntityEvent e) {
        if (!firstRun) {
            if (e.getType() == PermissionEntity.Type.USER) {
                PermissionUser user = (PermissionUser) e.getEntity();
                removePlayer(user.getPlayer());
                addPlayer(user.getPlayer());
            }
        }
    }

    @EventHandler
    public void userChangeWorldEvent(PlayerChangedWorldEvent e) {
        if (!firstRun) {
            removePlayer(e.getPlayer());
            addPlayer(e.getPlayer());
        }
    }
}
