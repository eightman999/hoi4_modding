package eightman.library.GUI;

import eightman.library.GUI.System.MT_core;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import static eightman.library.GUI.System.Mac_OS.isMac;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // パネル
    protected FileDialogTypePanel fileDlgTypePanel;
    // コンストラクタ
    public MainFrame() throws Exception {
        super();

        // ウィンドウイベントのハンドリング
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // JFrameを閉じるときに呼び出されるが、
                // OSXでcmd-Qで終了するときは呼び出されない.
                // ApplicationListenerで受け取る必要あり.
                quit();
            }
        });

        // ウィンドウアイコンの設定
        // ただし、OSXにはウィンドウアイコンはないため表示されない
        // Dockアイコンは、これによって設定することはできない.
        // Leopard以降はApple Java ExtensionsのApplicationクラスで設定可能.
        this.setIconImage(Main_GUI.icon);

        // メニューの登録
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("ファイル");
        menuBar.add(fileMenu);

        JMenuItem menuSaveAs = new JMenuItem("名前をつけて保存");
        menuSaveAs.setMnemonic(KeyEvent.VK_S);
        menuSaveAs.addActionListener(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
                onSaveAs();
            }
        });
        fileMenu.add(menuSaveAs);

        // システムのデフォルトのコマンド修飾キーを取得する.
        // Windowsならctrl, OSXならばmetaになる.
        Toolkit tk = Toolkit.getDefaultToolkit();
        int shotcutKey = tk.getMenuShortcutKeyMaskEx();

        // コマンドのアクセラレータをシステムのデフォルトのキーの組み合わせで登録
        menuSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shotcutKey));

        // バージョン情報と終了コマンド
        // "apple.laf.useScreenMenuBar"が定義されている場合はシステムで用意するので不要.
