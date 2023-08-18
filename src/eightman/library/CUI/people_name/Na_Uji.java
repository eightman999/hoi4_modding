package eightman.library.CUI.people_name;

import static eightman.library.CUI.Main_CUI.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Na_Uji {

  public void NAME() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    System.out.println("[1]名・氏で男性名の入力を開始します。");
    temp = temp + "\tmale = { \n\t\tnames = {";
    System.out.println("男性の名前を入力してください。");
    int num = 0;
    try {
      int sw;
      while (true) {
        num = num + 1;
        System.out.println("男性の名前(" + num + "人目)を入力してください。");
        temp = temp + " \"" + br.readLine() + "\" ";
        System.out.println(
          "男性の名前の入力を終了しますか？しない場合[1]する場合は[0]"
        );
        sw = Integer.parseInt(br.readLine());
        if (sw == 0) {
          System.out.println(
            "男性名の入力を終了します。男性のコールサインを入力を始めます。"
          );
          temp = temp + "\n" + "}\n";
          sw = 1;
          num = 0;
          temp = temp + "callsigns = {\n\t";
          while (true) {
            num = num + 1;
            System.out.println(
              "男性のコールサイン(" + num + "人目)を入力してください。"
            );
            temp = temp + " \"" + br.readLine() + "\" ";
            System.out.println(
              "男性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]"
            );
            sw = Integer.parseInt(br.readLine());
            if (sw == 0) {
              System.out.println("男性のコールサインの入力を終わります。");
              temp = temp + "\n" + "}\n}\n";
              break;
            }
          }
          break;
        }
      }
      System.out.println("続いて[1]名・氏で女性名の入力を開始します。");
      temp = temp + "female = { \n\t\tnames = {";
      num = 0;
      while (true) {
        num = num + 1;
        System.out.println("女性の名前(" + num + "人目)を入力してください。");
        temp = temp + " \"" + br.readLine() + "\" ";
        System.out.println(
          "女性の名前の入力を終了しますか？しない場合[1]する場合は[0]"
        );
        sw = Integer.parseInt(br.readLine());
        if (sw == 0) {
          System.out.println(
            "女性名の入力を終了します。女性のコールサインを入力を始めます。"
          );
          temp = temp + "\n" + "}\n";
          sw = 1;
          num = 0;
          temp = temp + "callsigns = {\n\t";
          while (true) {
            num = num + 1;
            System.out.println(
              "女性のコールサイン(" + num + "人目)を入力してください。"
            );
            temp = temp + " \"" + br.readLine() + "\" ";
            System.out.println(
              "女性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]"
            );
            sw = Integer.parseInt(br.readLine());
            if (sw == 0) {
              System.out.println("女性のコールサインの入力を終わります。");
              temp = temp + "\n" + "}\n}\n";
              break;
            }
          }
          break;
        }
      }
      System.out.println("続いて[1]名・名で苗字の入力を開始します。");
      temp = temp + "\n surnames = {\n";
      num = 0;
      while (true) {
        num = num + 1;
        System.out.println("苗字(" + num + "人目)を入力してください。");
        temp = temp + " \"" + br.readLine() + "\" ";
        System.out.println(
          "苗字の入力を終了しますか？しない場合[1]する場合は[0]"
        );
        sw = Integer.parseInt(br.readLine());
        if (sw == 0) {
          System.out.println("苗字の入力を終わります。");
          temp = "\n" + "}\n}\n";
          break;
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
