package me.servercaster.groups;

import java.util.ArrayList;
import me.servercaster.groups.groupmanager.GroupTree;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupManagerHandler{

    private final GroupManager groupManager;
    private ArrayList<GroupList> groups = new ArrayList<>();

    public GroupManagerHandler(GroupManager plugin) {
        groupManager = plugin;
    }
    
    public boolean newPlayer(Player player) {
        GroupTree gt = new GroupTree(player.getWorld().getName(), groupManager);
        for (GroupList groupList : groups) {
            String groupName = groupList.getName();
            
        }
        return false;
    }

    public String getGroup(Player base) {
        AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
        if (handler == null) {
            return null;
        }
        return handler.getGroup(base.getName());
    }

   
    public boolean leavePlayer(Player player) {
        return false;
    }

}
