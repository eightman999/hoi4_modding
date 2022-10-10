package sunlight.library.inc.country_name_type;

import sunlight.library.inc.Main;
import sunlight.library.inc.System.Log_System;

import java.io.*;

import static sunlight.library.inc.Main.*;

public class baka_sekai {
    public void baka_cn(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("ファイル名を入力してください。");
            file_name = "countries_l_english.yml";
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {
               def.vanilas();
                System.out.println("国家の市民主義の名前を入力");
                temp = temp + Main.TAG + "_civilism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の保守民主主義の名前を入力");
                temp = temp + Main.TAG + "_conservative_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の立憲君主主義の名前を入力");
                temp = temp + Main.TAG + "_constitutional_monarchy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の直接民主主義の名前を入力");
                temp = temp + Main.TAG + "_direct_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の未来主義の名前を入力");
                temp = temp + Main.TAG + "_futurism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の知識主義の名前を入力");
                temp = temp + Main.TAG + "_intellectualism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中道右派の名前を入力");
                temp = temp + Main.TAG + "_rightneutrality:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の無政府主義の名前を入力");
                temp = temp + Main.TAG + "_anarchism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のバカ主義の名前を入力");
                temp = temp + Main.TAG + "_stupidism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の技術主義の名前を入力");
                temp = temp + Main.TAG + "_technicalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の中立民主主義の名前を入力");
                temp = temp + Main.TAG + "_neutrality_democracy:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の変容主義の名前を入力");
                temp = temp + Main.TAG + "_transformationism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の破滅主義の名前を入力");
                temp = temp + Main.TAG + "_ruinism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の縦長主義の名前を入力");
                temp = temp + Main.TAG + "_longitudinalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の横長主義の名前を入力");
                temp = temp + Main.TAG + "_horizontalism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のひんぬリズムの名前を入力");
                temp = temp +Main.TAG + "_hinnulism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家のきょぬリズム主義の名前を入力");
                temp = temp + Main.TAG + "_kyonulism:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);

                System.out.println("国家の無抵抗主義の名前を入力");
                temp = temp + Main.TAG + "_non_resistance:0 \"" + br.readLine() + "\"\n";
                System.out.println(temp);
                def.defaults();
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
