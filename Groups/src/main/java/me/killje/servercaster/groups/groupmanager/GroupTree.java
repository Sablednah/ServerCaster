package me.killje.servercaster.groups.groupmanager;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;
import org.bukkit.permissions.Permission;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupTree {

    private final Map<String, GroupNode> groupNodes = new HashMap<>();

    public GroupTree(String world, GroupManager groupManager) {
        WorldsHolder holder = groupManager.getWorldsHolder();
        Map<String, Group> gmGroups = holder.getWorldData(world).getGroups();
        for (Map.Entry<String, Group> groups : gmGroups.entrySet()) {
            String groupName = groups.getKey();
            Group group = groups.getValue();
            groupNodes.put(groupName, new GroupNode(group));
        }
        for (Map.Entry<String, GroupNode> entry : groupNodes.entrySet()) {
            GroupNode groupNode = entry.getValue();
            List<String> childs = groupNode.getGroup().getInherits();
            for (String childName : childs) {
                groupNode.addChild(groupNodes.get(childName));
            }
        }
        for (Map.Entry<String, GroupNode> entry : groupNodes.entrySet()) {
            GroupNode group = entry.getValue();
            group.removeDubbleGroups(null);
        }
    }

    public String getTopPermission(String groupName, int iterations) {
        GroupNode gn = groupNodes.get(groupName);
        Group g = gn.getGroup();
        for (String string : g.getPermissionList()) {
            if (string.startsWith("ServerCaster.messages.")) {
                return string;
            }
        }
        Entry<Integer, String> permissions;
        for (GroupNode groupNode : gn.getChilds()) {
            String permission = getTopPermission(groupNode.getGroup().getName(), iterations++);
            if (permission != null) {
                
            }
        }
        return null;
    }
}
