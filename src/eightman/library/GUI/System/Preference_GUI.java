package eightman.library.GUI.System;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;
import static eightman.library.GUI.System.MT_core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static eightman.library.GUI.Main_GUI.*;
import static eightman.library.GUI.language.*;

public class Preference_GUI extends JFrame {
    public static JComboBox<String> languageComboBox;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private static final Logger logger = Logger.getLogger(Preference_GUI.class.getName());
    public Preference_GUI() {
        super(PREF);
        setLayout(new BorderLayout());
        setupLayout();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultLanguage();

        Main_GUI mainGUI = Main_GUI.getInstance();
        mainGUI.dispose();

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

    public static void setLanguage(String language) {
        languageComboBox.setSelectedItem(language);
    }

    private void setDefaultLanguage() {
        if (Main_GUI.L_mode == 1) {
            languageComboBox.setSelectedItem(language.ENGLISH);
        } else if (Main_GUI.L_mode == 0) {
            languageComboBox.setSelectedItem(language.JAPANESE);
        }
    }

    private void setupLayout() {
        setSize(600, 350);
        // レイアウトの設定、ボタンの追加、設定項目の追加などのコード
        // 設定項目選択のボタン群
        setupPanel();
    }

    private void setupPanel() {
        // ボタンパネルの設定
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton modPathButton = new JButton("Mod Path");
        JButton languageButton = new JButton(Loc_Pref);
        JButton fontButton = new JButton(FONT);
        JButton otherButton = new JButton("その他");

        // ボタンのサイズを縦30pxに設定
        Dimension buttonSize = new Dimension(120, 20);
        languageButton.setPreferredSize(buttonSize);
        fontButton.setPreferredSize(buttonSize);
        otherButton.setPreferredSize(buttonSize);

        // ボタンをパネルに追加
        buttonPanel.add(modPathButton);
        buttonPanel.add(languageButton);
        buttonPanel.add(fontButton);
        buttonPanel.add(otherButton);

        // 設定項目
        JPanel languagePanel = new JPanel();
        String[] languages = {language.ENGLISH, language.JAPANESE};
        languageComboBox = new JComboBox<>(languages);
        languagePanel.add(languageComboBox);

        JPanel fontPanel = new JPanel(new BorderLayout());
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        JComboBox<String> fontComboBox = new JComboBox<>(fontNames);
        JLabel sampleText = new JLabel("Sample Text");
        JButton applyButton = new JButton("適用");

        fontComboBox.addActionListener(e -> {
            String selectedFont = (String) fontComboBox.getSelectedItem();
            sampleText.setFont(new Font(selectedFont, Font.PLAIN, 16));
        });

        applyButton.addActionListener(e -> {
            String selectedFont = (String) fontComboBox.getSelectedItem();
            sampleText.setFont(new Font(selectedFont, Font.PLAIN, 16));

            // フォント設定を保存
            saveSetting(selectedFont);

            // 全てのGUIにフォントを適用
            applyFontToAllGUI(selectedFont);

            JOptionPane.showMessageDialog(this, "フォントが適用されました: " + selectedFont);
        });

        fontPanel.add(fontComboBox, BorderLayout.NORTH);
        fontPanel.add(sampleText, BorderLayout.CENTER);
        fontPanel.add(applyButton, BorderLayout.SOUTH);

        JPanel otherPanel = new JPanel();
        // modのpath指定の設定項目
        JPanel modPathPanel = new JPanel();
        modPathPanel.add(new JScrollPane(modPathList));
        JButton addModPathButton = new JButton("Add");
        addModPathButton.addActionListener(e -> {
            if (modPathMap == null) {
                modPathMap = new HashMap<>();
            }
            String modName = JOptionPane.showInputDialog("Enter mod name:");
            String modPath = JOptionPane.showInputDialog("Enter mod path:");
            modPathListModel.addElement(modName + ": " + modPath);
            modPathMap.put(modName, modPath);
        });
        modPathPanel.add(addModPathButton);
        JButton removeModPathButton = new JButton("Remove");
        removeModPathButton.addActionListener(e -> {
            String selectedModPath = modPathList.getSelectedValue();
            if (selectedModPath != null) {
                modPathListModel.removeElement(selectedModPath);
                String modName = selectedModPath.split(": ")[0];
                modPathMap.remove(modName);
            }
        });
        modPathPanel.add(removeModPathButton);

        cardPanel.add(modPathPanel, "Mod Path");
        cardPanel.add(languagePanel, Loc_Pref);
        cardPanel.add(fontPanel, FONT);
        cardPanel.add(otherPanel, "その他");

        // ボタンのアクションリスナー
        modPathButton.addActionListener(e -> cardLayout.show(cardPanel, "Mod Path"));
        languageButton.addActionListener(e -> cardLayout.show(cardPanel, Loc_Pref));
        fontButton.addActionListener(e -> cardLayout.show(cardPanel, FONT));
        otherButton.addActionListener(e -> cardLayout.show(cardPanel, "その他"));

        // 区切り線の追加
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setPreferredSize(new Dimension(0, 1));

        // 設定保存ボタンの追加
        JButton saveSettingsButton = new JButton(SAVE_SETTINGS);
        saveSettingsButton.addActionListener(e -> {
            // 言語設定を保存
            String selectedLanguage = (String) languageComboBox.getSelectedItem();
            if (language.ENGLISH.equals(selectedLanguage)) {
                Main_GUI.L_mode = 1;
                set_language();
            } else if (language.JAPANESE.equals(selectedLanguage)) {
                Main_GUI.L_mode = 0;
                set_language();
            }

            // フォント設定を保存
            String selectedFont = (String) fontComboBox.getSelectedItem();
            saveSetting(selectedFont);

            // 全てのGUIにフォントを適用
            applyFontToAllGUI(selectedFont);

            Main_GUI.repainting();
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(separator, BorderLayout.NORTH);
        bottomPanel.add(saveSettingsButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void set_language() {
        if (Main_GUI.L_mode == 1) {
            languageComboBox.setSelectedItem(language.ENGLISH);
        } else if (Main_GUI.L_mode == 0) {
            languageComboBox.setSelectedItem(language.JAPANESE);
        }
    }

    private void saveSetting(String font) {
        Map<String, Object> settings = new HashMap<>();
        if (modPathMap != null) {
            settings.put("modPathMap", modPathMap);
        }
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        settings.put("lang", selectedLanguage);
        settings.put("font", font);
        settings.put("runCount_h", runtime_h); // runtime_hを追加
        settings.put("runCount", run); // runtimeを追加

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(settings);
        Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
        try {
            Files.createDirectories(configPath.getParent());
            try (FileWriter writer = new FileWriter(configPath.toString())) {
                writer.write(json);
            }
        } catch (IOException e) {
            MT_System.out.println("設定の保存に失敗しました。");
            logger.log(Level.SEVERE, "設定の保存に失敗しました。", e);
        }
    }

    private void applyFontToAllGUI(String font) {
        Font newFont = new Font(font, Font.PLAIN, 12);
        UIManager.put("Label.font", newFont);
        UIManager.put("Button.font", newFont);
        UIManager.put("ComboBox.font", newFont);
        UIManager.put("TextField.font", newFont);
        UIManager.put("TextArea.font", newFont);
        UIManager.put("Panel.font", newFont);
        SwingUtilities.updateComponentTreeUI(this);
    }

}