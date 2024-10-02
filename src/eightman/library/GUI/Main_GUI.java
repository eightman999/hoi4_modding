package eightman.library.GUI;

import eightman.library.Core;

import eightman.library.GUI.GUI_tool.FleetDesigner_GUI;
import eightman.library.GUI.GUI_tool.Name_GUI;
import eightman.library.GUI.MODULE.*;
import eightman.library.GUI.GFX.GFX_GUI;
import eightman.library.GUI.GFX.Goals_GUI;
import eightman.library.GUI.GFX.SHIP_GFX_GUI;
import eightman.library.GUI.Localize.Localize_GUI;
import eightman.library.GUI.System.About_GUI;
import eightman.library.GUI.System.Preference_GUI;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import static eightman.library.Core.*;
import static eightman.library.GUI.System.Mac_OS.*;
import static eightman.library.GUI.language.*;
import static eightman.library.GUI.language.C_languages;
import static javax.swing.UIManager.setLookAndFeel;

public class Main_GUI extends JFrame implements Runnable {
    private static Main_GUI instance;
    private static final String MF_CLASS_NAME = "eightman.library.GUI.MainFramePartialForMacOSX";
    public static Map<String, String> modPathMap = new HashMap<>();
    public static DefaultListModel<String> modPathListModel = new DefaultListModel<>();
    public static JList<String> modPathList = new JList<>(modPathListModel);
    public static int L_mode;
    public static String Version = "0.7.3.3";
    public static Image icon;
    public static Image icon2;
    public static Image loading;
    public static int run;
    public static int runtime_h;
    public static String Version_beta = "0";
    public static Boolean Beta = true;
    public static String Version_date_2 = "2024/09/24";
    public static String file_name;
    public static String temp;
    public static JMenuBar menuBar = new JMenuBar();
    public static String naval_path;
    public static String use_font = "Arial";
    public static List<String> modList;
    public JMenu main_Menu = new JMenu(Title);
    public JMenuItem aboutItem = new JMenuItem(ABOUT + Title);
    public JMenuItem prefsItem = new JMenuItem(PREF + Title);
    public JMenuItem surveyItem = new JMenuItem(Survey + Title);
    public JMenuItem quitItem = new JMenuItem(Quit + Title);
    public static JMenu languageMenu = new JMenu(LG_SELECT);
    public static JMenuItem englishItem = new JMenuItem(ENGLISH);
    public static JMenuItem japaneseItem = new JMenuItem(JAPANESE);
    public static JMenu modeMenu = new JMenu(MODE_SELECT);
    public static JMenuItem localizeItem = new JMenuItem(LOCALIZE);
    public static JMenuItem convertItem = new JMenuItem(CVRT);
    public static JMenuItem goalItem = new JMenuItem(GOAL);
    public static JMenuItem gfxItem = new JMenuItem(GFX);
//    public static JMenuItem sdItem = new JMenuItem(SDI);
    public static JMenuItem fdItem = new JMenuItem(FDI);
    public static JMenuItem shlItem = new JMenuItem(SHL);
    public static JMenuItem countryItem = new JMenuItem(COUNTRY);
    public static JMenuItem moduleItem = new JMenuItem(MODULE);
    public static JMenuItem NameItem = new JMenuItem(NAME);


    public static void main(String[] args) throws Exception {
        setupLogger();
        Core.out.logInfo("Application started.");
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
        set_language();
        load_config();
        System.out.println(run_date);
    }

    public Main_GUI(String Title) throws ParseException {
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
        applyFontToAllGUI(use_font);
    }

