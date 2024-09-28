package eightman.library.GUI.GUI_tool;

import eightman.library.GUI.System.NavalParser;
import eightman.library.GUI.System.NavalParser.FS_DBList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class FleetDesigner_GUI {

    private JFrame frame;
    private JTree tree;
    private JPanel detailsPanel;

    public FleetDesigner_GUI() {
        frame = new JFrame("Fleet Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // ツリー構造の作成
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Navy");

        try {
            NavalParser parser = new NavalParser();
            List<FS_DBList> fsDbList = parser.parseNavalData();

            for (FS_DBList entry : fsDbList) {
                DefaultMutableTreeNode fleetNode = findOrCreateNode(root, "Fleet: " + entry.fleetName);
                DefaultMutableTreeNode taskForceNode = findOrCreateNode(fleetNode, "Task Force: " + entry.taskForceName);
                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode("Ship: " + entry.shipName);
                taskForceNode.add(shipNode);

                shipNode.add(new DefaultMutableTreeNode("Definition: " + entry.shipDefinition));
                shipNode.add(new DefaultMutableTreeNode("Owner: " + entry.shipOwner));
                shipNode.add(new DefaultMutableTreeNode("Version: " + entry.shipVersionName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JTreeの作成
        tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        // 詳細表示用のパネル
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        JLabel detailsLabel = new JLabel("Select a node to see details");
        detailsPanel.add(detailsLabel, BorderLayout.CENTER);

        // ツリーの選択イベントリスナー
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    detailsLabel.setText("Selected: " + selectedNode.getUserObject().toString());
                }
            }
        });

        // 左にツリー、右に詳細表示を配置するスプリットペイン
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, detailsPanel);
        splitPane.setDividerLocation(300);

        // メインフレームに追加
        frame.getContentPane().add(splitPane);
    }

    private DefaultMutableTreeNode findOrCreateNode(DefaultMutableTreeNode parent, String nodeName) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (node.getUserObject().toString().equals(nodeName)) {
                return node;
            }
        }
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
        parent.add(newNode);
        return newNode;
    }

    public void showGUI() {
        frame.setVisible(true);
    }
}