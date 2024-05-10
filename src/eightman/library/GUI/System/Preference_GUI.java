package eightman.library.GUI.System;


import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;

import javax.swing.*;
import java.awt.*;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import static eightman.library.GUI.Main_GUI.*;

import static eightman.library.GUI.language.*;

public class Preference_GUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    public static JComboBox<String> languageComboBox;

    public Preference_GUI() {
        super(PREF); // タイトルを設定
        setLayout(new BorderLayout());
        setupLayout();
        setLocationRelativeTo(null);
        setVisible(true);

        // L_modeに基づいてlanguageComboBoxのデフォルト値を設定
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
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton modPathButton = new JButton("Mod Path");
        JButton languageButton = new JButton(Loc_Pref);
        JButton fontButton = new JButton(FONT);
        JButton otherButton = new JButton("その他");

        // ボタンのサイズを縦30pxに設定
        Dimension buttonSize = new Dimension(100, 20);
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

        JPanel fontPanel = new JPanel();
        // フォント設定のコードをここに追加

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

            Main_GUI.repainting();
            saveModPathSettings();
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(separator, BorderLayout.NORTH);
        bottomPanel.add(saveSettingsButton, BorderLayout.EAST);

        add(buttonPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    private void saveModPathSettings() {
        Map<String, Object> settings = new HashMap<>();
        if (modPathMap != null) {
            settings.put("modPathMap", modPathMap);
        }
        // 言語設定を取得して保存
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        settings.put("lang", selectedLanguage);

        Gson gson = new Gson();
        String json = gson.toJson(settings);
        Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
        try {
            Files.createDirectories(configPath.getParent()); // ディレクトリが存在しない場合は作成
            try (FileWriter writer = new FileWriter(configPath.toString())) {
                writer.write(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLanguage(String language) {
        languageComboBox.setSelectedItem(language);
    }


}