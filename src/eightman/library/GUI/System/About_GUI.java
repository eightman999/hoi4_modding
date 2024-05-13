package eightman.library.GUI.System;

import eightman.library.GUI.MainFrame;
import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;

import javax.swing.*;
import java.awt.*;

import static eightman.library.GUI.language.ABOUT;
import static eightman.library.GUI.language.Title;

public class About_GUI extends JFrame {
    private JLabel versionLabel = new JLabel();
    public About_GUI() {
        super(Title + ABOUT);
        setResizable(false);

        setLayout(new BorderLayout());
        setSize(300, 450);

        // アイコンのサイズを変更
        Image scaledIcon = Main_GUI.icon.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon), SwingConstants.CENTER);

        JLabel titleLabel = new JLabel(Title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 14));
        if(Main_GUI.Beta = true) {
            titleLabel.setText(titleLabel.getText() + " Beta " + Main_GUI.Version_beta+ "." +Main_GUI.run);
            versionLabel = new JLabel("Version : " + Main_GUI.Version + " β." + Main_GUI.Version_beta + "." + Main_GUI.run , SwingConstants.CENTER);
        }else{
            titleLabel.setText(titleLabel.getText() + " " + Main_GUI.Version);
            versionLabel = new JLabel("Version : " + Main_GUI.Version, SwingConstants.CENTER);
        }
        JLabel versiondate = new JLabel("Release Date : " + Main_GUI.Version_date_2, SwingConstants.CENTER);
        JLabel copyrightLabel = new JLabel("© eightman 2022-2024 \n All rights reserved", SwingConstants.CENTER);

        // パネルを作成してtitleLabelとversionLabelをグループ化
        JPanel centerPanel = new JPanel(new GridLayout(5, 1));
        centerPanel.add(iconLabel);
        centerPanel.add(titleLabel);
        centerPanel.add(versionLabel);
        centerPanel.add(versiondate);
        centerPanel.add(copyrightLabel);

        add(centerPanel, BorderLayout.CENTER); // パネルを追加

        setLocationRelativeTo(null);
        setVisible(true);
    }
}