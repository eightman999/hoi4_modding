package sunlight.library.inc.GUI;
import sunlight.library.inc.GUI.GFX.GFX_GUI;
import sunlight.library.inc.GUI.GFX.Goals_GUI;
import sunlight.library.inc.GUI.GFX.SHIP_GFX_GUI;
import sunlight.library.inc.GUI.Localize.Localize_GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Main_GUI extends JFrame implements ActionListener {
    language l = new language();
    public static String temp = "";
    public static String file_name = "";
    public static String Stype = "";
    public static String IN_Stype = "";
    public static String Ctag = "";
    public static String   INS;
    public static int sw = 1;
    public static int Type = 0;
    String LMD = "";
    public static int L_mode = 0;
    String Mode = "";
    JPopupMenu popup;
    public static void main(String[] args) {
        Main_GUI frame = new Main_GUI("Hoi4 modding tool");

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    Main_GUI (String title){
        setTitle(title);
        setBounds(100, 100, 400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JLabel label = new JLabel(l.MODE_SELECT);
        JLabel label1 = new JLabel(l.LG_SELECT);
        JComboBox cb = new JComboBox();
        cb.addItem(l.NONE);
        cb.addItem(l.LOCALIZE);
        cb.addItem(l.EQUIPMENT);
        cb.addItem("Ship Hull");
        cb.addItem(l.COUNTRY);
        cb.addItem(l.GFX);
        cb.addItem(l.GOAL);
        JComboBox cbl = new JComboBox();
        cbl.addItem(l.NONE);
        cbl.addItem(l.ENGLISH);
        cbl.addItem(l.JAPANESE);
        cb.addActionListener(this);
        cbl.addActionListener(new LGActionListener());
        JButton done = new JButton(l.DONE);
        JButton done1 = new JButton(l.DONE);
        popup = new JPopupMenu();

        JMenuItem hullMenuItem = new JMenuItem("SHIP HULL");
        JMenuItem moduleMenuItem = new JMenuItem("SHIP MODULES");
        popup.add(hullMenuItem);
        popup.add(moduleMenuItem);

        done.addActionListener(new DoneActionListener());
        done1.addActionListener(new LDonectionListener());
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

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        Mode = String.valueOf(cb.getSelectedItem());
        System.out.println(cb.getSelectedItem());
    }
    class DoneActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(Mode == "Localize"){
                Localize_GUI LGUI = new Localize_GUI();
                LGUI.localize_GUI();
            }else if (Mode == "" || Mode == "None"){
                JOptionPane.showMessageDialog(null,
                        l.Please_Select_mode,
                        l.Please_Select_mode,
                        JOptionPane.WARNING_MESSAGE);
            }else if (Mode == "Goals"){
                Goals_GUI GGUI = new Goals_GUI();
                GGUI.goals_GUI();
            }else if (Mode == "GFX"){
                GFX_GUI GFGUI = new GFX_GUI();
                GFGUI.gfx_GUI();
            }else if (Mode == "Ship Hull"){
                SHIP_GFX_GUI sgg = new SHIP_GFX_GUI();
                sgg.gfx_GUI();
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        showPopup(e);
    }

    public void mousePressed(MouseEvent e){
        showPopup(e);
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    class LGActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cbl = (JComboBox)e.getSource();
            LMD = String.valueOf(cbl.getSelectedItem());
            System.out.println(cbl.getSelectedItem());
        }
    }
    class LDonectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(LMD == "None"||LMD == ""){
                JOptionPane.showMessageDialog(null,
                        "言語モードを選択してください",
                        "Please Select language mode",
                        JOptionPane.WARNING_MESSAGE);
            }else if(LMD == l.ENGLISH){
                L_mode = 1;
//                l.C_languages();

            }else if(LMD == l.JAPANESE) {
                L_mode = 0;
//                l.C_languages();
            }
        }
    }
}
