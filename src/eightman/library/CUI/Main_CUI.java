package eightman.library.CUI;

import eightman.library.CUI.System.Log_System;
import eightman.library.CUI.System.ROOT;

public class Main_CUI {

  public static String temp = "";
  public static String file_name = "";
  public static String S_type = "";
  public static String IN_Stype = "";
  public static String Ctag = "";
  public static String INS;
  public static int sw = 1;
  public static int Type = 0;
  public static int num = 0;
  public static String LSTR = "";
  public static String MSTR = "";
  public static int L_mode = 0;
  public static String TAG = "";
  static int mode = 0;

  public static void main(String[] args) {
    ROOT root = new ROOT();
    root.start();
    if (mode == 1) { //翻訳
      root.localize();
    } else if (mode == 2) {
      root.equipmenttool();
    } else if (mode == 3) {
      root.peoplename();
    } //人名
    else if (mode == 4) {
      root.ship_name();
    } else if (mode == 5) { //翻訳
      root.country_name();
    } else {
      Log_System.ERROR();
    }
  }
}
