package eightman.library.inc.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;
import eightman.library.inc.GUI.GFX.GFX_GUI;
import eightman.library.inc.GUI.GFX.Goals_GUI;
import eightman.library.inc.GUI.GFX.SHIP_GFX_GUI;
import eightman.library.inc.GUI.Localize.Localize_GUI;

public class Main_GUI extends JFrame implements ActionListener {

  public static String temp = "";
  public static String file_name = "";
  public static String S_type = "";
  public static String IN_Stype = "";
  public static String Ctag = "";
  public static String INS;
  public static int sw = 1;
  public static int Type = 0;
  public static int L_mode = 0;

  String LMD = "";
  String Mode = "";
  JPopupMenu popup;
  JPanel p = new JPanel();
  JPanel p1 = new JPanel();
  JLabel label = new JLabel(language.MODE_SELECT);
  JLabel label1 = new JLabel(language.LG_SELECT);
  JButton done = new JButton(language.DONE);
  JButton done1 = new JButton(language.DONE);
  JComboBox<String> cb = new JComboBox<>();
  JComboBox<String> cbl = new JComboBox<>();

  public static void main(String[] args) throws IOException{
    Main_GUI frame = new Main_GUI(language.Title);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  Main_GUI(String title) {
    load_screen(title);
    done.addActionListener(new DoneActionListener());
    done1.addActionListener(new LDonectionListener());
    cb.addActionListener(this);
    cbl.addActionListener(new LGActionListener());
  }

  public void load_screen(String title) {
    setTitle(title);
    setBounds(100, 100, 400, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cb.addItem(language.NONE);
    cb.addItem(language.LOCALIZE);
    cb.addItem(language.EQUIPMENT);
    cb.addItem(language.SHL);
    cb.addItem(language.COUNTRY);
    cb.addItem(language.GFX);
    cb.addItem(language.GOAL);

    cbl.addItem(language.NONE);
    cbl.addItem(language.ENGLISH);
    cbl.addItem(language.JAPANESE);

    popup = new JPopupMenu();
    label.setText(language.MODE_SELECT);
    label1.setText(language.LG_SELECT);
    done.setText(language.DONE);
    done1.setText(language.DONE);
    p.add(label);
    p.add(cb);
    p.add(done);

    p1.add(label1);
    p1.add(cbl);
    p1.add(done1);

    Container contentPane = getContentPane();
    contentPane.add(p, BorderLayout.CENTER);
    contentPane.add(p1, BorderLayout.NORTH);
  }

  public void kill_screen() {
    cb.removeAllItems();
    cbl.removeAllItems();
    p.remove(label);
    p.remove(cb);
    p.remove(done);
    p1.remove(label1);
    p1.remove(cbl);
    p1.remove(done1);
  }

  public void actionPerformed(ActionEvent e) {
    // JComboBox cb = (JComboBox) e.getSource();
    Mode = String.valueOf(cb.getSelectedItem());
    System.out.println(cb.getSelectedItem());
  }

  class DoneActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (Mode == language.LOCALIZE) {
        Localize_GUI LGUI = new Localize_GUI();
        LGUI.localize_GUI();
      } else if (Mode == language.GOAL) {
        Goals_GUI GGUI = new Goals_GUI();
        GGUI.goals_GUI();
      } else if (Mode == language.GFX) {
        GFX_GUI GFGUI = new GFX_GUI();
        GFGUI.gfx_GUI();
      } else if (Mode == language.SHL) {
        SHIP_GFX_GUI sgg = new SHIP_GFX_GUI();
        sgg.gfx_GUI();
      } else if (LMD == "未選択" || LMD == "None" || LMD == "") {
        JOptionPane.showMessageDialog(
          null,
          language.Please_Select_mode,
          language.Please_Select_mode,
          JOptionPane.WARNING_MESSAGE
        );
      }
      System.out.println(language.GOAL);
      System.out.println(language.GFX);
      System.out.println(language.SHL);
    }
  }

  public void mouseReleased(MouseEvent e) {
    showPopup(e);
  }

  public void mousePressed(MouseEvent e) {
    showPopup(e);
  }

  private void showPopup(MouseEvent e) {
    if (e.isPopupTrigger()) {
      popup.show(e.getComponent(), e.getX(), e.getY());
    }
  }

  class LGActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      LMD = String.valueOf(cbl.getSelectedItem());
      System.out.println(cbl.getSelectedItem());
    }
  }

  class LDonectionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if (LMD == "未選択" || LMD == "None" || LMD == "") {
        JOptionPane.showMessageDialog(
          null,
          "言語モードを選択してください",
          "Please Select language mode",
          JOptionPane.WARNING_MESSAGE
        );
      } else if (LMD == "英語" || LMD == "English") {
        L_mode = 1;
        language.C_languages();
        kill_screen();
        load_screen(language.Title);
        LMD = "";
      } else if (LMD == "日本語" || LMD == "Japanese") {
        L_mode = 0;
        language.C_languages();
        kill_screen();
        load_screen(language.Title);
        LMD = "";
      }
    }
  }
}
