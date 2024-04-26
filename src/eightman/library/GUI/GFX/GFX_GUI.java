package eightman.library.GUI.GFX;

import eightman.library.GUI.language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.awt.event.KeyEvent.VK_CONTROL;

public class GFX_GUI extends JFrame implements ActionListener {

    JTextArea textArea_s = new JTextArea();
    String file_name = "00_GFX.gfx";
    language l = new language();
    JPanel p = new JPanel();
    JLabel path = new JLabel("");
    String file_path = "";
    JTextField texture_file_path = new JTextField("", 15);
    JTextField fn = new JTextField("File Path", 15);
    JTextField fnm = new JTextField("File name", 15);
    String temp = "#" + language.CCC + "\n" + "spriteTypes = {\n";

    public void gfx_GUI() {
        GFX_GUI Lframe = new GFX_GUI();
        Lframe.setLocationRelativeTo(null);
        Lframe.setVisible(true);
    }

    public GFX_GUI() {
        setBounds(100, 100, 660, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuSave = new JMenuItem("Save");

        menuSave.setMnemonic(VK_CONTROL + 'S');
        textArea_s.setWrapStyleWord(true);
        textArea_s.setLineWrap(false);
        JScrollPane scrollPane = new JScrollPane(textArea_s);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        scrollPane.setPreferredSize(new Dimension(500, 1000));

        getRootPane().setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuFile.add(menuSave);

        add(p1);
        JLabel label = new JLabel("Path : ");

        p1.setLayout(null);
        JLabel label1 = new JLabel(".gfx");
        //        label1.setBounds(465,10,40,20);
        label.setBounds(120, 10, 40, 20);
        path.setBounds(120, 50, 340, 20);
        texture_file_path.setBounds(120, 35, 340, 20);
        JButton done = new JButton(language.DONE);
        JButton load = new JButton("Load&Save");
        done.setBounds(505, 10, 80, 20);
        load.setBounds(505, 50, 80, 20);
        fn.setBounds(160, 10, 300, 20);
        p1.add(done);
        p1.add(label1);
        p1.add(label);
        p1.add(fn);
        p1.add(path);
        p1.add(load);
        p1.add(texture_file_path);
        fnm.setBounds(160, 75, 300, 20);
        p1.add(fnm);
        done.addActionListener(new GFX_GUI.DoneActionListener());
        load.addActionListener(new GFX_GUI.LoadActionListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    class DoneActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            file_path = fn.getText();
            path.setText("file Path : " + file_path);
        }
    }

    class LoadActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            File dir = new File(file_path);
            File[] list = dir.listFiles();
            for (int i = 0; i < list.length; i++) {
                if (list[i].isFile()) { //ファイルの場合
                    System.out.println(list[i].getName());
                    String fileName = list[i].getName();
                    System.out.println(fileName);
                    int index = fileName.lastIndexOf(".");
                    temp =
                            temp +
                                    "\t\tSpriteType = {\n\t\tname = \"GFX_" +
                                    fileName.substring(0, index) +
                                    "\"";
                    temp =
                            temp +
                                    "\n\t\t\ttexturefile = \"" +
                                    texture_file_path.getText() +
                                    "/" +
                                    list[i].getName() +
                                    "\"";
                    temp = temp + "\n\t\t}\n";
                } else if (list[i].isDirectory()) { //ディレクトリの場合
                    //何もしない
                }
            }
            temp = temp + "}\n";
            try {
                FileOutputStream fos = new FileOutputStream(
                        "/" + file_path + "/" + fnm.getText() + ".gfx"
                );
                System.out.println("/" + file_path + "/" + fnm.getText() + ".gfx");
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                osw.write(temp);
                osw.close();
                fos.close();
            } catch (IOException er) {
                System.out.println(er);
            }
        }
    }
}
