package me.killje.servercaster.groups.groupmanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.org.apache.commons.lang3.tuple.ImmutablePair;
import net.minecraft.util.org.apache.commons.lang3.tuple.Pair;
import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;

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

    public Pair<Integer, String> getTopPermission(String groupName, int iterations) {
        GroupNode gn = groupNodes.get(groupName);
        Group g = gn.getGroup();
        for (String string : g.getPermissionList()) {
            if (string.startsWith("ServerCaster.messages.")) {
                return new ImmutablePair(iterations, string);
            }
        }
        Pair<Integer, String> permissions = null;
        for (GroupNode groupNode : gn.getChilds()) {
            Pair tp = getTopPermission(groupNode.getGroup().getName(), iterations + 1);
            if (permissions == null || (tp != null && ((Integer) tp.getKey()) < permissions.getKey())) {
                permissions = tp;
            }
        }
        return permissions;
    }
}
