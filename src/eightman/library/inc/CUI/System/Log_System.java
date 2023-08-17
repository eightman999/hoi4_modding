package eightman.library.inc.CUI.System;

public class Log_System {
    public static void ERROR(){
        System.out.println("予期せぬエラーが発生しました。ソフトを終了します。");
        System.exit(0);
    }
    public static void BREAK(){
        System.out.println("ソフトを終了します。");
    }
}
