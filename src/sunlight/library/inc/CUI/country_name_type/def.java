package sunlight.library.inc.CUI.country_name_type;
import sunlight.library.inc.CUI.Main_CUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sunlight.library.inc.CUI.Main_CUI.*;

public class def {
    public static void defaults(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("国家のDEFの名前を入力");
        try {
            temp = temp + Main_CUI.TAG + "_DEF:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);

            System.out.println("国家のADJの名前を入力");
            temp = temp + Main_CUI.TAG + "_ADJ:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);

            System.out.println("終了しますか？しない場合[1]する場合は[0]");
            sw = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void vanilas(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("国家の民主主義の名前を入力");
            temp = temp +  Main_CUI.TAG + "_democratic:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);

            System.out.println("国家の共産主義の名前を入力");
            temp = temp +  Main_CUI.TAG + "_communism:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);

            System.out.println("国家のファシズムの名前を入力");
            temp = temp +  Main_CUI.TAG + "_fascism:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);

            System.out.println("国家の中道主義の名前を入力");
            temp = temp +  Main_CUI.TAG + "_neutrality:0 \"" + br.readLine() + "\"\n";
            System.out.println(temp);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
