package eightman.library.GUI.GFX;

import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import static eightman.library.GUI.language.Title;

public class NV_MOD_GFX_GUI extends JFrame implements ActionListener {

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
        NV_MOD_GFX_GUI L_frame = new NV_MOD_GFX_GUI();
        L_frame.setLocationRelativeTo(null);
        L_frame.setVisible(true);
    }

    public NV_MOD_GFX_GUI() {
        setBounds(100, 100, 660, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu(language.File);
        JMenuItem menuSave = new JMenuItem(language.Save);
        JMenuItem menuExit = new JMenuItem("Exit");
        JMenu menuView = new JMenu("View");
        JCheckBoxMenuItem menuTool = new JCheckBoxMenuItem("Text import mode ");
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
        done.addActionListener(new NV_MOD_GFX_GUI.DoneActionListener());
        load.addActionListener(new NV_MOD_GFX_GUI.LoadActionListener());
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
        }
    }
}
