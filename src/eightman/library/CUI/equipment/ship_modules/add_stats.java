package eightman.library.CUI.equipment.ship_modules;

import static eightman.library.CUI.Main_CUI.MSTR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class add_stats {

  public void add_stat() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    try {
      System.out.println("stats?-軽攻撃?");
      if (Integer.parseInt(br.readLine()) == 0) {
        MSTR = MSTR + "add_stats = {\n";
      } else {
        MSTR =
          MSTR + "add_stats = {\n\t\t\tlg_attack = " + br.readLine() + "\n";
      }
      System.out.println("stats?-重攻撃?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\thg_attack = " + br.readLine() + "\n";
      }
      System.out.println("stats?-雷撃?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\ttorpedo_attack = " + br.readLine() + "\n";
      }
      System.out.println("stats?-対空?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tanti_air_attack = " + br.readLine() + "\n";
      }

      System.out.println("stats?-海上発見?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tsurface_detection = " + br.readLine() + "\n";
      }
      System.out.println("stats?-海中発見?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tsub_detection = " + br.readLine() + "\n";
      }
      System.out.println("stats?-海上被発見?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tsurface_visibility = " + br.readLine() + "\n";
      }
      System.out.println("stats?-海中被発見?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tsub_visibility = " + br.readLine() + "\n";
      }
      System.out.println("stats?-装甲?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tarmor_value = " + br.readLine() + "\n";
      }
      System.out.println("stats?-体力?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tmax_strength = " + br.readLine() + "\n";
      }
      System.out.println("stats?-コスト?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tbuild_cost_ic = " + br.readLine() + "\n";
      }
      System.out.println("stats?-スピード?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tnaval_speed = " + br.readLine() + "\n";
      }
      System.out.println("stats?-燃料消費?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tfuel_consumption = " + br.readLine() + "\n";
      }
      MSTR = MSTR + "\t\t}\n\n";
      System.out.println("stats?-人員?");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tmanpower = " + br.readLine() + "\n";
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
