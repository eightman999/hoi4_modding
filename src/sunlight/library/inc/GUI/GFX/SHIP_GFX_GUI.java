package sunlight.library.inc.GUI.GFX;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    "#It is described by Sunlight.library and software created by STRaDA (Sunlight Technology Research and Development Association).\n" +
    "spriteTypes = {\n";
  Boolean f1 = true;
  Boolean f2 = true;

  Boolean m1 = true;
  Boolean m2 = true;
  Boolean m3 = true;

  Boolean r1 = true;
  Boolean r2 = true;

  JButton front_1_custom_slot = new JButton();
  JButton front_2_custom_slot = new JButton();

  JButton mid_1_custom_slot = new JButton();
  JButton mid_2_custom_slot = new JButton();
  JButton mid_3_custom_slot = new JButton();

  JButton rear_1_custom_slot = new JButton();
  JButton rear_2_custom_slot = new JButton();

  JButton fixed_ship_battery_slot = new JButton();
  JButton fixed_ship_anti_air_slot = new JButton();
  JButton fixed_ship_fire_control_system_slot = new JButton();
  JButton fixed_ship_radar_slot = new JButton();
  JButton fixed_ship_engine_slot = new JButton();
  JButton fixed_ship_secondaries_slot = new JButton();
  JButton fixed_ship_armor_slot = new JButton();

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
    front_1_custom_slot.setIcon(slot_icon);
    front_2_custom_slot.setIcon(slot_icon);
    mid_1_custom_slot.setIcon(slot_icon);
    mid_2_custom_slot.setIcon(slot_icon);
    mid_3_custom_slot.setIcon(slot_icon);
    rear_1_custom_slot.setIcon(slot_icon);
    rear_2_custom_slot.setIcon(slot_icon);
    wIcon.setIcon(new ImageIcon(wPic));
    BG.setIcon(new ImageIcon(BGpic));
    BG.setHorizontalAlignment(JLabel.CENTER);
    wIcon.setHorizontalAlignment(JLabel.CENTER);

    p1.setLayout(null);
    BG.setBounds(60, 10, 540, 200);
    wIcon.setBounds(60, 10, 540, 200);
    select.setBounds(500, 225, 100, 20);
    impath.setBounds(60, 225, 400, 20);
    front_1_custom_slot.setBounds(60, 245, 80, 50);
    //            front_1_custom_slot_l.setBounds(60 - 2, 300, 80 + 4, 50);
    front_2_custom_slot.setBounds(140, 245, 80, 50);
    //            front_2_custom_slot_l.setBounds(140 - 2, 300, 80 + 4, 50);
    mid_1_custom_slot.setBounds(220, 245, 80, 50);
    //            mid_1_custom_slot_l.setBounds(220 - 2, 300, 80 + 4, 50);
    mid_2_custom_slot.setBounds(300, 245, 80, 50);
    //            mid_2_custom_slot_l.setBounds(300 - 2, 300, 80 + 4, 50);
    mid_3_custom_slot.setBounds(380, 245, 80, 50);
    //            mid_3_custom_slot_l.setBounds(380 - 2, 300, 80 + 4, 50);
    rear_1_custom_slot.setBounds(460, 245, 80, 50);
    //            rear_1_custom_slot_l.setBounds(460 - 2, 245, 80 + 4, 50);
    rear_2_custom_slot.setBounds(540, 245, 80, 50);
    //            rear_2_custom_slot_l.setBounds(540 - 2, 245, 80 + 4, 50);
    Speed.setBounds(60, 500, 80, 20);
    Speed_in.setBounds(140, 500, 80, 20);
    Kn.setBounds(220, 500, 40, 20);
    p1.add(wIcon);
    p1.add(BG);
    p1.add(select);
    p1.add(impath);
    //*上段スロット*//
    p1.add(front_1_custom_slot);
    p1.add(front_2_custom_slot);
    p1.add(mid_1_custom_slot);
    p1.add(mid_2_custom_slot);
    p1.add(mid_3_custom_slot);
    p1.add(rear_1_custom_slot);
    p1.add(rear_2_custom_slot);
    //*ステータス*//
    p1.add(Speed);
    p1.add(Speed_in);
    p1.add(Kn);

    select.addActionListener(this);
    front_1_custom_slot.addActionListener(new front_1_custom_slot_l());
    front_2_custom_slot.addActionListener(new front_2_custom_slot_l());
    mid_1_custom_slot.addActionListener(new mid_1_custom_slot_l());
    mid_2_custom_slot.addActionListener(new mid_2_custom_slot_l());
    mid_3_custom_slot.addActionListener(new mid_3_custom_slot_l());
    rear_1_custom_slot.addActionListener(new rear_1_custom_slot_l());
    rear_2_custom_slot.addActionListener(new rear_2_custom_slot_l());
  }

  /*画像読み込み*/
  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser filechooser = new JFileChooser();
    filechooser.setFileFilter(
      new FileNameExtensionFilter(
        "画像ファイル",
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
  class front_1_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (f1 == true) {
        f1 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        front_1_custom_slot.setIcon(slot_icon);
      } else if (f1 == false) {
        f1 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        front_1_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class front_2_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (f2 == true) {
        f2 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        front_2_custom_slot.setIcon(slot_icon);
      } else if (f2 == false) {
        f2 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        front_2_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class mid_1_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (m1 == true) {
        m1 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        mid_1_custom_slot.setIcon(slot_icon);
      } else if (m1 == false) {
        m1 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        mid_1_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class mid_2_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (m2 == true) {
        m2 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        mid_2_custom_slot.setIcon(slot_icon);
      } else if (m2 == false) {
        m2 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        mid_2_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class mid_3_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (m3 == true) {
        m3 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        mid_3_custom_slot.setIcon(slot_icon);
      } else if (m3 == false) {
        m3 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        mid_3_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class rear_1_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (r1 == true) {
        r1 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        rear_1_custom_slot.setIcon(slot_icon);
      } else if (r1 == false) {
        r1 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        rear_1_custom_slot.setIcon(slot_icon);
      }
    }
  }

  class rear_2_custom_slot_l implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (r2 == true) {
        r2 = false;
        ImageIcon slot_icon = new ImageIcon(slotlock);
        rear_2_custom_slot.setIcon(slot_icon);
      } else if (r2 == false) {
        r2 = true;
        ImageIcon slot_icon = new ImageIcon(slot);
        rear_2_custom_slot.setIcon(slot_icon);
      }
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
          temp1 =
            temp1 +
            "\t\tSpriteType = {\n\t\tname = \"GFX_" +
            fileName.substring(0, index) +
            "_medium\"";
          temp1 =
            temp1 +
            "\n\t\t\ttexturefile = \"" +
            texture_file_path.getText() +
            "/" +
            list[i].getName() +
            "\"";
          temp1 = temp1 + "\n\t\t}\n";
        } else if (list[i].isDirectory()) { //ディレクトリの場合
          //何もしない
        }
      }
      temp1 = temp1 + "}\n";
    }
  }

  class SaveActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      try {
        FileOutputStream fos = new FileOutputStream(
          System.getProperty("user.home") + "/Desktop/" + fnm.getText()
        );
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(temp1);
        osw.close();
        fos.close();
      } catch (IOException er) {
        System.out.println(er);
      }
    }
  }
}
