package eightman.library.CUI;

import eightman.library.CUI.System.Log_System;
import eightman.library.CUI.people_name.Na_Uji;
import eightman.library.CUI.people_name.Uji_Na;
import eightman.library.GUI.System.MT_core;

import java.io.*;

import static eightman.library.CUI.Main_CUI.*;
public class peoplename {
    public void peoplenames(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("人名ファイルの作成を開始します。");
        String Ctag = "";
        temp = "#This file was created using software created by \n#eightman.library \n";
        int sw = 1;
        int Type = 0;
        System.out.println(System.getProperty("user.home"));
        try {
            file_name = "NEW_00_names.txt";
            System.out.println("file neme is「" + file_name + "」");
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name );
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

            while (true) {
                System.out.println("国家タグを入力してください。");
                Ctag = br.readLine();
                temp = temp + Ctag + " = {\n";

                System.out.println("名前のタイプを下から選び、数字で入力してください。");
                System.out.println("[0]氏・名 [1]名・氏");
                Type = Integer.parseInt(br.readLine());
                if (Type == 0){
                    Uji_Na un = new Uji_Na();
                    un.NAME();
                }else if (Type == 1){
                    Na_Uji nu = new Na_Uji();
                    nu.NAME();
                }else {
                    Log_System.ERROR();
                }

                System.out.println(temp);
                System.out.println("続いて他の国を入力しますか？しない場合[0]する場合は[1]");
                sw = Integer.parseInt(br.readLine());
                System.out.println(sw);
                if (sw == 0){
                    System.out.println("新しいファイルには入力した国の名前しか入っていません。お気をつけください。よきmoddingを！");
                    System.out.println("ソフトを終了します。");
                    break;
                }
            }
            osw.write(temp);
            osw.close();
            fos.close();



        } catch (IOException e) {
            MT_core.MT_System.out.logError("Failed to load config.", e);
        }
    }
}
