package sunlight.library.inc.equipment;

import sunlight.library.inc.System.Log_System;
import sunlight.library.inc.equipment.ship_modules.add_average_stats;
import sunlight.library.inc.equipment.ship_modules.add_stats;
import sunlight.library.inc.equipment.ship_modules.build_cost_resources;
import sunlight.library.inc.equipment.ship_modules.multiply_stats;

import java.io.*;

import static sunlight.library.inc.Main.*;

public class ship_equipment_modules {
    public static void SEM() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int sw = 1;
        String  LM = "";
        String temp;
        try {
            if (L_mode == 1) {
               LM = "english";
            }else if (L_mode == 2) {
                LM = "japanese";
            }
            FileOutputStream localization = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "00_ship_modules_l_"+LM+".yml");
            FileOutputStream modules = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "00_ship_modules.txt" );
            OutputStreamWriter localizationosw = new OutputStreamWriter(localization, "UTF-8");
            OutputStreamWriter modulesosw = new OutputStreamWriter(modules, "UTF-8");
            MSTR=MSTR+"equipment_modules = {\n";
            while (true){
                System.out.println("装備のシステム名を入力");
                temp =br.readLine();
                MSTR=MSTR+"\t"+temp+" = {\n";
                LSTR = LSTR + temp + ":0" + "\"";
                System.out.println("装備の実際の名前を入力");
                LSTR=LSTR+" "+ br.readLine()+"\"\n";
                System.out.println("カテゴリー?");
                MSTR=MSTR+"\t\tcategory = "+br.readLine()+"\n";
                System.out.println("前提技術?");
                MSTR=MSTR+"\t\tparent = "+br.readLine()+"\n";
                System.out.println("sfx?");
                MSTR=MSTR+"\t\tsfx = "+br.readLine()+"\n\n";
                System.out.println("statsを入力してください。ない場合は0を入力してください");
                System.out.println("statsを入力してください。ある場合はそれ以外をを入力したのちステータスを入力してください");
                //ステータス
                add_stats as = new add_stats();
                as.add_stats();
                //multiply_stats
                System.out.println("%のstatsを入力してください。ない場合は0を入力してください");
                System.out.println("%のstatsを入力してください。ある場合はそれ以外をを入力したのちステータスを入力してください");
                multiply_stats ms = new multiply_stats();
                ms.multiply_stats();
                //add_average_stats
                System.out.println("平均値のstatsを入力してください。ない場合は0を入力してください");
                System.out.println("平均値のstatsを入力してください。ある場合はそれ以外をを入力したのちステータスを入力してください");
                add_average_stats aas = new add_average_stats();
                aas.add_average_stats();
                //build_cost_resources
                build_cost_resources bs = new build_cost_resources();
                bs.build_cost_resources();
                System.out.println("除去コスト？");
                MSTR = MSTR + "\t\tdismantle_cost_ic = " + br.readLine() + "\n";
                System.out.println("クリティカルパーツ？");
                MSTR = MSTR + "\t\tcritical_parts = " + br.readLine() + "\n";
                System.out.println("終了しますか？しない場合[1]する場合は[0]");
                sw = Integer.parseInt(br.readLine());
                System.out.println(sw);
                if (sw==0){
                    MSTR = MSTR + "}}" + br.readLine() + "\n";
                    System.out.println("ソフトを終了します。");
                    break;
                }else {
                    MSTR = MSTR + "\t\t}" + br.readLine() + "\n";
                }
            }
            modulesosw.write(MSTR);
            modulesosw.close();
            localizationosw.write(LSTR);
            localizationosw.close();
            localization.close();
            modules.close();
        }catch (IOException e) {
            Log_System.ERROR();
            System.out.println(e);
        }

    }

}
