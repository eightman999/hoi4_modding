package eightman.library.GUI.GUI_tool;

import eightman.library.Core;
import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.System.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static eightman.library.GUI.Main_GUI.*;
import static eightman.library.GUI.language.Title;

public class FleetDesigner_GUI {

    private JFrame frame;
    private JTree tree;
    private JPanel detailsPanel;
    private JComboBox<String> modComboBox;
    private JButton loadButton;
    private String modName;
    private String modpath;
    private File tempFile;
    private JLabel detailsLabel; // Define detailsLabel here

    public FleetDesigner_GUI() {
        Core.out.println("Fleet Designer GUI is now opening");
        frame = new JFrame("Fleet Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        modComboBox = new JComboBox<>(modList.toArray(new String[0]));
        modComboBox.addActionListener(e -> updateModPathLabel());
        loadButton = new JButton("読み込み開始");
        loadButton.addActionListener(e -> LoadingModsNaval());
        Core.out.println("set up the modComboBox and loadButton");
        JPanel topPanel = new JPanel();
        topPanel.add(modComboBox);
        topPanel.add(loadButton);

        String fileName = Paths.get(naval_path).getFileName().toString();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileName);
        Core.out.println("set up the root node");
        try {
            Core.out.println("Reading file: " + naval_path);
            String content = new String(Files.readAllBytes(Paths.get(naval_path)));
            Core.out.println("File read successfully, initializing lexer and parser.");

            Core.out.println("Initializing Lexer");
            Lexer lexer = new Lexer(content);
            Core.out.println("Lexer initialized successfully");

            Core.out.println("Initializing Parser");
            NavalParser parser = new NavalParser(lexer);
            Core.out.println("Parser initialized successfully");

            Core.out.println("Starting parsing process");
            Units units = parser.parse();
            Core.out.println("Parsing completed, processing units.");

            for (Fleet fleet : units.getFleets()) {
                Core.out.println("Setting up fleet node: " + fleet.getName());
                DefaultMutableTreeNode fleetNode = new DefaultMutableTreeNode(fleet);
                root.add(fleetNode);
                for (TaskForce taskForce : fleet.getTaskForces()) {
                    Core.out.println("Setting up task force node: " + taskForce.getName());
                    DefaultMutableTreeNode taskForceNode = new DefaultMutableTreeNode(taskForce);
                    fleetNode.add(taskForceNode);
                    for (Ship ship : taskForce.getShips()) {
                        Core.out.println("Setting up ship node: " + ship.getName());
                        DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(ship);
                        taskForceNode.add(shipNode);
                        for (Equipment equipment : ship.getEquipmentList()) {
                            Core.out.println("Setting up equipment node: " + equipment.getOwner());
                            DefaultMutableTreeNode equipmentNode = new DefaultMutableTreeNode(equipment);
                            shipNode.add(equipmentNode);
                        }
                    }
                }
            }
            Core.out.println("All nodes set up successfully.");
        } catch (IOException e) {
            Core.ERROR();
            e.printStackTrace();
        }
        Core.out.println("set up the tree nodes");
        tree = new JTree(root);
        tree.setDragEnabled(true);
        tree.setDropMode(DropMode.ON_OR_INSERT);
        tree.setTransferHandler(new TreeTransferHandler());
        JScrollPane treeScrollPane = new JScrollPane(tree);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsLabel = new JLabel("Select a node to see details"); // Initialize detailsLabel
        detailsPanel.add(detailsLabel, BorderLayout.CENTER);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) return;

                Object userObject = node.getUserObject();

