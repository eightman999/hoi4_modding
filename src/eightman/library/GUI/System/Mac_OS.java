package eightman.library.GUI.System;

import eightman.library.GUI.language;

public class Mac_OS {
    public static boolean isMac() {
        // MacOSXで動作しているか?
        String lcOSName = java.lang.System.getProperty("os.name").toLowerCase();
        return lcOSName.startsWith("mac os x");
    }

    public static void mac_system() throws ClassNotFoundException {
        // JFrameにメニューをつけるのではなく、一般的なOSXアプリ同様に画面上端のスクリーンメニューにする.
        java.lang.System.setProperty("apple.laf.useScreenMenuBar", "true");
        //アプリケーション名の指定
        language.C_languages(); // ここで言語設定を更新
        java.lang.System.setProperty("apple.awt.application.name", language.Title);
        // スクリーンメニュー左端に表記されるアプリケーション名を設定する
        // (何も設定しないとクラス名になる。)
        java.lang.System.setProperty(
                "com.apple.mrj.application.apple.menu.about.name",
                language.Title);
        java.lang.System.setProperty("apple.laf.useScreenMenuBar", "true");
    }

    public static void mac_title() {
        language.C_languages(); // ここで言語設定を更新
        // JFrameにメニューをつけるのではなく、一般的なOSXアプリ同様に画面上端のスクリーンメニューにする.
        java.lang.System.setProperty("apple.laf.useScreenMenuBar", "true");
        //アプリケーション名の指定
        language.C_languages(); // ここで言語設定を更新
        java.lang.System.setProperty("apple.awt.application.name", language.Title);
        // スクリーンメニュー左端に表記されるアプリケーション名を設定する
        // (何も設定しないとクラス名になる。)
        java.lang.System.setProperty(
                "com.apple.mrj.application.apple.menu.about.name",
                language.Title);
        java.lang.System.setProperty("apple.laf.useScreenMenuBar", "true");
    }


}
