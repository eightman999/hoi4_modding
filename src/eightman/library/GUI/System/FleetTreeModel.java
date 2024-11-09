package eightman.library.GUI.System;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class FleetTreeModel extends DefaultTreeModel {

    public FleetTreeModel(TreeNode root) {
        super(root);
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent;
            Object userObject = node.getUserObject();
            if (userObject instanceof Ship) {
                Ship ship = (Ship) userObject;
                return new DefaultMutableTreeNode(ship.getChildren().get(index));
            }
        }
        return super.getChild(parent, index);
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent;
            Object userObject = node.getUserObject();
            if (userObject instanceof Ship) {
                Ship ship = (Ship) userObject;
                return ship.getChildren().size();
            }
        }
        return super.getChildCount(parent);
    }
}