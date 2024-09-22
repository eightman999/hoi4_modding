package eightman.library.GUI;

import com.apple.eawt.Application;

public class MainFramePartialForMacOSX {

    private MainFramePartialForMacOSX() {
        super();
    }

    public static void setupScreenMenu(final MainFrame mainFrame) {
        if (mainFrame == null) {
            throw new IllegalArgumentException();
        }

        Application app = Application.getApplication();

        app.setAboutHandler(null); // 「このアプリについて」のメニュー項目。デフォルトでtrue
        app.setPreferencesHandler(null); // 「環境設定」のメニュー項目、デフォルトはfalse

        // "About"メニューアイテムのハンドラーを設定
        app.setAboutHandler(e -> Main_GUI.onAbout());

        // "Preferences"メニューアイテムのハンドラーを設定
        app.setPreferencesHandler(e -> Main_GUI.onPreference());

    }


}