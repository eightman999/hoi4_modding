package eightman.library.CUI.equipment.ship_modules;

import static eightman.library.CUI.Main_CUI.MSTR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class build_cost_resources {

  public void build_cost_resource() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    try {
      MSTR = MSTR + "\t\tbuild_cost_resources = {\n";
      System.out.println("資源(スチール)？");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tsteel = " + br.readLine() + "\n";
      }
      System.out.println("資源(クロム)？");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\tchromium = " + br.readLine() + "\n";
      }
      System.out.println("資源(ゴム)？");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\trubber = " + br.readLine() + "\n";
      }
      System.out.println("資源(アルミ)？");
      if (Integer.parseInt(br.readLine()) == 0) {} else {
        MSTR = MSTR + "\t\t\taluminium = " + br.readLine() + "\n";
      }
      MSTR = MSTR + "\t\t}" + br.readLine() + "\n";
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
