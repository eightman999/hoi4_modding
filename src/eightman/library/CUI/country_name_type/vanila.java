package eightman.library.CUI.country_name_type;

import eightman.library.CUI.System.Log_System;

import java.io.*;

import static eightman.library.CUI.Main_CUI.*;

public class vanila {
    public void vanila_cn(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("ファイル名を入力してください。");
            file_name = "countries_l_english.yml";
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {

                System.out.println("国家タグを入力");
                TAG = br.readLine();
                temp = temp + "\n";
                def.vanilas();
                System.out.println("国家の名前を入力");
                temp = temp + TAG + ":0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                def.defaults();
                System.out.println(sw);
                if (sw == 0) {
                    Log_System.BREAK();
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
