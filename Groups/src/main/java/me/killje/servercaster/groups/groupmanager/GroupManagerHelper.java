package me.killje.servercaster.groups.groupmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.killje.servercaster.autocaster.AutoCastHelper;
import me.killje.servercaster.autocaster.GroupSender;
import me.killje.servercaster.groups.Groups;
import me.killje.servercaster.groups.GroupsGroupSender;
import net.minecraft.util.org.apache.commons.lang3.tuple.Pair;
import org.anjocaido.groupmanager.GroupManager;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupManagerHelper extends AutoCastHelper {

    private final Map<World, GroupTree> wg = new HashMap<>();

    public GroupManagerHelper() {
        initWorlds();
    }

    @Override
    protected void addPlayer(Player player) {
        GroupManager gm = (GroupManager) Groups.getInstance().getServer().getPluginManager().getPlugin("GroupManager");
        String groupName = gm.getWorldsHolder().getWorldData(player).getUser(player.getName()).getGroup().getLastName();
        String permission;
        Pair<Integer, String> topPermission = wg.get(player.getWorld()).getTopPermission(groupName, 0);
        if (topPermission == null) {
            Groups.getInstance().getLogger().info("This player does not have a permission to view messages");
        } else {
            permission = topPermission.getValue();
            permission = permission.replaceFirst("ServerCaster\\.", "");
            if (!senders.contains(permission)) {
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

    private void initWorlds() {
        List<World> worlds = Groups.getInstance().getServer().getWorlds();
        GroupManager gm = (GroupManager) Groups.getInstance().getServer().getPluginManager().getPlugin("GroupManager");
        for (World world : worlds) {
            wg.put(world, new GroupTree(world.toString(), gm));
        }
    }

    @Override
    public void init() {
    }

}
