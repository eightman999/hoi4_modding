package eightman.library.GUI.GUI_tool;

import eightman.library.GUI.System.Fleet;
import eightman.library.GUI.System.NavalParser;
import eightman.library.GUI.System.Ship;
import eightman.library.GUI.System.TaskForce;

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
            List<Fleet> fleets = parser.parseNavalData();

            for (Fleet fleet : fleets) {
                DefaultMutableTreeNode fleetNode = new DefaultMutableTreeNode("Fleet: " + fleet.name);
                root.add(fleetNode);

                for (TaskForce taskForce : fleet.taskForces) {
                    DefaultMutableTreeNode taskForceNode = new DefaultMutableTreeNode("Task Force: " + taskForce.name);
                    fleetNode.add(taskForceNode);

                    for (Ship ship : taskForce.ships) {
                        DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode("Ship: " + ship.name);
                        taskForceNode.add(shipNode);

                        DefaultMutableTreeNode definitionNode = new DefaultMutableTreeNode("definition: " + ship.definition);
                        shipNode.add(definitionNode);

                        DefaultMutableTreeNode versionNode = new DefaultMutableTreeNode("version: " + ship.versionName);
                        shipNode.add(versionNode);
                    }
                }
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

    public void showGUI() {
        frame.setVisible(true);
    }
}