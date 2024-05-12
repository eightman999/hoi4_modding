package eightman.library.GUI.MODULE;

import com.fasterxml.jackson.databind.ObjectMapper;
import eightman.library.GUI.System.MT_core.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static eightman.library.GUI.Main_GUI.modPathMap;

public class module_maker_GUI extends JFrame {
    private JComboBox<String> modNameDropdown;
    private JLabel modPathLabel;
    private JLabel loadingLabel;
    private List<Module> modules = new ArrayList<>();
    private String modpath;
    private String modName;
    private JList<String> moduleList;
    private JScrollPane listScrollPane;
    public void module_maker_GUI() {
        setupFrame();
        setupgui();
        setupAnimationLabel();
        setVisible(true);
    }

    private void setupFrame() {
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupgui() {
        JPanel topPanel = new JPanel(new BorderLayout());

        modNameDropdown = new JComboBox<>(modPathMap.keySet().toArray(new String[0]));
        modNameDropdown.addActionListener(e -> updateModPathLabel());
        topPanel.add(modNameDropdown, BorderLayout.WEST);

        modPathLabel = new JLabel();
        topPanel.add(modPathLabel, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> new Thread(this::loading_animation).start());
        topPanel.add(loadButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        moduleList = new JList<>();
        moduleList.setPreferredSize(new Dimension(700, 600));
        listScrollPane = new JScrollPane(moduleList);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane (containing the list) to the frame
        add(listScrollPane, BorderLayout.CENTER);
    }

    private void updateModPathLabel() {
        String selectedModName = (String) modNameDropdown.getSelectedItem();
        modName = selectedModName;
        String modPath = modPathMap.get(selectedModName);
        modpath = modPath;
        modPathLabel.setText(modPath);
    }

    private void setupAnimationLabel() {
        ImageIcon loadingIcon = new ImageIcon("./images/Glass lines.gif");
        loadingLabel = new JLabel(loadingIcon);
        loadingLabel.setVisible(false);
        JPanel animationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        animationPanel.add(loadingLabel);
        add(animationPanel, BorderLayout.PAGE_END);
    }

    private void loading_animation() {
        // アニメーションを表示
        loadingLabel.setVisible(true);

        try {
            // ファイルの読み込みと解析
            MT_System.out.println(modpath+"/common/units/equipment/modules");

            List<File> files = loadFilesInDirectory(modpath+"/common/units/equipment/modules");
//            MT_System.out.println("Loaded " + files.size() + " files...");
            for (File file : files) {
                String content = readFileContent(file.getPath());

                //TODO: ファイルの内容を解析してModuleオブジェクトを生成する!!!
                
//                ModuleProcessor processor = new ModuleProcessor(modpath, modName);
//                processor.processModules();
                // cacheModulesAsJson(modules); // キャッシュ生成のコードを削除
//                MT_System.out.println("Loaded " + files.size() + " files...");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ロード処理が完了したらアニメーションを非表示にする
        loadingLabel.setVisible(false);
    }


    private List<File> loadFilesInDirectory(String directoryPath) throws IOException {
        Path path = Path.of(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        List<File> files = Files.walk(path)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".txt"))
                .map(Path::toFile)
                .sorted()
                .collect(Collectors.toList());

        for (File file : files) {
            MT_System.out.println("Finished loading file: " + file.getPath());
        }

        if (files.isEmpty()) {
            MT_System.out.println("No files loaded from directory: " + directoryPath);
        }

        return files;
    }

    private String readFileContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }




}