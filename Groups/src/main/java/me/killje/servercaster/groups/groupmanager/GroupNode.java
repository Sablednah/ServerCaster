package me.killje.servercaster.groups.groupmanager;

import java.util.LinkedList;
import org.anjocaido.groupmanager.data.Group;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class GroupNode {

    private LinkedList<GroupNode> childs;
    private final Group groupData;
    private boolean isTop = true;

    public GroupNode(Group groupData) {
        this.groupData = groupData;
    }

    public LinkedList<GroupNode> removeDubbleGroups(LinkedList<GroupNode> parentGroups) {
        if (parentGroups != null) {
            for (GroupNode groupNode : childs) {
                if (parentGroups.contains(groupNode)) {
                    parentGroups.remove(groupNode);
                }
            }
        }
        for (GroupNode groupNode : childs) {
            childs = groupNode.removeDubbleGroups(childs);
            groupNode.setTop(false);
        }
        return parentGroups;
    }

    public Group getGroup() {
        return groupData;
    }
    
    public boolean addChild(GroupNode child){
        return childs.add(child);
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public boolean isTop() {
        return isTop;
    }

    public LinkedList<GroupNode> getChilds() {
        return childs;
    }
    
    
}
