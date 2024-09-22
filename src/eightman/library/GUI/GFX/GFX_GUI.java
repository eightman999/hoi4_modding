package eightman.library.GUI.GFX;

import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;
import eightman.library.GUI.System.MT_core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import static eightman.library.GUI.language.Title;
import static java.awt.event.KeyEvent.VK_CONTROL;

public class GFX_GUI extends JFrame {


    private JTextArea textArea_s = new JTextArea();
    private String file_name = "00_GFX.gfx";
    private language l = new language();
    private JPanel p = new JPanel();
    private JLabel path = new JLabel("");
    private String file_path = "";
    private JTextField texture_file_path = new JTextField("", 15);
    private JTextField fn = new JTextField("File Path", 15);
    private JTextField fnm = new JTextField("File name", 15);
    private String temp = "#" + language.CCC + "\n" + "spriteTypes = {\n";

    public GFX_GUI() {
        setupFrame();
        setupMenu();
        setupPanel();
//        setupButtons();
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

    private void setupFrame() {
        setBounds(100, 100, 660, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(p, BorderLayout.CENTER);
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.setMnemonic(KeyEvent.VK_CONTROL + 'S');
        menuBar.add(menuFile);
        menuFile.add(menuSave);
        getRootPane().setJMenuBar(menuBar);
    }

    private void setupPanel() {
        JPanel p1 = new JPanel();
        add(p1);
        p1.setLayout(null);
        JLabel label = new JLabel("Path : ");
        label.setBounds(120, 10, 40, 20);
        path.setBounds(120, 50, 340, 20);
        texture_file_path.setBounds(120, 35, 340, 20);
        fn.setBounds(160, 10, 300, 20);
        JButton done = new JButton(language.DONE);
        done.setBounds(505, 10, 80, 20);
        done.addActionListener(e -> updateFilePath());
        JButton load = new JButton("Load&Save");
        load.setBounds(505, 50, 80, 20);
        load.addActionListener(e -> loadAndSave());
        p1.add(done);
        p1.add(load);
        p1.add(label);
        p1.add(fn);
        p1.add(path);
        p1.add(texture_file_path);
    }



    private void updateFilePath() {
        file_path = fn.getText();
        path.setText("file Path : " + file_path);
    }
    public void loadAndSave() {
        File dir = new File(file_path);
        File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    int index = fileName.lastIndexOf(".");
                    if (index > 0) {
                        String baseName = fileName.substring(0, index);
                        temp += "\tSpriteType = {\n\t\tname = \"GFX_" + baseName + "\"\n\t}\n";
                    }
                }
            }
        }
        temp += "}\n";

        try (FileOutputStream fos = new FileOutputStream(file_path + "/" + fnm.getText() + ".gfx");
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
            osw.write(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reset temp for future use
        temp = "#" + language.CCC + "\n" + "spriteTypes = {\n";
    }
}