                if (userObject instanceof Fleet) {
                    displayFleetDetails((Fleet) userObject);
                } else if (userObject instanceof TaskForce) {
                    displayTaskForceDetails((TaskForce) userObject);
                } else if (userObject instanceof Ship) {
                    displayShipDetails((Ship) userObject);
                } else {
                    "Select a node to see details".equals(detailsLabel.getText());
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, detailsPanel);
        splitPane.setDividerLocation(300);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new Main_GUI(Title).setVisible(true);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        File tempDir = new File("Hoi4_modding_Tool/temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        try {
            tempFile = File.createTempFile("fleet", ".ftmp", tempDir);
            tempFile.deleteOnExit();

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("units = {\n");
                for (Fleet fleet : Units.getFleets()) {
                    writer.write("\tfleet = {\n");
                    writer.write("\t\tname = \"" + fleet.getName() + "\"\n");
                    writer.write("\t\tnaval_base = " + fleet.getNavalBase() + "\n");

                    for (TaskForce taskForce : fleet.getTaskForces()) {
                        writer.write("\t\ttask_force = {\n");
                        writer.write("\t\t\tname = \"" + taskForce.getName() + "\"\n");
                        writer.write("\t\t\tlocation = " + taskForce.getLocation() + "\n");
                        for (Ship ship : taskForce.getShips()) {
                            writer.write("\t\t\tship = {\n");
                            writer.write("\t\t\t\tname = \"" + ship.getName() + "\"\n");
                            writer.write("\t\t\t\tdefinition = \"" + ship.getDefinition() + "\"\n");
                            writer.write("\t\t\t\tstart_experience_factor = " + ship.getStartExperienceFactor() + "\n");
                            writer.write("\t\t\t\tequipment = {\n");
                            for (Equipment equipment : ship.getEquipmentList()) {
                                writer.write("\t\t\t\t\t" + equipment.getShipHull() + " = {\n");
                                writer.write("\t\t\t\t\t\tamount = " + equipment.getAmount() + "\n");
                                writer.write("\t\t\t\t\t\towner = \"" + equipment.getOwner() + "\"\n");
                                writer.write("\t\t\t\t\t\tversion_name = \"" + equipment.getVersionName() + "\"\n");
                                writer.write("\t\t\t\t\t}\n");
                            }
                            writer.write("\t\t\t\t}\n");
                            writer.write("\t\t\t}\n");
                        }
                        writer.write("\t\t}\n");
                    }
                    writer.write("\t}\n");
                }
                writer.write("    #変更日時: " + new Date() + "\n");
                writer.write("}\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateModPathLabel() {
    }

    private void LoadingModsNaval() {

    }

    private void displayFleetDetails(Fleet fleet) {
        detailsPanel.removeAll();
        JTextArea detailsLabel = new JTextArea();
        detailsLabel.setEditable(false);
        detailsLabel.setLineWrap(true);
        detailsLabel.setWrapStyleWord(true);
        detailsLabel.setText(
                "Name: " + fleet.getName() + "\n" +
                        "Naval Base: " + fleet.getNavalBase() + "\n" +
                        "Number of Task Forces: " + fleet.getTaskForces().size() + "\n"
        );
        detailsPanel.add(new JScrollPane(detailsLabel), BorderLayout.CENTER);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void displayTaskForceDetails(TaskForce taskForce) {
        detailsPanel.removeAll();
        JTextArea detailsLabel = new JTextArea();
        detailsLabel.setEditable(false);
        detailsLabel.setLineWrap(true);
        detailsLabel.setWrapStyleWord(true);
        detailsLabel.setText(
                "Name: " + taskForce.getName() + "\n" +
                        "Location: " + taskForce.getLocation() + "\n"
        );
        detailsPanel.add(new JScrollPane(detailsLabel), BorderLayout.CENTER);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void displayShipDetails(Ship ship) {
        detailsPanel.removeAll();
        JTextArea detailsLabel = new JTextArea();
        detailsLabel.setEditable(false);
        detailsLabel.setLineWrap(true);
        detailsLabel.setWrapStyleWord(true);
        detailsLabel.setText(
                "Name: " + ship.getName() + "\n" +
                        "Definition: " + ship.getDefinition() + "\n" +
                        "Ship_hull: " + ship.getShipHull() + "\n" +
                        "Start Experience Factor: " + ship.getStartExperienceFactor() + "\n" +
                        "Pride of the Fleet: " + ship.isPrideOfTheFleet() + "\n" +
                        "Version Name: " + ship.getEquipmentList().get(0).getVersionName() + "\n" +
                        "Owner: " + ship.getEquipmentList().get(0).getOwner() + "\n" +
                        "Amount: " + ship.getEquipmentList().get(0).getAmount() + "\n"
        );
        detailsPanel.add(new JScrollPane(detailsLabel), BorderLayout.CENTER);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }
    public void showGUI() {
        Core.out.logInfo("Fleet Designer GUI is now running");
        frame.setVisible(true);
    }
}