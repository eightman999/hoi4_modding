package eightman.library.CUI;

import eightman.library.CUI.System.Log_System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static eightman.library.CUI.Main_CUI.*;

public class start {
    public void START(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println(temp + "in" + file_name);
        try {
            System.out.println("\n言語を以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1] EN  [2] JP  ");
            L_mode = Integer.parseInt(br.readLine());
            System.out.println("\nモードを以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1]翻訳ファイルのみ  [2]テクノロジー  [3]人名 [4]艦名[5]国名");
            mode = Integer.parseInt(br.readLine());
            if (mode == 1||mode == 2||mode == 3||mode == 4||mode == 5){
            }else {
                Log_System.ERROR();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
