package eightman.library.CUI;

import static eightman.library.CUI.Main_CUI.*;

import java.io.*;
import eightman.library.CUI.country_name_type.baka_sekai;
import eightman.library.CUI.country_name_type.def;
import eightman.library.CUI.country_name_type.vanila;

public class country_name {

  public static void country_names() {
    int switchs = 0;
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    System.out.println("国家名翻訳の作成を開始します。\n");
    temp =
      "#This file was created using software created by \n#eightman.library \n";
    String LM = "";
    if (L_mode == 1) {
      LM = "english";
    } else if (L_mode == 2) {
      LM = "japanese";
    }
    temp = temp + "l_" + LM + ":";
    System.out.println(System.getProperty("user.home"));
    System.out.println("どの世界線での国家の名前？\n");
    System.out.println("[1]バカ世界地図 [2]バニラ [3]汎用名のみ\n");
    try {
      switchs = Integer.parseInt(br.readLine());
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (switchs == 1) {
      baka_sekai baka = new baka_sekai();
      baka.baka_cn();
    } else if (switchs == 2) {
      vanila vanila = new vanila();
      vanila.vanila_cn();
    } else if (switchs == 3) {
      def.defaults();
    }
  }
}
