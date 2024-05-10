package eightman.library.GUI;

import eightman.library.GUI.ATRM_MODULE.module_maker_GUI;
import eightman.library.GUI.GFX.GFX_GUI;
import eightman.library.GUI.GFX.Goals_GUI;
import eightman.library.GUI.GFX.SHIP_GFX_GUI;
import eightman.library.GUI.Localize.Localize_GUI;
import eightman.library.GUI.System.About_GUI;
import eightman.library.GUI.System.Preference_GUI;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import static eightman.library.GUI.System.MT_core.load_config;
import static eightman.library.GUI.System.MT_core.run_counter;
import static eightman.library.GUI.System.Mac_OS.*;
import static eightman.library.GUI.language.Title;
import static javax.swing.UIManager.setLookAndFeel;

public class Main_GUI extends JFrame implements Runnable {

    public static Map<String, String> modPathMap = new HashMap<>();
    public static DefaultListModel<String> modPathListModel = new DefaultListModel<>();
    public static JList<String> modPathList = new JList<>(modPathListModel);
    public static int L_mode;
    public static String Version = "0.7.0";
    public static Image icon;
    public static int run;
    public static String Version_beta = "5";
    public static Boolean Beta = true;
    public static String Version_date_2 = "2024/05/09";
    public static String file_name;
    public static String temp;
    public static JMenuBar menuBar = new JMenuBar();
    public JMenu main_Menu = new JMenu(Title);
    public JMenuItem aboutItem = new JMenuItem("About" + Title);
    public JMenuItem prefsItem = new JMenuItem("Preferences" + Title);
    public JMenuItem surveyItem = new JMenuItem("Survey" + Title);
    public JMenuItem quitItem = new JMenuItem("Quit" + Title);
    public static JMenu languageMenu = new JMenu(language.LG_SELECT);
    public static JMenuItem englishItem = new JMenuItem(language.ENGLISH);
    public static JMenuItem japaneseItem = new JMenuItem(language.JAPANESE);
    public static JMenu modeMenu = new JMenu(language.MODE_SELECT);
    public static JMenuItem localizeItem = new JMenuItem(language.LOCALIZE);
    public static JMenuItem goalItem = new JMenuItem(language.GOAL);
    public static JMenuItem gfxItem = new JMenuItem(language.GFX);
    public static JMenuItem shlItem = new JMenuItem(language.SHL);
    public static JMenuItem countryItem = new JMenuItem(language.COUNTRY);
    public static JMenuItem moduleItem = new JMenuItem(language.MODULE);

    public static void main(String[] args) throws Exception {
        load_config();
        if (isMac()) {
            mac_system();
            loadIcon_onMac();
        } else {
            loadIcon();
        }
        run_counter();
        os_Preference();
        Date run_date = new Date();
        System.out.println(run_date);
    }

    Main_GUI(String title) throws ParseException {
        setTitle(Title); // タイトルを設定
        if (isMac()) {
            setupMenuItems_mac_os();
        } else {
            setupMenuItems();
        }
        setJMenuBar(menuBar);
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/icon.png").getImage());
    }

    private void setupMenuItems() {
        aboutItem.addActionListener(e -> onAbout());
        prefsItem.addActionListener(e -> onPreference());
        surveyItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Survey " + Title));
        quitItem.addActionListener(e -> quit());
        main_Menu.add(aboutItem);
        main_Menu.add(prefsItem);
        main_Menu.add(surveyItem);
        main_Menu.add(quitItem);


        localizeItem.addActionListener(e -> new Localize_GUI().localize_GUI());
        goalItem.addActionListener(e -> new Goals_GUI().goals_GUI());
        gfxItem.addActionListener(e -> new GFX_GUI().gfx_GUI());
        shlItem.addActionListener(e -> new SHIP_GFX_GUI().gfx_GUI());
        moduleItem.addActionListener(e -> new module_maker_GUI().module_maker_GUI());
        modeMenu.add(localizeItem);
        modeMenu.add(goalItem);
        modeMenu.add(gfxItem);
        modeMenu.add(shlItem);
        modeMenu.add(countryItem);
        modeMenu.add(moduleItem);

        menuBar.add(main_Menu);
        menuBar.add(modeMenu);
    }

    private void setupMenuItems_mac_os() {
        aboutItem.addActionListener(e -> onAbout());
        prefsItem.addActionListener(e -> onPreference());
        surveyItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Survey " + Title));
        quitItem.addActionListener(e -> quit());


        localizeItem.addActionListener(e -> new Localize_GUI().localize_GUI());
        goalItem.addActionListener(e -> new Goals_GUI().goals_GUI());
        gfxItem.addActionListener(e -> new GFX_GUI().gfx_GUI());
        shlItem.addActionListener(e -> new SHIP_GFX_GUI().gfx_GUI());
        modeMenu.add(localizeItem);
        modeMenu.add(goalItem);
        modeMenu.add(gfxItem);
        modeMenu.add(shlItem);
        modeMenu.add(countryItem);

        menuBar.add(modeMenu);
    }

    public static void repainting() {
        languageMenu.setText(language.LG_SELECT);
        englishItem.setText(language.ENGLISH);
        japaneseItem.setText(language.JAPANESE);
        modeMenu.setText(language.MODE_SELECT);
        localizeItem.setText(language.LOCALIZE);
        goalItem.setText(language.GOAL);
        gfxItem.setText(language.GFX);
        shlItem.setText(language.SHL);
        countryItem.setText(language.COUNTRY);

        // メニューバーの変更を反映
        menuBar.repaint();
        menuBar.revalidate();
    }

    private static void os_Preference() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        // システム標準のL&Fを設定.
        // MacOSXならAqua、WindowsXPならLuna、Vista/Windows7ならばAeroになる.
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Main_GUI(Title));
        // システムのデフォルトのコマンド修飾キーを取得する.
        // WindowsならCTRL_MASK, OSXならばMETA_MASKになる.
        Toolkit tk = Toolkit.getDefaultToolkit();
        int shotcutKey = tk.getMenuShortcutKeyMask();
    }

    public static void loadIcon() {
        try {
            icon = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadIcon_onMac() {
        try {
            icon = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            try {
                taskbar.setIconImage(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void onAbout() {
        new About_GUI();
    }

    public static void set_language() {
        if(isMac()){
            mac_title();
        }else {
            language.C_languages();
        }
    }

    public static void onPreference() {
        new Preference_GUI();
    }

    public void quit() {
        JOptionPane.showMessageDialog(this, "終了します.");
        System.exit(0);
    }

    @Override
    public void run() {
        try {
            // システム標準のL&Fを設定.
            // MacOSXならAqua、WindowsXPならLuna、Vista/Windows7ならばAeroになる.
            // Aeroの場合、メニューに表示されるニーモニックのアンダースコアはALTキーを押さないとでてこない.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // メインフレーム構築
            final Main_GUI mainFrame = new Main_GUI(Title);
            if (isMac()) {
                Class<?> clz = Class.forName("eightman.library.GUI.MainFramePartialForMacOSX");
                Method mtd = clz.getMethod("setupScreenMenu", new Class[] {MainFrame.class});
                MainFrame mainFrameInstance = new MainFrame();
                mtd.invoke(null, new Object[] {mainFrameInstance});
            }
            mainFrame.setLocationRelativeTo(null);
            // 画面表示
            mainFrame.setVisible(true);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}