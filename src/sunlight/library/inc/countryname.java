package sunlight.library.inc;
import sunlight.library.inc.System.Log_System;

import java.io.*;

import static sunlight.library.inc.Main.*;
import static sunlight.library.inc.Main.L_mode;

public class countryname {
    public static void countrynames(){
        int switchs = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("国家名翻訳の作成を開始します。\n");
        temp = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
        String  LM = "";
        if (L_mode == 1) {
            LM = "english";
        }else if (L_mode == 2) {
            LM = "japanese";
        }
        temp = "l_"+LM+":";
        int sw = 1;
        System.out.println(System.getProperty("user.home"));
        System.out.println("どの世界線での国家の名前？\n");
        System.out.println("[1]バカ世界地図 [2]バニラ\n");
        try {
            switchs = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (switchs == 1){
            baka baka = new baka();
            baka.baka_cn();
        }else if (switchs == 2){
            vanila vanila = new vanila();
            vanila.vanila_cn();
        }
    }
}

class baka {
    void baka_cn(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("ファイル名を入力してください。");
            file_name = "countries_l_english.yml";
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {
                String TAG = "";
                System.out.println("国家タグを入力");
                TAG = br.readLine();
                temp = temp + "\n";

                System.out.println("国家の名前を入力");
                temp = temp + TAG + ":0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);


                System.out.println("国家の民主主義の名前を入力");
                temp = temp + TAG + "_democratic:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の共産主義の名前を入力");
                temp = temp + TAG + "_communism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のファシズムの名前を入力");
                temp = temp + TAG + "_fascism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中道主義の名前を入力");
                temp = temp + TAG + "_neutrality:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の市民主義の名前を入力");
                temp = temp + TAG + "_civilism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の保守民主主義の名前を入力");
                temp = temp + TAG + "_conservative_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の立憲君主主義の名前を入力");
                temp = temp + TAG + "_constitutional_monarchy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の直接民主主義の名前を入力");
                temp = temp + TAG + "_direct_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の未来主義の名前を入力");
                temp = temp + TAG + "_futurism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の知識主義の名前を入力");
                temp = temp + TAG + "_intellectualism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中道右派の名前を入力");
                temp = temp + TAG + "_rightneutrality:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の無政府主義の名前を入力");
                temp = temp + TAG + "_anarchism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のバカ主義の名前を入力");
                temp = temp + TAG + "_stupidism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の技術主義の名前を入力");
                temp = temp + TAG + "_technicalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中立民主主義の名前を入力");
                temp = temp + TAG + "_neutrality_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の変容主義の名前を入力");
                temp = temp + TAG + "_transformationism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の破滅主義の名前を入力");
                temp = temp + TAG + "_ruinism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の縦長主義の名前を入力");
                temp = temp + TAG + "_longitudinalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の横長主義の名前を入力");
                temp = temp + TAG + "_horizontalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のひんぬリズムの名前を入力");
                temp = temp + TAG + "_hinnulism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のきょぬリズム主義の名前を入力");
                temp = temp + TAG + "_kyonulism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の無抵抗主義の名前を入力");
                temp = temp + TAG + "_non_resistance:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のDEFの名前を入力");
                temp = temp + TAG + "_DEF:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のADJの名前を入力");
                temp = temp + TAG + "_ADJ:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);


                System.out.println("終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
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

class vanila {
    void vanila_cn(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("ファイル名を入力してください。");
            file_name = "countries_l_english.yml";
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {
                String TAG = "";
                System.out.println("国家タグを入力");
                TAG = br.readLine();
                temp = temp + "\n";

                System.out.println("国家の名前を入力");
                temp = temp + TAG + ":0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);


                System.out.println("国家の民主主義の名前を入力");
                temp = temp + TAG + "_democratic:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の共産主義の名前を入力");
                temp = temp + TAG + "_communism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のファシズムの名前を入力");
                temp = temp + TAG + "_fascism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中道主義の名前を入力");
                temp = temp + TAG + "_neutrality:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のDEFの名前を入力");
                temp = temp + TAG + "_DEF:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のADJの名前を入力");
                temp = temp + TAG + "_ADJ:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
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

