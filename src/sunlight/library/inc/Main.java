package sunlight.library.inc;
import java.io.*;
import sunlight.library.inc.System.Log_System;
import sunlight.library.inc.System.ROOT;

public class Main {
    public static String temp = "";
    static String file_name = "";
    public static String Stype = "";
    public static String IN_Stype = "";
    public static String Ctag = "";
    public static String   INS;
    static int sw = 1;
    public static int Type = 0;
    public static int num = 0;
    public static String LSTR = "";
    public static  String MSTR = "";
    public static int L_mode = 0;
    public static int mode = 0;
    public static void main(String[] args) {
        ROOT root = new ROOT();
        root.start();
        if (mode == 1){//翻訳
            root.localize();
        }
        else if (mode == 2){
            root.equipmenttool();
        }
        else if (mode == 3){
            root.peoplename();
        }//人名
        else if (mode == 4){
            root.shipname();
        }
        else if (mode == 5){//翻訳
            root.countryname();
        }
        else {
            Log_System.ERROR();
        }
    }
}

