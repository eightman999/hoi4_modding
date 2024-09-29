// FleetDesigner_GUI.java
package eightman.library.GUI.GUI_tool;

import eightman.library.GUI.System.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static eightman.library.GUI.Main_GUI.naval_path;

public class FleetDesigner_GUI {

    private JFrame frame;
    private JTree tree;
    private JPanel detailsPanel;

    public FleetDesigner_GUI() {
        frame = new JFrame("Fleet Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Navy");

        try {
            String content = new String(Files.readAllBytes(Paths.get(naval_path)));
            Lexer lexer = new Lexer(content);
            NavalParser parser = new NavalParser(lexer);
            List<Fleet> fleets = parser.parse();

            for (Fleet fleet : fleets) {
                DefaultMutableTreeNode fleetNode = findOrCreateNode(root, "Fleet: " + fleet.getName());
                for (TaskForce taskForce : fleet.getTaskForces()) {
                    DefaultMutableTreeNode taskForceNode = findOrCreateNode(fleetNode, "Task Force: " + taskForce.getName());
                    for (Ship ship : taskForce.getShips()) {
                        findOrCreateNode(taskForceNode, "Ship: " + ship.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        JLabel detailsLabel = new JLabel("Select a node to see details");
        detailsPanel.add(detailsLabel, BorderLayout.CENTER);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) return;
                detailsLabel.setText(node.toString());
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, detailsPanel);
        splitPane.setDividerLocation(300);

        frame.getContentPane().add(splitPane);
    }

    private DefaultMutableTreeNode findOrCreateNode(DefaultMutableTreeNode parent, String nodeName) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (node.getUserObject().equals(nodeName)) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FleetDesigner_GUI gui = new FleetDesigner_GUI();
            gui.showGUI();
        });
    }
}