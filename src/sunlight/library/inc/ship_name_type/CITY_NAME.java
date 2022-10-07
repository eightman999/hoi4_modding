package sunlight.library.inc.ship_name_type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sunlight.library.inc.Main.*;

public class CITY_NAME {
    public static void CITIES (){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            while (true) {
                num = num +1;
                Type = 0;
                System.out.println("都市の名前を入力してください。\n");
                Stype = br.readLine();
                temp = temp + "\"" + Stype + "\" ";
                if (num == 10){
                    temp = temp + "\n\t";
                    num = 0;
                }
                System.out.println("都市名の入力は全て終わりましたか？\n" );
                System.out.println("続けて入力する場合は[0]を　終了する場合は[1]を入力してください。");
                Type = Integer.parseInt(br.readLine());
                temp = temp + "\t{\n{\n";

                if (Type == 1){
                    System.out.println("おつかれさまでした。");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
