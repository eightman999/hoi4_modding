package eightman.library.GUI.GFX;

import eightman.library.Core;
import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;

import static eightman.library.GUI.language.Title;
import static java.awt.event.KeyEvent.VK_CONTROL;

public class Goals_GUI extends JFrame implements ActionListener {

    JTextArea textArea_s = new JTextArea();
    language l = new language();
    JPanel p = new JPanel();
    JLabel path = new JLabel("");
    String file_path = "";
    String file_name = "00_Goals.gfx";
    JTextField texturefile_path = new JTextField("texturefile_path", 15);
    JTextField fn = new JTextField("File Path", 15);
    JTextField fnm = new JTextField("File name", 15);
    String temp = "#" + language.CCC + "\n" + "spriteTypes = {\n";

    public void goals_GUI() {
        Goals_GUI Lframe = new Goals_GUI();
        Lframe.setLocationRelativeTo(null);
        Lframe.setVisible(true);
    }

    public Goals_GUI() {
        setBounds(100, 100, 660, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuSave = new JMenuItem("Save");
        JMenuItem menuExit = new JMenuItem("Exit");
        JMenu menuView = new JMenu("View");
        JCheckBoxMenuItem menuTool = new JCheckBoxMenuItem("Text import mode ");
        JMenu menuSize = new JMenu("Size");
        menuFile.setMnemonic('F');
        menuSave.setMnemonic(VK_CONTROL + 'S');
        menuExit.setMnemonic('x');
        menuView.setMnemonic('V');
        menuTool.setMnemonic('T');
        menuSize.setMnemonic('S');
        textArea_s.setWrapStyleWord(true);
        textArea_s.setLineWrap(false);
        JScrollPane scrollPane = new JScrollPane(textArea_s);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        scrollPane.setPreferredSize(new Dimension(500, 1000));
        menuExit.addActionListener(this);
        menuTool.addActionListener(this);

        getRootPane().setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuFile.add(menuSave);
        menuFile.add(menuExit);
        menuBar.add(menuView);
        menuView.add(menuTool);
        add(p1);
        JLabel label = new JLabel("Path : ");

        p1.setLayout(null);
        JLabel label1 = new JLabel(".gfx");
        //        label1.setBounds(465,10,40,20);
        label.setBounds(120, 10, 40, 20);
        path.setBounds(120, 50, 340, 20);
        texturefile_path.setBounds(120, 35, 340, 20);
        JButton done = new JButton(language.DONE);
        JButton load = new JButton("Load&Save");
        done.setBounds(505, 10, 80, 20);
        load.setBounds(505, 50, 80, 20);
        fn.setBounds(160, 10, 300, 20);
        fnm.setBounds(160, 75, 300, 20);
        p1.add(fnm);
        p1.add(done);
        p1.add(label1);
        p1.add(label);
        p1.add(fn);
        p1.add(path);
        p1.add(load);

        p1.add(texturefile_path);
        done.addActionListener(new DoneActionListener());
        load.addActionListener(new LoadActionListener());
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
                                    "\t\tSpriteType = {\n\t\t\tname = \"GFX_" +
                                    fileName.substring(0, index) +
                                    "\"";
                    temp =
                            temp +
                                    "\n\t\t\ttexturefile = \"" +
                                    texturefile_path.getText() +
                                    "/" +
                                    list[i].getName() +
                                    "\"";
                    temp = temp + "\n\t\t}\n";

                    temp =
                            temp +
                                    "\t\tSpriteType = {\n\t\t\tname = \"GFX_" +
                                    fileName.substring(0, index) +
                                    "_shine\"";
                    temp =
                            temp +
                                    "\n\t\t\ttexturefile = \"" +
                                    texturefile_path.getText() +
                                    "/" +
                                    list[i].getName() +
                                    "\"";
                    temp = temp + "\n\t\t\teffectFile = \"gfx/FX/buttonstate.lua\"\n";
                    temp =
                            temp +
                                    "\n\t\t\tanimation = {\n\t\t\t\tanimationmaskfile = \"" +
                                    list[i].getName() +
                                    "\"";
                    temp =
                            temp +
                                    "\n\t\t\t\tanimationtexturefile = \"gfx/interface/goals/shine_overlay.dds\"\n";
                    temp =
                            temp +
                                    "\t\t\t\tanimationrotation = -90.0\n" +
                                    "\t\t\t\tanimationlooping = no\n" +
                                    "\t\t\t\tanimationtime = 0.75\n" +
                                    "\t\t\t\tanimationdelay = 0\n" +
                                    "\t\t\t\tanimationblendmode = \"add\"\n" +
                                    "\t\t\t\tanimationtype = \"scrolling\"\n" +
                                    "\t\t\t\tanimationrotationoffset = { x = 0.0 y = 0.0 }\n" +
                                    "\t\t\t\tanimationtexturescale = { x = 1.0 y = 1.0 } \n" +
                                    "\t\t\t}";
                    temp =
                            temp +
                                    "\n\t\t\tanimation = {\n\t\t\t\tanimationmaskfile = \"" +
                                    list[i].getName() +
                                    "\"";
                    temp =
                            temp +
                                    "\n\t\t\t\tanimationtexturefile = \"gfx/interface/goals/shine_overlay.dds\"\n";
                    temp =
                            temp +
                                    "\t\t\t\tanimationrotation = 90.0\n" +
                                    "\t\t\t\tanimationlooping = no\n" +
                                    "\t\t\t\tanimationtime = 0.75\n" +
                                    "\t\t\t\tanimationdelay = 0\n" +
                                    "\t\t\t\tanimationblendmode = \"add\"\n" +
                                    "\t\t\t\tanimationtype = \"scrolling\"\n" +
                                    "\t\t\t\tanimationrotationoffset = { x = 0.0 y = 0.0 }\n" +
                                    "\t\t\t\tanimationtexturescale = { x = 1.0 y = 1.0 } \n" +
                                    "\t\t}\n";
                    temp = temp + "\t\t\tlegacy_lazy_load = no\n" + "\t\t\t}\n";
                } else if (list[i].isDirectory()) { //ディレクトリの場合
                    //何もしない
                }
            }
            temp = temp + "}\n";
            try {
                FileOutputStream fos = new FileOutputStream(
                        "/" + file_path + "/" + fnm.getText() + ".gfx"
                );
                Core.out.println("/" + file_path + "/" + fnm.getText() + ".gfx");
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                osw.write(temp);
                osw.close();
                fos.close();
            } catch (IOException er) {
                Core.out.logError("Failed to load config.", er);
            }
            temp = "#" + language.CCC + "\n" + "spriteTypes = {\n";
        }
    }
}
