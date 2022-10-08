package sunlight.library.inc;
import java.io.*;

import static sunlight.library.inc.Main.*;
import static sunlight.library.inc.Main.L_mode;

public class localize {
    public static void localization(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("翻訳ファイルの作成を開始します。");
        temp = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
        int sw = 1;
        String  LM = "";
        if (L_mode == 1) {
            LM = "english";
        }else if (L_mode == 2) {
            LM = "japanese";
        }
        temp = "l_"+LM+":";
        System.out.println(System.getProperty("user.home"));
        try {
            System.out.println("ファイル名を入力してください。");
            file_name = br.readLine();
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name );
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {

                System.out.println("翻訳前の名前(:0の前)を入力");
                temp = temp + "\n " + br.readLine() + ":0\"";

                System.out.println("翻訳後の名前(:0の後)を入力");
                temp = temp + br.readLine() + "\"";
                System.out.println(temp);
                System.out.println("終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
                System.out.println(sw);
                if (sw == 0){
                    System.out.println("ソフトを終了します。");
                    break;
                }
            }
            osw.write(temp);
            osw.close();
            fos.close();



        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
