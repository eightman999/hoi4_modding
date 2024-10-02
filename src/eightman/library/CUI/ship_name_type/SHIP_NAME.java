package eightman.library.CUI.ship_name_type;

import static eightman.library.CUI.Main_CUI.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import eightman.library.CUI.ship_name;
import eightman.library.Core;

public class SHIP_NAME {

  public static void SHIP_NAMES() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    try {
      while (true) {
        Type = 0;
        System.out.println("適応する船のタイプを入力してください。\n");
        IN_Stype = br.readLine();
        S_type = S_type + " " + IN_Stype;
        System.out.println(
          "適応する船のタイプは" + S_type + "のみでよろしいですか？\n"
        );
        System.out.println(
          "よければ[0]を まだ入力する場合は[1]を入力してください。"
        );
        Type = Integer.parseInt(br.readLine());
        if (Type == 1) {
          System.out.println("引き続き入力があります。");
          break;
        }
      }
      System.out.println(
        "艦名枯渇時の名前を入れてください。番号は[%d]を入力してください。"
      );
      INS = br.readLine();
      temp = temp + "\tfallback_name = \"" + INS + "\"\n\n";
      temp = temp + "unique = {\n";
      while (true) {
        num = num + 1;
        Type = 0;
        System.out.println(
          ship_name.Ship_Type + "の名前を入力してください。\n"
        );
        S_type = br.readLine();
        temp = temp + "\"" + S_type + "\" ";
        if (num == 10) {
          temp = temp + "\n\t";
          num = 0;
        }
        System.out.println("艦名の入力は全て終わりましたか？\n");
        System.out.println(
          "続けて入力する場合は[0]を 終了する場合は[1]を入力してください。"
        );
        Type = Integer.parseInt(br.readLine());
        temp = temp + "\t{\n{\n";

        if (Type == 1) {
          System.out.println("おつかれさまでした。");
          break;
        }
      }
    } catch (IOException e) {
      Core.out.logError("Failed to load config.", e);
    }
  }
}
