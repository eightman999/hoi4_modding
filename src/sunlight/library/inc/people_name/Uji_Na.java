package sunlight.library.inc.people_name;
import sunlight.library.inc.Main.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sunlight.library.inc.Main.temp;

public class Uji_Na {

    public void NAME() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("[0]氏・名で男性名の入力を開始します。");
        temp = temp + "\tmale = { \n\t\tsurnames = {";
        System.out.println("男性の名前を入力してください。");
        int num = 0;
        int sw;
        try {
            while (true) {
                num = num + 1;
                System.out.println("男性の名前(" + num + "人目)を入力してください。");
                temp = temp + " \"" + br.readLine() + "\" ";
                System.out.println("男性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
                if (sw == 0) {
                    System.out.println("男性名の入力を終了します。男性のコールサインを入力を始めます。");
                    temp = temp + "\n" + "}\n";
                    sw = 1;
                    num = 0;
                    temp = temp + "callsigns = {\n\t";
                    while (true) {
                        num = num + 1;
                        System.out.println("男性のコールサイン(" + num + "人目)を入力してください。");
                        temp = temp + " \"" + br.readLine() + "\" ";
                        System.out.println("男性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
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
            System.out.println("続いて[0]氏・名で女性名の入力を開始します。");
            temp = temp + "\tfemale = { \n\t\tsurnames = {";
            num = 0;
            while (true) {
                num = num + 1;
                System.out.println("女性の名前(" + num + "人目)を入力してください。");
                temp = temp + " \"" + br.readLine() + "\" ";
                System.out.println("女性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
                if (sw == 0) {
                    System.out.println("女性名の入力を終了します。女性のコールサインを入力を始めます。");
                    temp = temp + "\n" + "}\n";
                    sw = 1;
                    num = 0;
                    temp = temp + "\tcallsigns = {\n\t";
                    while (true) {
                        num = num + 1;
                        System.out.println("女性のコールサイン(" + num + "人目)を入力してください。");
                        temp = temp + " \"" + br.readLine() + "\" ";
                        System.out.println("女性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
                        sw = Integer.parseInt(br.readLine());
                        if (sw == 0) {
                            System.out.println("女性のコールサインの入力を終わります。");
                            temp = temp + "\n\t" + "}\n}\n";
                            break;
                        }
                    }
                    break;
                }
            }
            System.out.println("続いて[0]氏・名で苗字の入力を開始します。");
            temp = temp + "\n\tnames = {\n";
            num = 0;
            while (true) {
                num = num + 1;
                System.out.println("苗字(" + num + "人目)を入力してください。");
                temp = temp + " \"" + br.readLine() + "\" ";
                System.out.println("苗字の入力を終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
                if (sw == 0) {
                    System.out.println("苗字の入力を終わります。");
                    temp = temp + "\n" + "}\n}\n";
                    break;
                }
            }

        }catch (IOException e) {
            System.out.println(e);
        }
    }
}