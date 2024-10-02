// FleetDesigner_GUI.java
package eightman.library.GUI.GUI_tool;

import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.System.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
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

    public FleetDesigner_GUI() {
        frame = new JFrame("Fleet Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Mod選択プルダウンと読み込み開始ボタンを作成
        modComboBox = new JComboBox<>(modList.toArray(new String[0]));
        modComboBox.addActionListener(e -> updateModPathLabel());
        loadButton = new JButton("読み込み開始");
        loadButton.addActionListener(e -> LoadingModsNaval());

        // 上部パネルに追加
        JPanel topPanel = new JPanel();
        topPanel.add(modComboBox);
        topPanel.add(loadButton);
        // ファイル名をルートノードの名前として設定
        String fileName = Paths.get(naval_path).getFileName().toString();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileName);

        try {
            String content = new String(Files.readAllBytes(Paths.get(naval_path)));
            Lexer lexer = new Lexer(content);
            NavalParser parser = new NavalParser(lexer);
            List<Fleet> fleets = parser.parse();

            for (Fleet fleet : fleets) {
                DefaultMutableTreeNode fleetNode = findOrCreateNode(root, fleet.getName());
                for (TaskForce taskForce : fleet.getTaskForces()) {
                    DefaultMutableTreeNode taskForceNode = findOrCreateNode(fleetNode,  taskForce.getName());
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

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    new Main_GUI(Title).setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void LoadingModsNaval() {
        // modpath/history/unitsディレクトリ内の*_mtg_naval.txtファイルを列挙
        String FolderPath = modpath + "/history/units";
        System.out.println("FolderPath=" + FolderPath);
        File unitDir = new File(FolderPath);
        File[] navalFiles = unitDir.listFiles((dir, name) ->
                name.endsWith("_naval_mtg") || name.endsWith("_navy") || name.endsWith("_naval")
        );

        if (navalFiles == null || navalFiles.length == 0) {
            JOptionPane.showMessageDialog(frame, "No naval files found in the specified directory.");
            return;
        }

        // 既存のツリーを削除
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        root.removeAllChildren();

        // 各ファイルを解析してツリーを生成
        for (File navalFile : navalFiles) {
            try {
                String content = new String(Files.readAllBytes(navalFile.toPath()));
                Lexer lexer = new Lexer(content);
                NavalParser parser = new NavalParser(lexer);
                List<Fleet> fleets = parser.parse();

                for (Fleet fleet : fleets) {
                    DefaultMutableTreeNode fleetNode = findOrCreateNode(root, fleet.getName());
                    for (TaskForce taskForce : fleet.getTaskForces()) {
                        DefaultMutableTreeNode taskForceNode = findOrCreateNode(fleetNode, taskForce.getName());
                        for (Ship ship : taskForce.getShips()) {
                            findOrCreateNode(taskForceNode, "Ship: " + ship.getName());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ツリーのモデルを更新
        ((DefaultTreeModel) tree.getModel()).reload();
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

    private void updateModPathLabel() {
        String selectedModName = (String) modComboBox.getSelectedItem();
        modName = selectedModName;
        System.out.println("selectedModName=" + selectedModName);
        String modPath = modPathMap.get(selectedModName);
        modpath = modPath;
        System.out.println("modPath=" + modPath);
//        modPathLabel.setText(modPath);
    }
}