    private void setupMain_GUI(){
        // ロゴとソフト名を表示
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon logoIcon = new ImageIcon(icon); // ロゴのパスを指定
        Image image = logoIcon.getImage(); // ImageIconからImageを取得
        Image newimg = image.getScaledInstance(80, 80,  Image.SCALE_SMOOTH); // スケーリング
        logoIcon = new ImageIcon(newimg);  // スケーリング後のImageをImageIconに再変換
        logoLabel.setIcon(logoIcon);
        logoLabel.setText(Title);
        add(logoLabel, BorderLayout.NORTH);

        // モード一覧をボタンで作成
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4)); // 横にボタンを配置するためのパネル
        String[] groupNames = {VIW, LNG, TEC, CTR, "GFX"};
        JMenuItem[][] modes = {
                {goalItem, gfxItem}, // 外観グループ
                {localizeItem, NameItem}, // 言語グループ
                {shlItem, moduleItem, fdItem}, // 技術グループ
                {countryItem}, // 国家グループ
//                {convertItem} // 変換グループ
        };
        for (int i = 0; i < modes.length; i++) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
            JLabel groupName = new JLabel(groupNames[i]);
            groupName.setHorizontalAlignment(JLabel.CENTER); // グループ名を中央寄せに設定
            groupPanel.add(groupName);
            for (JMenuItem mode : modes[i]) {
                JButton modeButton = new JButton(mode.getText());
                modeButton.setMaximumSize(new Dimension(130, modeButton.getPreferredSize().height));
                if (mode == localizeItem) {
                    modeButton.addActionListener(e -> {
                        new Localize_GUI().localize_GUI();
                        this.setVisible(false);
                    });
                } else if (mode == goalItem) {
                    modeButton.addActionListener(e -> {
                        new Goals_GUI().goals_GUI();
                        this.setVisible(false);
                    });
                } else if (mode == gfxItem) {
                    modeButton.addActionListener(e -> {
                        GFX_GUI gfxGui = new GFX_GUI();
                        gfxGui.setVisible(true);
                        this.setVisible(false);
                    });
//                } else if (mode == sdItem) {
//                    modeButton.addActionListener(e -> {
//                        new Naval_hull_designer().setVisible(true);
//                        this.setVisible(false);
//                    });

                } else if (mode == fdItem) {
                    modeButton.addActionListener(e -> {
                        FleetDesigner_GUI fleetDesigner = new FleetDesigner_GUI();
                        fleetDesigner.showGUI();
                        this.setVisible(false);
                    });
                } else if (mode == shlItem) {
                    modeButton.addActionListener(e -> {
                        new SHIP_GFX_GUI().gfx_GUI();
                        this.setVisible(false); // Main_GUIを一旦閉じる
                    });
                } else if (mode == countryItem) {
                    // countryItemのアクションを設定
                } else if (mode == moduleItem) {
                    modeButton.addActionListener(e -> {
                        new module_maker_GUI().moduleMakerGUI();
                        this.setVisible(false); // Main_GUIを一旦閉じる
                    });
                } else if (mode == NameItem) {
                    modeButton.addActionListener(e -> {
                        new Name_GUI().name_GUI();
                        this.setVisible(false);
                    });
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
        Core.out.println("MacOS");
    }

    public static void repainting() {
        languageMenu.setText(LG_SELECT);
        englishItem.setText(ENGLISH);
        japaneseItem.setText(JAPANESE);
        modeMenu.setText(MODE_SELECT);
        localizeItem.setText(LOCALIZE);
        goalItem.setText(GOAL);
        gfxItem.setText(GFX);
        shlItem.setText(SHL);
        moduleItem.setText(MODULE);
        fdItem.setText(FDI);
        countryItem.setText(COUNTRY);
        NameItem.setText(NAME);
        convertItem.setText(CVRT);

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
        int shortcutKey = tk.getMenuShortcutKeyMask();
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
            C_languages();
        }
    }

    public static void onPreference() {
        new Preference_GUI();
    }

    public void quit() {
        JOptionPane.showMessageDialog(this, FIN);
        Core.out.println("Application closed.");
        Core.BREAK();
    }

    public static Main_GUI getInstance() {
        if (instance == null) {
            try {
                instance = new Main_GUI(Title);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private void applyFontToAllGUI(String font) {
        Font newFont = new Font(font, Font.PLAIN, 12);
        UIManager.put("Label.font", newFont);
        UIManager.put("Button.font", newFont);
        UIManager.put("ComboBox.font", newFont);
        UIManager.put("TextField.font", newFont);
        UIManager.put("TextArea.font", newFont);
        UIManager.put("Panel.font", newFont);
        SwingUtilities.updateComponentTreeUI(this);
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
                Class<?> clz = Class.forName(MF_CLASS_NAME);
                Method mtd = clz.getMethod("setupScreenMenu", MainFrame.class);
                MainFrame mainFrameInstance = new MainFrame();
                mtd.invoke(null, mainFrameInstance);
            }
            mainFrame.setLocationRelativeTo(null);
            // 画面表示
            mainFrame.setVisible(true);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void setModList(List<String> modList) {
        Main_GUI.modList = modList;
    }
}