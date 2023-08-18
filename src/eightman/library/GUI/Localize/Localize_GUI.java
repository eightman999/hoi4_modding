package eightman.library.GUI.Localize;

import static java.awt.event.KeyEvent.VK_CONTROL;
import static eightman.library.GUI.Main_GUI.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import eightman.library.GUI.language;

public class Localize_GUI extends JFrame implements ActionListener {

  language l = new language();
  String LM = "Japanese";
  JComboBox<String> cb = new JComboBox<>();
  JPanel p = new JPanel();
  JLabel label2 = new JLabel("file name : ");
  JLabel label = new JLabel("Set Filename");
  JTextField fn = new JTextField("File Name", 15);
  JTextArea textArea_s = new JTextArea();
  JTextArea textArea_t = new JTextArea();
  JTextArea textArea_d = new JTextArea();
  String STR;
  int com_pr = 1;
  JProgressBar pb = new JProgressBar(1, com_pr);

  public void localize_GUI() {
    Localize_GUI Lframe = new Localize_GUI();
    Lframe.setLocationRelativeTo(null);
    Lframe.setVisible(true);
    System.out.println(language.str_loc);
    System.out.println(System.getProperty("user.home"));
  }

  public Localize_GUI() {
    setBounds(100, 100, 660, 700);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel p1 = new JPanel();

    Container contentPane = getContentPane();
    contentPane.add(p, BorderLayout.CENTER);

    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu(language.File);
    JMenuItem menuSave = new JMenuItem(language.Save);
    textArea_s.setWrapStyleWord(true);
    textArea_s.setLineWrap(false);
    textArea_t.setWrapStyleWord(true);
    textArea_t.setLineWrap(false);
    textArea_d.setWrapStyleWord(true);
    textArea_d.setLineWrap(false);
    JScrollPane scrollPane = new JScrollPane(textArea_s);
    JScrollPane scrollPanet = new JScrollPane(textArea_t);
    JScrollPane scrollPaned = new JScrollPane(textArea_d);
    scrollPane.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
    );
    scrollPane.setPreferredSize(new Dimension(500, 1000));
    scrollPanet.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
    );
    scrollPanet.setPreferredSize(new Dimension(500, 1000));
    scrollPaned.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
    );
    scrollPaned.setPreferredSize(new Dimension(1000, 1000));
    textArea_d.setEditable(false);

    menuSave.setMnemonic(VK_CONTROL + 'S');

    //        menuSizeLarge.setMnemonic('L');
    //        menuSizeSmall.setMnemonic('S');

    menuSave.addActionListener(new menuSaveListener());

    JButton combined = new JButton(language.combined);
    //        menuSizeSmall.addActionListener(this);
    combined.addActionListener(new combinedListener());
    getRootPane().setJMenuBar(menuBar);

    menuFile.add(menuSave);

    JButton done = new JButton(language.DONE);

    done.addActionListener(new DoneActionListener());
    //        menuSizeLarge.addActionListener(new  SetActionListener());
    cb.addItem(language.JAPANESE);
    cb.addItem(language.ENGLISH);

    cb.addActionListener(this);
    JLabel label1 = new JLabel(".yml");
    JLabel inputs = new JLabel("Input System text");
    JLabel inputt = new JLabel("Input Translated text");
    add(p1);
    p1.setLayout(null);

    label.setBounds(120, 10, 80, 20);
    label1.setBounds(465, 10, 40, 20);
    label2.setBounds(120, 35, 440, 20);
    fn.setBounds(200, 10, 150, 20);
    cb.setBounds(345, 10, 120, 20);
    done.setBounds(505, 10, 40, 20);
    textArea_s.setBounds(20, 80, 200, 320);
    textArea_t.setBounds(440, 80, 200, 320);
    textArea_d.setBounds(120, 420, 400, 200);
    inputs.setBounds(20, 55, 200, 20);
    inputt.setBounds(440, 55, 200, 20);
    combined.setBounds(230, 160, 200, 100);
    pb.setBounds(120, 670, 400, 20);
    p.add(menuBar);
    p1.add(fn);
    p1.add(label);
    p1.add(label1);
    p1.add(label2);
    p1.add(cb);
    p1.add(done);
    p1.add(textArea_s);
    p1.add(textArea_t);
    p1.add(inputs);
    p1.add(inputt);
    p1.add(combined);
    p1.add(pb);
    p1.add(textArea_d);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    LM = String.valueOf(cb.getSelectedItem());
    System.out.println(cb.getSelectedItem());
  }

  class menuSaveListener extends Component implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      temp =
        "#It is described by eightman.library and software created by  eightman\n";
      temp = temp + "l_" + LM + ":";
      temp = temp + "\n" + STR;
      try {
        FileOutputStream fos = new FileOutputStream(
          System.getProperty("user.home") + "/Desktop/" + file_name
        );
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(temp);
        osw.close();
        fos.close();
      } catch (IOException er) {
        System.out.println(er);
      }
    }
  }

  class DoneActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      file_name = fn.getText() + "_l_" + LM + ".yml";
      label2.setText("file name : " + file_name);
    }
  }

  class combinedListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      int linecount_s = textArea_s.getLineCount();
      int linecount_t = textArea_t.getLineCount();
      if (linecount_s == linecount_t) {
        com_pr = linecount_s;
        pb.setValue(1);
        String str_s = textArea_s.getText();
        String str_t = textArea_t.getText();
        String[] strs = str_s.split("\n");
        String[] strt = str_t.split("\n");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
          pb.setValue(i);
          sb.append(strs[i] + ":0 \"" + strt[i] + "\"\n");
        }
        STR = String.valueOf(sb);
        textArea_d.setText(new String(sb));
      }
      //            else if (linecount_s == 1 || linecount_t == 1){
      //                JOptionPane.showMessageDialog(null,
      //                        "何も入力されていません！",
      //                        "",
      //                        JOptionPane.WARNING_MESSAGE);
      //
      //            }
      else {
        JOptionPane.showMessageDialog(
          null,
          language.LR_no,
          "",
          JOptionPane.ERROR_MESSAGE
        );
      }
      temp =
        "#It is described by eightman.library and software created by  eightman\n";
      temp = temp + "l_" + LM + ":";
      temp = temp + "\n" + STR;
      try {
        FileOutputStream fos = new FileOutputStream(
          System.getProperty("user.home") + "/Desktop/" + file_name
        );
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(temp);
        osw.close();
        fos.close();
      } catch (IOException er) {
        System.out.println(er);
      }
    }
  }
  //    class SetActionListener implements ActionListener {
  //        public void actionPerformed(ActionEvent e) {
  //            setBounds(100, 100, 600, 600);
  //            Container contentPane = getContentPane();
  //            contentPane.add(p, BorderLayout.CENTER);
  //        }
  //    }
}
