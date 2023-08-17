package eightman.library.inc.CUI;

import static eightman.library.inc.CUI.Main_CUI.*;

import java.io.*;
import eightman.library.inc.CUI.System.Log_System;
import eightman.library.inc.CUI.ship_name_type.SHIP_NAME;
import eightman.library.inc.CUI.ship_name_type.SHIP_NAME_TYPES;

public class ship_name {

  public static String Ship_Type = "";

  public void ship_names() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    System.out.println("艦名ファイルの作成を開始します。");

    temp =
      "#This file was created using software created by \n#eightman.library \n";
    System.out.println(System.getProperty("user.home"));
    SHIP_NAME_TYPES sn = new SHIP_NAME_TYPES();

    try {
      System.out.println("国家タグを入力してください。");
      Ctag = br.readLine();
      file_name = Ctag + "_ship_names.txt";
      System.out.println("file neme is「" + file_name + "」");
      FileOutputStream fos = new FileOutputStream(
        System.getProperty("user.home") + "/Desktop/" + file_name
      );
      OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
      while (true) {
        System.out.println("船のタイプを下から選び、数字で入力してください。");
        System.out.println(
          "[0]駆逐艦 [1]軽巡洋艦 [2]重巡洋艦 [3]戦艦 [4]巡洋戦艦 [5]航空母艦 [6]軽空母 [7]大型潜水艦 [8]中型潜水艦 [9]小型潜水艦 [10]都市 [11]山  [12]川 [13]植物 [14]旧国名 [15]島 [16]海峡 [17]鳥 [18]魚"
        );
        Type = Integer.parseInt(br.readLine());
        if (Type == 0) {
          Ship_Type = "駆逐艦";
          sn.DESTROYER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 1) {
          Ship_Type = "軽巡洋艦";
          sn.LIGHT_CRUISER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 2) {
          Ship_Type = "重巡洋艦";
          sn.HEAVY_CRUISER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 3) {
          Ship_Type = "戦艦";
          sn.BATTLESHIP();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 4) {
          Ship_Type = "巡洋戦艦";
          sn.BATTLE_CRUISER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 5) {
          Ship_Type = "航空母艦";
          sn.AIRCRAFT_CARRIER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 6) {
          Ship_Type = "軽空母";
          sn.LIGHT_CARRIER();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 7) {
          Ship_Type = "大型潜水艦";
          sn.HEAVY_SUBMAIRNE();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 8) {
          Ship_Type = "中型潜水艦";
          sn.SUBMARINE();
        } else if (Type == 9) {
          Ship_Type = "小型潜水艦";
          sn.LIGHT_SUBMARINE();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 10) {
          Ship_Type = "都市";
          sn.CITIES();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 11) {
          Ship_Type = "山";
          sn.MOUNTAINS();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 12) {
          Ship_Type = "川";
          sn.RIVERS();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 13) {
          Ship_Type = "植物";
          sn.PLANTS();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 14) {
          Ship_Type = "旧国名・州";
          sn.PREFECTURES();
        } else if (Type == 15) {
          Ship_Type = "島";
          sn.ISLANDS();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 16) {
          Ship_Type = "海峡";
          sn.STRAIT();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 17) {
          Ship_Type = "鳥";
          sn.BIRD();
          SHIP_NAME.SHIP_NAMES();
        } else if (Type == 18) {
          Ship_Type = "魚";
          sn.FISH();
          SHIP_NAME.SHIP_NAMES();
        } else {
          Log_System.ERROR();
        }
        System.out.println(temp);
        System.out.println(
          "続いて他の国を入力しますか？しない場合[0]する場合は[1]"
        );
        sw = Integer.parseInt(br.readLine());
        System.out.println(sw);
        if (sw == 0) {
          System.out.println(
            "新しいファイルには入力した国の名前しか入っていません。お気をつけください。よきmoddingを！"
          );
          System.out.println("ソフトを終了します。");
          break;
        }
      }
      osw.write(temp);
      osw.close();
      fos.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
