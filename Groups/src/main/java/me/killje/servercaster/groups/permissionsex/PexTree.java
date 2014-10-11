package me.killje.servercaster.groups.permissionsex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.org.apache.commons.lang3.tuple.ImmutablePair;
import net.minecraft.util.org.apache.commons.lang3.tuple.Pair;
import ru.tehkode.permissions.PermissionGroup;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PexTree {

    private final Map<String, PexNode> pexNodes = new HashMap<>();
    private final String worldName;

    public PexTree(String worldName, List<PermissionGroup> groups) {
        this.worldName = worldName;
        for (PermissionGroup group : groups) {
            String groupName = group.getName();
            pexNodes.put(groupName.toLowerCase(), new PexNode(group));
        }

        for (Map.Entry<String, PexNode> entry : pexNodes.entrySet()) {
            PexNode groupNode = entry.getValue();
            List<PermissionGroup> childs = groupNode.getGroup().getChildGroups();
            for (PermissionGroup child : childs) {
                String childName = child.getName();
                if (pexNodes.get(childName) != null) {
                    groupNode.addChild(pexNodes.get(childName));
                }
            }
        }

        for (Map.Entry<String, PexNode> entry : pexNodes.entrySet()) {
            PexNode group = entry.getValue();
            group.removeDubbleGroups(null);
        }
    }

    public Pair<Integer, String> getTopPermission(String groupName, int iterations) {
        PexNode gn = pexNodes.get(groupName.toLowerCase());
        PermissionGroup g = gn.getGroup();
        for (String string : g.getPermissions(worldName)) {
            if (string.startsWith("ServerCaster.Messages.")) {
                return new ImmutablePair(iterations, string);
            }
        }
        Pair<Integer, String> permissions = null;
        for (PexNode groupNode : gn.getChilds()) {
            Pair tp = getTopPermission(groupNode.getGroup().getName(), iterations + 1);
            if (permissions == null || (tp != null && ((Integer) tp.getKey()) < permissions.getKey())) {
                permissions = tp;
            }
        }
        return permissions;
    }
}
