package eightman.library.GUI.GFX;

import eightman.library.Core;
import eightman.library.GUI.Main_GUI;
import eightman.library.GUI.language;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;

import static eightman.library.GUI.language.Title;

public class SHIP_GFX_GUI extends JFrame implements ActionListener {

    JPanel p = new JPanel();
    String file_path = "";
    JLabel wIcon = new JLabel();
    JLabel BG = new JLabel();
    JTextField texture_file_path = new JTextField("", 15);
    JTextField fnm = new JTextField("File name", 15);
    JLabel impath = new JLabel("");
    BufferedImage wPic = null;
    BufferedImage BGpic = null;
    BufferedImage sloticon = null;
    BufferedImage slotlock = null;
    BufferedImage slot = null;
    String temp1 =
            "#It is described by eightman.library and software created by  eightman\n" +
                    "spriteTypes = {\n";
    // CSV読み込みボタンの追加
    JButton loadCSVButton = new JButton("Load CSV");

    Boolean Type_slot = true;
    Boolean PrArS = true;

    Boolean SecArS = true;
    Boolean PrSuArS = true;
    Boolean SecSuArS = true;

    Boolean PrLiArS = true;
    Boolean SecLiArS = true;

    JButton ship_type_slot = new JButton();
    JButton primary_armament_slot = new JButton();

    JButton secondary_armament_slot = new JButton();
    JButton primary_sub_armament_slot = new JButton();
    JButton secondary_sub_armament_slot = new JButton();

    JButton primary_light_armament_slot = new JButton();
    JButton secondary_light_armament_slot = new JButton();

    JLabel Speed = new JLabel("Speed:");
    JTextField Speed_in = new JTextField("");
    JLabel Kn = new JLabel("kn");

    public void gfx_GUI() {
        SHIP_GFX_GUI Lframe = new SHIP_GFX_GUI();
        Lframe.setLocationRelativeTo(null);
        Lframe.setVisible(true);
    }

