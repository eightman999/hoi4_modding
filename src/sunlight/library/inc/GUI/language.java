package sunlight.library.inc.GUI;

import sunlight.library.inc.CUI.start;
import sunlight.library.inc.GUI.Main_GUI.*;

public class language {

  public static String DONE = "Done";
  public static String LOCALIZE = "Localize";
  public static String EQUIPMENT = "Equipment";
  public static String COUNTRY = "country";
  public static String NONE = "None";
  public static String MODE_SELECT = "Select Mode";
  public static String LG_SELECT = "Select language";
  public static String ENGLISH = "English";
  public static String JAPANESE = "Japanese";
  public static String Please_Select_mode = "Please Select mode";
  public static String GFX = "GFX";
  public static String GOAL = "Goals";
  public static String SHL = "Ship Hull";
  public static String Title = "Hoi4 modding tool";

  public static void C_languages() {
    if (Main_GUI.L_mode == 0) {
      DONE = "完了";
      LOCALIZE = "翻訳ファイル作成";
      EQUIPMENT = "装備作成";
      COUNTRY = "国家作成";
      NONE = "未選択";
      MODE_SELECT = "モード選択";
      LG_SELECT = "言語選択";
      ENGLISH = "英語";
      JAPANESE = "日本語";
      Please_Select_mode = "Please Select mode";
      SHL = "船体設定";
      GOAL = "NF用画像";
      GFX = "画像適用";
      Title = "Hoi4 mod開発支援ツール";
      System.out.println(DONE);
      // System.out.println(L_mode);
    } else if (Main_GUI.L_mode == 1) {
      DONE = "Done";
      LOCALIZE = "Localize";
      EQUIPMENT = "Equipment";
      COUNTRY = "country";
      NONE = "None";
      MODE_SELECT = "Select Mode";
      LG_SELECT = "Select language";
      ENGLISH = "English";
      JAPANESE = "Japanese";
      Please_Select_mode = "Please Select mode";
      GFX = "GFX";
      GOAL = "Goals";
      SHL = "Ship Hull";
      Title = "Hoi4 modding tool";
    }
  }
}
