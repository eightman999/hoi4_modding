package sunlight.library.inc;

import java.io.*;

import sunlight.library.inc.System.Error;

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
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int mode = 0;
        String LSTR = "";
        String MSTR = "";
        System.out.println(temp + "in" + file_name);
        try {
            System.out.println("\n言語を以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1] EN  [2] JP  ");
            L_mode = Integer.parseInt(br.readLine());
            System.out.println("\nモードを以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1]翻訳ファイルのみ  [2]テクノロジー  [3]人名 [4]艦名[5]国名");
            mode = Integer.parseInt(br.readLine());
            if (mode == 1||mode == 2||mode == 3||mode == 4||mode == 5){

            }else {
                Error.ERROR();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mode == 1){//翻訳
            localize lc = new localize();
            lc.localization();
        }
        else if (mode == 2){
            equipmenttool eq = new equipmenttool();
            eq.equipment_modues();
        }
        else if (mode == 3){
            peoplename pn = new peoplename();
            pn.peoplename();
        }//人名
        else if (mode == 4){
           shipname sn = new shipname();
           sn.shipname();
        }
        else if (mode == 5){//翻訳
            countryname cn = new countryname();
            cn.countrynames();
        }
        else {
            Error.ERROR();
        }





    }
}

