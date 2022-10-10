package sunlight.library.inc;
import sunlight.library.inc.System.Log_System;
import sunlight.library.inc.country_name_type.baka_sekai;
import sunlight.library.inc.country_name_type.def;
import sunlight.library.inc.country_name_type.vanila;

import java.io.*;

import static sunlight.library.inc.Main.*;
import static sunlight.library.inc.Main.L_mode;

public class countryname {
    public static void countrynames(){
        int switchs = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("国家名翻訳の作成を開始します。\n");
        temp = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
        String  LM = "";
        if (L_mode == 1) {
            LM = "english";
        }else if (L_mode == 2) {
            LM = "japanese";
        }
        temp = temp + "l_"+LM+":";
        int sw = 1;
        System.out.println(System.getProperty("user.home"));
        System.out.println("どの世界線での国家の名前？\n");
        System.out.println("[1]バカ世界地図 [2]バニラ [3]汎用名のみ\n");
        try {
            switchs = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (switchs == 1){
            baka_sekai baka = new baka_sekai();
            baka.baka_cn();
        }else if (switchs == 2){
            vanila vanila = new vanila();
            vanila.vanila_cn();
        }else if (switchs == 3){
            def.defaults();
        }
    }
}



