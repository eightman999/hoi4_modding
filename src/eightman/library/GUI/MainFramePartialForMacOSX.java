package eightman.library.GUI;

import com.apple.eawt.Application;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

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


        // Dockアイコンの設定 Leopard以降のみ
//        try {
//            Class clz = app.getClass();
//            Method mtd = clz.getMethod("setDockIconImage", new Class[] {Image.class});
//            mtd.invoke(app, new Object[] {mainFrame.icon});
//
//        } catch (Exception ex) {
//            // サポートされていない場合は単に無視する.
//        }
    }


}