//         if (System.getProperty("apple.laf.useScreenMenuBar") == null) {
        if (! isMac()) {
            fileMenu.add(new JToolBar.Separator());
            JMenuItem quitMenu = new JMenuItem("終了(Q)");
            quitMenu.setMnemonic(KeyEvent.VK_Q);
            quitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, shotcutKey));
            quitMenu.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;
                public void actionPerformed(ActionEvent e) {
                    quit();
                }
            });
            fileMenu.add(quitMenu);

            JMenu helpMenu = new JMenu("ヘルプ(H)");
            helpMenu.setMnemonic(KeyEvent.VK_H);
            menuBar.add(helpMenu);

            JMenuItem menuPreference = new JMenuItem("環境設定(E)");
            menuPreference.setMnemonic(KeyEvent.VK_E);
            menuPreference.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, shotcutKey));
            menuPreference.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;
                public void actionPerformed(ActionEvent e) {
                    Main_GUI.onPreference();
                }
            });
            helpMenu.add(menuPreference);

            JMenuItem menuAbout = new JMenuItem("バージョン情報(V)");
            menuAbout.setMnemonic(KeyEvent.VK_V);
            menuAbout.addActionListener(new AbstractAction() {
                private static final long serialVersionUID = 1L;
                public void actionPerformed(ActionEvent e) {
                    Main_GUI.onAbout();
                }
            });
            helpMenu.add(menuAbout);
        }

        // OSXでなければニーモニックを設定する.
        // ニーモニックはOSXでは使われない(無視される)ので、OSXでは設定しない。
        if (! isMac()) {
            fileMenu.setText(fileMenu.getText() + "(F)");
            fileMenu.setMnemonic(KeyEvent.VK_F);
            menuSaveAs.setText(menuSaveAs.getText() + "(S)");
            menuSaveAs.setMnemonic(KeyEvent.VK_S);
        }


        // システムプロパティ「apple.laf.useScreenMenuBar」がtrueであると、
        // JFrameの中にではなく、スクリーンメニューにメニューが設定される.
        // 同時に「〜を終了」「〜について」のメニューも自動的に追加される.
        setJMenuBar(menuBar);

        setTitle("MacJavaSample");
        setSize(300, 300);

        // パネルコンテンツ
        Container contentPane = getContentPane();
        fileDlgTypePanel = new FileDialogTypePanel();
        contentPane.add(fileDlgTypePanel, BorderLayout.NORTH);

        // 画面中央に表示
        setLocationRelativeTo(null);
    }

    // ファイルダイアログのタイプを選択するパネル
    protected static class FileDialogTypePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        private final JRadioButton r1 = new JRadioButton("Awt");

        public FileDialogTypePanel() {
            super();
            setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),
                    new TitledBorder("ファイルダイアログの形式")));
            setLayout(new GridLayout(2, 1));
            ButtonGroup grp = new ButtonGroup();
            grp.add(r1);
            JRadioButton r2 = new JRadioButton("Swing");
            grp.add(r2);
            add(r1);
            add(r2);
            r1.setSelected(true);
        }

        public boolean isAWT() {
            return r1.isSelected();
        }
    }

    protected void onSaveAs() {
        File outFile;
        if (fileDlgTypePanel.isAWT()) {
            FileDialog fdlg = new FileDialog(this,
                    "システムプロパティの一覧を保存します.", FileDialog.SAVE);
            fdlg.setDirectory(System.getProperty("user.home"));
            try {
                // FileDialogはモーダルダイアログのため
                // JFrameの中にメニューがある場合はメニューも操作できない。
                // しかし、OSXのスクリーンメニューは操作可能であるため明示的にディセーブルしておく.
                getJMenuBar().setEnabled(false);

                fdlg.setVisible(true);
            } finally {
                getJMenuBar().setEnabled(true);
            }

            String fname = fdlg.getFile();
            if (fname == null) {
                // cancel
                return;
            }
            // MacOSXのHFSファイルシステムは文字コードは濁点等を分離している、いわゆるUTF-8-MACとも
            // 呼ばれる形式(NFD)であるが、JAVA上では分離されていない通常の状態(NFC)として扱われる.
            // そのため、ファイルの文字コードについてはMac特有の考慮事項はない。
            outFile = new File(fdlg.getDirectory(), fdlg.getFile());

        } else {
            // Swingのファイルダイアログを使う場合
            JFileChooser fdlg = new JFileChooser();
            fdlg.setDialogTitle("システムプロパティの一覧を保存します.(Swing)");

            getJMenuBar().setEnabled(false);
            try {
                int ret = fdlg.showSaveDialog(this);
                if (ret != JFileChooser.APPROVE_OPTION) {
                    return;
                }
            } finally {
                getJMenuBar().setEnabled(true);
            }

            outFile = fdlg.getSelectedFile();
        }
        try {
            // システムプロパティの一覧をファイルに出力
            // プラットフォームに関係なく、UTF-8/LF形式で出力する.
            // なお、同じOSXでもLeopardのJDK5はUTF-8がデフォルトだが、
            // JDK6/SnowLeopardではSJISがデフォルトになる.
            try (Writer wr = new OutputStreamWriter(new FileOutputStream(outFile),
                    StandardCharsets.UTF_8)) {
                // ファイル名を記録
                wr.write(outFile + "\n"); // Macの場合 Option+\ で入力
                wr.write(getSystemProperties());
                wr.flush();
            }
        } catch (Exception ex) {
            MT_core.MT_System.ERROR();
            ex.printStackTrace();

        }
    }

    protected void quit() {
        JOptionPane.showMessageDialog(this, "終了します.");
        System.exit(0);
    }
    // システムプロパティをダンプする

    private String getSystemProperties() {
        ArrayList keys = new ArrayList();
        StringBuilder buf = new StringBuilder();
        for (Enumeration enm = System.getProperties().keys(); enm.hasMoreElements();) {
            String key = (String) enm.nextElement();
            keys.add(key);
        }
        Collections.sort(keys);
        for (Object o : keys) {
            String key = (String) o;
            buf.append(key).append("=").append(System.getProperty(key)).append("\n");
        }
        buf.append("*EOF*");
        return buf.toString();
    }
}