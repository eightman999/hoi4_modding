package eightman.library.GUI.System;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeTransferHandler extends TransferHandler {
    private final DataFlavor nodesFlavor;
    private final DataFlavor[] flavors;
    private DefaultMutableTreeNode[] nodesToRemove;

    public TreeTransferHandler() {
        nodesFlavor = new DataFlavor(DefaultMutableTreeNode[].class, "Node");
        flavors = new DataFlavor[]{nodesFlavor};
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        if (!support.isDrop() || !support.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }
        support.setShowDropLocation(true);
        return true;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
        int childIndex = dl.getChildIndex();
        TreePath dest = dl.getPath();
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) dest.getLastPathComponent();
        JTree tree = (JTree) support.getComponent();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        DefaultMutableTreeNode[] nodes = null;
        try {
            nodes = (DefaultMutableTreeNode[]) support.getTransferable().getTransferData(nodesFlavor);
        } catch (UnsupportedFlavorException | IOException ex) {
            ex.printStackTrace();
        }

        for (DefaultMutableTreeNode node : nodes) {
            model.insertNodeInto(node, parent, childIndex++);
            // 再帰的に子ノードも追加
            addChildrenToModel(node, model);
        }
        return true;
    }

    private void addChildrenToModel(DefaultMutableTreeNode node, DefaultTreeModel model) {
        Ship ship = (Ship) node.getUserObject();
        for (Ship child : ship.getChildren()) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
            model.insertNodeInto(childNode, node, node.getChildCount());
            addChildrenToModel(childNode, model);
        }
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTree tree = (JTree) c;
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            List<DefaultMutableTreeNode> copies = new ArrayList<>();
            for (TreePath path : paths) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                copies.add(node);
            }
            DefaultMutableTreeNode[] nodes = copies.toArray(new DefaultMutableTreeNode[0]);
            nodesToRemove = nodes;
            return new NodesTransferable(nodes);
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE) {
            JTree tree = (JTree) source;
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            for (DefaultMutableTreeNode node : nodesToRemove) {
                model.removeNodeFromParent(node);
            }
        }
    }

    public class NodesTransferable implements Transferable {
        private final DefaultMutableTreeNode[] nodes;

        public NodesTransferable(DefaultMutableTreeNode[] nodes) {
            this.nodes = nodes;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return nodesFlavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return nodes;
        }
    }
}