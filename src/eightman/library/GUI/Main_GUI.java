package eightman.library.GUI;

import eightman.library.GUI.GUI_tool.Naval_hull_designer;
import eightman.library.GUI.MODULE.*;
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

import static eightman.library.GUI.System.MT_core.*;
import static eightman.library.GUI.System.Mac_OS.*;
import static eightman.library.GUI.language.*;
import static javax.swing.UIManager.setLookAndFeel;

public class Main_GUI extends JFrame implements Runnable {

    public static Map<String, String> modPathMap = new HashMap<>();
    public static DefaultListModel<String> modPathListModel = new DefaultListModel<>();
    public static JList<String> modPathList = new JList<>(modPathListModel);
    public static int L_mode;
    public static String Version = "0.7.2";
    public static Image icon;
    public static Image icon2;
    public static Image loading;
    public static int run;
    public static int runtime_h;
    public static String Version_beta = "0";
    public static Boolean Beta = true;
    public static String Version_date_2 = "2024/06/24";
    public static String file_name;
    public static String temp;
    public static JMenuBar menuBar = new JMenuBar();
    public JMenu main_Menu = new JMenu(Title);
    public JMenuItem aboutItem = new JMenuItem(ABOUT + Title);
    public JMenuItem prefsItem = new JMenuItem(PREF + Title);
    public JMenuItem surveyItem = new JMenuItem("Survey" + Title);
    public JMenuItem quitItem = new JMenuItem("Quit" + Title);
    public static JMenu languageMenu = new JMenu(language.LG_SELECT);
    public static JMenuItem englishItem = new JMenuItem(language.ENGLISH);
    public static JMenuItem japaneseItem = new JMenuItem(language.JAPANESE);
    public static JMenu modeMenu = new JMenu(language.MODE_SELECT);
    public static JMenuItem localizeItem = new JMenuItem(language.LOCALIZE);
    public static JMenuItem goalItem = new JMenuItem(language.GOAL);
    public static JMenuItem gfxItem = new JMenuItem(language.GFX);
    public static JMenuItem sdItem = new JMenuItem(language.SDI);
    public static JMenuItem shlItem = new JMenuItem(language.SHL);
    public static JMenuItem countryItem = new JMenuItem(language.COUNTRY);
    public static JMenuItem moduleItem = new JMenuItem(language.MODULE);
    public static JMenuItem NameItem = new JMenuItem(language.NAME);

    public static void main(String[] args) throws Exception {
        MT_System.setupLogger();
        MT_System.out.logInfo("Application started.");
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
        setupActionListener(localizeItem, () -> new Localize_GUI().localize_GUI());
        setupMain_GUI();
        setJMenuBar(menuBar);
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/icon.png").getImage());
    }

    private void setupMain_GUI(){
        // ロゴとソフト名を表示
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon logoIcon = new ImageIcon(icon); // ロゴのパスを指定
        Image image = logoIcon.getImage(); // ImageIconからImageを取得
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // スケーリング
        logoIcon = new ImageIcon(newimg);  // スケーリング後のImageをImageIconに再変換
        logoLabel.setIcon(logoIcon);
        logoLabel.setText(Title);
        add(logoLabel, BorderLayout.NORTH);

        // モード一覧をボタンで作成
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4)); // 横にボタンを配置するためのパネル
        String[] groupNames = {"外観", "言語", "技術", "国家"};
        JMenuItem[][] modes = {
                {goalItem, gfxItem, sdItem}, // 外観グループ
                {localizeItem, NameItem}, // 言語グループ
                {shlItem, moduleItem}, // 技術グループ
                {countryItem} // 国家グループ
        };
        for (int i = 0; i < modes.length; i++) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
            JLabel groupName = new JLabel(groupNames[i]);
            groupName.setHorizontalAlignment(JLabel.CENTER); // グループ名を中央寄せに設定
            groupPanel.add(groupName);
            for (JMenuItem mode : modes[i]) {
                JButton modeButton = new JButton(mode.getText());
                modeButton.setMaximumSize(new Dimension(100, modeButton.getPreferredSize().height));
                if (mode == localizeItem) {
                    modeButton.addActionListener(e -> new Localize_GUI().localize_GUI());
                } else if (mode == goalItem) {
                    modeButton.addActionListener(e -> new Goals_GUI().goals_GUI());
                } else if (mode == gfxItem) {
                    modeButton.addActionListener(e -> {
                        GFX_GUI gfxGui = new GFX_GUI();
                        gfxGui.setVisible(true);
                    });
                } else if (mode == sdItem) {
                    modeButton.addActionListener((e -> new Naval_hull_designer().setVisible(true)));
                } else if (mode == shlItem) {
                    modeButton.addActionListener(e -> new SHIP_GFX_GUI().gfx_GUI());
                } else if (mode == countryItem) {
                    // countryItemのアクションを設定
                } else if (mode == moduleItem) {
                    modeButton.addActionListener(e -> new module_maker_GUI().module_maker_GUI());
                }
                groupPanel.add(modeButton);
            }
            buttonPanel.add(groupPanel);
        }
        add(buttonPanel, BorderLayout.CENTER);

        pack();
    }
    private void setupMenuItems() {
        main_Menu.add(aboutItem);
        main_Menu.add(prefsItem);
        main_Menu.add(surveyItem);
        main_Menu.add(quitItem);
        menuBar.add(main_Menu);
    }

    private void setupActionListener(JMenuItem item, Runnable action) {
        aboutItem.addActionListener(e -> onAbout());
        prefsItem.addActionListener(e -> onPreference());
        surveyItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Survey " + Title));
        quitItem.addActionListener(e -> quit());
    }
    private void setupMenuItems_mac_os() {
        MT_System.out.println("MacOS");
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
        NameItem.setText(language.NAME);

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
            icon2 = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/icon2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            icon = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loading = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/Glass_lines.gif\"")));
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
        try {
            icon2 = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/icon2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loading = ImageIO.read(Objects.requireNonNull(Main_GUI.class.getResource("images/Glass_lines.gif")));
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
        MT_System.out.println("Application closed.");
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