    public SHIP_GFX_GUI() {
        setBounds(100, 100, 660, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel();

        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuSave = new JMenuItem("Save");

        getRootPane().setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuFile.add(menuSave);
        JButton select = new JButton("Select image");
        add(p1);
        try {
            wPic = ImageIO.read(this.getClass().getResource("icon.png"));
            BGpic = ImageIO.read(this.getClass().getResource("ship_designer_bg.png"));
            sloticon =
                    ImageIO.read(
                            this.getClass().getResource("equipment_module_bg_slot_QE.png")
                    );
            slotlock =
                    ImageIO.read(
                            this.getClass().getResource("equipment_module_bg_slot_LC.png")
                    );
            slot =
                    ImageIO.read(
                            this.getClass().getResource("equipment_module_bg_slot.png")
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon slot_icon = new ImageIcon(sloticon);
        ship_type_slot.setIcon(slot_icon);
        primary_armament_slot.setIcon(slot_icon);
        secondary_armament_slot.setIcon(slot_icon);
        primary_sub_armament_slot.setIcon(slot_icon);
        secondary_sub_armament_slot.setIcon(slot_icon);
        primary_light_armament_slot.setIcon(slot_icon);
        secondary_light_armament_slot.setIcon(slot_icon);
        wIcon.setIcon(new ImageIcon(wPic));
        BG.setIcon(new ImageIcon(BGpic));
        BG.setHorizontalAlignment(JLabel.CENTER);
        wIcon.setHorizontalAlignment(JLabel.CENTER);

        p1.setLayout(null);
        loadCSVButton.setBounds(500, 260, 100, 20);
        p1.add(loadCSVButton);
        BG.setBounds(60, 10, 540, 200);
        wIcon.setBounds(60, 10, 540, 200);
        select.setBounds(500, 225, 100, 20);
        impath.setBounds(60, 225, 400, 20);
        ship_type_slot.setBounds(60, 245, 80, 50);
        primary_armament_slot.setBounds(140, 245, 80, 50);
        secondary_armament_slot.setBounds(220, 245, 80, 50);
        primary_sub_armament_slot.setBounds(300, 245, 80, 50);
        secondary_sub_armament_slot.setBounds(380, 245, 80, 50);
        primary_light_armament_slot.setBounds(460, 245, 80, 50);
        secondary_light_armament_slot.setBounds(540, 245, 80, 50);
        Speed.setBounds(60, 500, 80, 20);
        Speed_in.setBounds(140, 500, 80, 20);
        Kn.setBounds(220, 500, 40, 20);
        p1.add(wIcon);
        p1.add(BG);
        p1.add(select);
        p1.add(impath);
        //*上段スロット*//
        p1.add(ship_type_slot);
        p1.add(primary_armament_slot);
        p1.add(secondary_armament_slot);
        p1.add(primary_sub_armament_slot);
        p1.add(secondary_sub_armament_slot);
        p1.add(primary_light_armament_slot);
        p1.add(secondary_light_armament_slot);
        //*ステータス*//
        p1.add(Speed);
        p1.add(Speed_in);
        p1.add(Kn);

        select.addActionListener(this);
        ship_type_slot.addActionListener(new ship_type_slot_l());
        primary_armament_slot.addActionListener(new primary_armament_slot_l());
        secondary_armament_slot.addActionListener(new secondary_armament_slot_l());
        primary_sub_armament_slot.addActionListener(new primary_sub_armament_slot_l());
        secondary_sub_armament_slot.addActionListener(new secondary_sub_armament_slot_l());
        primary_light_armament_slot.addActionListener(new primary_light_armament_slot_l());
        secondary_light_armament_slot.addActionListener(new secondary_light_armament_slot_l());
        loadCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        while ((line = br.readLine()) != null) {
                            // CSVファイルの各行を処理
                            System.out.println(line);
                        }
                        br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
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

    /*画像読み込み*/
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(
                new FileNameExtensionFilter(
                        language.IMAGE,
                        "png",
                        "jpg",
                        "Jpeg",
                        "GIF",
                        "bmp"
                )
        );
        if (filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = filechooser.getSelectedFile();
            // アイコンをラベルに設定
            ImageIcon icon = new ImageIcon(f.getPath());
            Image smallImg = icon
                    .getImage()
                    .getScaledInstance(
                            (int) (icon.getIconWidth() * 1.5),
                            -1,
                            Image.SCALE_SMOOTH
                    );
            ImageIcon Licon = new ImageIcon(smallImg);
            wIcon.setIcon(Licon);
            BG.setText("");
            impath.setText(f.getPath());
        }
    }

    /*スロットロック*/
    class ship_type_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (Type_slot == true) {
                Type_slot = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                ship_type_slot.setIcon(slot_icon);
            } else if (Type_slot == false) {
                Type_slot = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                ship_type_slot.setIcon(slot_icon);
            }
        }
    }

    class primary_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (PrArS == true) {
                PrArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                primary_armament_slot.setIcon(slot_icon);
            } else if (PrArS == false) {
                PrArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                primary_armament_slot.setIcon(slot_icon);
            }
        }
    }

    class secondary_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (SecArS == true) {
                SecArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                secondary_armament_slot.setIcon(slot_icon);
            } else if (SecArS == false) {
                SecArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                secondary_armament_slot.setIcon(slot_icon);
            }
        }
    }

    class primary_sub_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (PrSuArS == true) {
                PrSuArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                primary_sub_armament_slot.setIcon(slot_icon);
            } else if (PrSuArS == false) {
                PrSuArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                primary_sub_armament_slot.setIcon(slot_icon);
            }
        }
    }

    class secondary_sub_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (SecSuArS == true) {
                SecSuArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                secondary_sub_armament_slot.setIcon(slot_icon);
            } else if (SecSuArS == false) {
                SecSuArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                secondary_sub_armament_slot.setIcon(slot_icon);
            }
        }
    }

    class primary_light_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (PrLiArS == true) {
                PrLiArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                primary_light_armament_slot.setIcon(slot_icon);
            } else if (PrLiArS == false) {
                PrLiArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                primary_light_armament_slot.setIcon(slot_icon);
            }
        }
    }

    class secondary_light_armament_slot_l implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (SecLiArS == true) {
                SecLiArS = false;
                ImageIcon slot_icon = new ImageIcon(slotlock);
                secondary_light_armament_slot.setIcon(slot_icon);
            } else if (SecLiArS == false) {
                SecLiArS = true;
                ImageIcon slot_icon = new ImageIcon(slot);
                secondary_light_armament_slot.setIcon(slot_icon);
            }
        }
    }


}
