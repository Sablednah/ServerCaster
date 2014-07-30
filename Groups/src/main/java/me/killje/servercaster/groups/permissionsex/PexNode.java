package me.killje.servercaster.groups.permissionsex;

import java.util.LinkedList;
import ru.tehkode.permissions.PermissionGroup;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class PexNode {

    private LinkedList<PexNode> childs = new LinkedList<>();
    private final PermissionGroup groupData;
    private boolean isTop = true;
    
    public PexNode(PermissionGroup groupData){
        this.groupData = groupData;
    }
    
    public LinkedList<PexNode> removeDubbleGroups(LinkedList<PexNode> parentGroups) {
        if (parentGroups != null) {
            for (PexNode groupNode : childs) {
                if (parentGroups.contains(groupNode)) {
                    parentGroups.remove(groupNode);
                }
            }
        }
        for (PexNode pexNode : childs) {
            childs = pexNode.removeDubbleGroups(childs);
            pexNode.setTop(false);
        }
        return parentGroups;
    }
    
    public PermissionGroup getGroup() {
        return groupData;
    }

    public boolean addChild(PexNode child) {
        return childs.add(child);
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public boolean isTop() {
        return isTop;
    }

    public LinkedList<PexNode> getChilds() {
        return childs;
    }
}
