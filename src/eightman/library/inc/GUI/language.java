package eightman.library.inc.GUI;

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
  public static String File = "File";
  public static String Save = "Save";
  public static String combined = "combined";
  public static String str_loc = "Starts the creation of a translation file.";
  public static String LR_no = "";
  public static String CCC =
    "It is described by eightman.library and software created by eightman";

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
      Please_Select_mode = "モードを選択してください";
      SHL = "船体設定";
      GOAL = "NF用画像";
      GFX = "画像適用";
      Title = "Hoi4 mod開発支援ツール";
      File = "ファイル";
      Save = "セーブ";
      combined = "結合";
      str_loc = "翻訳ファイルの作成を開始します。";
      LR_no = "左右で行数が違います";
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
      File = "File";
      Save = "Save";
      combined = "combined";
      str_loc = "Starts the creation of a translation file.";
      LR_no = "The number of lines is different on the left and right.";
    }
  }
}
