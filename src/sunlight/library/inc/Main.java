package sunlight.library.inc;

import java.io.*;
import java.util.Random;
import java.util.UUID;
import sunlight.library.inc.*;
import sunlight.library.inc.ship_name_type.SHIP_NAME_TYPES;

public class Main {
    public static String temp = "";
    static String file_name = "";
    public static String Stype = "";
    public static String IN_Stype = "";
    public static String Ctag = "";
    public static String   INS;
    static int sw = 1;
    public static int Type = 0;
    public static int num = 0;
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int mode = 0;

        String LSTR = "";
        String MSTR = "";
        System.out.println(temp + "in" + file_name);
        try {

            System.out.println("\nモードを以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1]翻訳ファイルのみ  [2]テクノロジー  [3]人名 [4]艦名[5]国名");
            mode = Integer.parseInt(br.readLine());
            if (mode == 1||mode == 2||mode == 3||mode == 4||mode == 5){

            }else {
                System.out.println("想定されていない番号です。ソフトを終了します。");
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mode == 1){//翻訳
            localize lc = new localize();
            lc.localization();
        }
        else if (mode == 2){
            System.out.println("\nモードを以下から選び、番号でキーボードから入力してください");
            System.out.println("\n[1]Air-equipments  [2]Ship-modules");
            MSTR = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
            LSTR = "l_english:\n";

            try {
                mode = Integer.parseInt(br.readLine());
                if (mode == 1||mode == 2){

                }else {
                    System.out.println("想定されていない番号です。ソフトを終了します。");
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (mode == 1){

            }
            else if (mode == 2){
                int sw = 1;
                try {
                    FileOutputStream localization = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "00_ship_modules_l_english.yml" );
                    FileOutputStream modules = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + "00_ship_modules.txt" );
                    OutputStreamWriter localizationosw = new OutputStreamWriter(localization, "UTF-8");
                    OutputStreamWriter modulesosw = new OutputStreamWriter(modules, "UTF-8");
                    MSTR=MSTR+"equipment_modules = {\n";

                        while (true){
                            System.out.println("装備のシステム名を入力");

                            MSTR=MSTR+"\t"+br.readLine()+" = {\n";



                            System.out.println("装備の実際の名前を入力");
                            LSTR=LSTR+" "+ br.readLine()+"\n";


                            System.out.println("カテゴリー?");

                            MSTR=MSTR+"\t\tcategory = "+br.readLine()+"\n";

                            System.out.println("前提技術?");

                            MSTR=MSTR+"\t\tparent = "+br.readLine()+"\n";

                            System.out.println("sfx?");

                            MSTR=MSTR+"\t\tsfx = "+br.readLine()+"\n\n";
                            System.out.println("statsを入力してください。ない場合は0を入力してください");

                            //ステータス

                            System.out.println("stats?-軽攻撃?");
                            if (Integer.parseInt(br.readLine())==0) { MSTR=MSTR + "add_stats = {\n"; }else {
                                MSTR = MSTR + "add_stats = {\n\t\t\tlg_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-重攻撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                            MSTR=MSTR+"\t\t\thg_attack = "+br.readLine()+"\n";
                            }
                            System.out.println("stats?-雷撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\ttorpedo_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-対空?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tanti_air_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-人員?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmanpower = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海上発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海中発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海上発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海中発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海上被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海中被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-装甲?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tarmor_value = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-体力?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmax_strength = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-コスト?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tbuild_cost_ic = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-スピード?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tnaval_speed = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-燃料消費?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tfuel_consumption = " + br.readLine() + "\n";
                            }
                            MSTR=MSTR+"\t\t}\n\n";

                            //multiply_stats

                            System.out.println("multiply_stats?-軽攻撃?");
                            if (Integer.parseInt(br.readLine())==0) { MSTR=MSTR + "multiply_stats = {\n"; }else {
                                MSTR = MSTR + "multiply_stats = {\n\t\t\tlg_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-重攻撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR=MSTR+"\t\t\thg_attack = "+br.readLine()+"\n";
                            }
                            System.out.println("multiply_stats?-雷撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\ttorpedo_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-対空?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tanti_air_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-人員?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmanpower = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-海上発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-海中発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海上被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海中被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-装甲?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tarmor_value = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-体力?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmax_strength = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-コスト?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tbuild_cost_ic = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-スピード?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tnaval_speed = " + br.readLine() + "\n";
                            }
                            System.out.println("multiply_stats?-燃料消費?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tfuel_consumption = " + br.readLine() + "\n";
                            }
                            MSTR=MSTR+"\t\t}\n\n";

                            //add_average_stats

                            System.out.println("add_average_stats?-軽攻撃?");
                            if (Integer.parseInt(br.readLine())==0) { MSTR=MSTR + "add_average_stats = {\n"; }else {
                                MSTR = MSTR + "add_average_stats = {\n\t\t\tlg_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-重攻撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR=MSTR+"\t\t\thg_attack = "+br.readLine()+"\n";
                            }
                            System.out.println("add_average_stats?-雷撃?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\ttorpedo_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-対空?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tanti_air_attack = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-人員?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmanpower = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-海上発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-海中発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_detection = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海上被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsurface_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-海中被発見?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsub_visibility = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-装甲?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tarmor_value = " + br.readLine() + "\n";
                            }
                            System.out.println("stats?-体力?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tmax_strength = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-コスト?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tbuild_cost_ic = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-スピード?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tnaval_speed = " + br.readLine() + "\n";
                            }
                            System.out.println("add_average_stats?-燃料消費?");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tfuel_consumption = " + br.readLine() + "\n";
                            }
                            MSTR=MSTR+"\t\t}\n\n";
                            MSTR = MSTR + "\t\tbuild_cost_resources = {\n";
                            System.out.println("資源(スチール)？");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tsteel = " + br.readLine() + "\n";
                            }
                            System.out.println("資源(クロム)？");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\tchromium = " + br.readLine() + "\n";
                            }
                            System.out.println("資源(ゴム)？");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\trubber = " + br.readLine() + "\n";
                            }
                            System.out.println("資源(アルミ)？");
                            if (Integer.parseInt(br.readLine())==0) {}else {
                                MSTR = MSTR + "\t\t\taluminium = " + br.readLine() + "\n";
                            }
                            System.out.println("除去コスト？");
                            MSTR = MSTR + "\t\tdismantle_cost_ic = " + br.readLine() + "\n";

                            System.out.println("クリティカルパーツ？");
                            MSTR = MSTR + "\t\tcritical_parts = " + br.readLine() + "\n";

                            System.out.println("終了しますか？しない場合[1]する場合は[0]");
                            sw = Integer.parseInt(br.readLine());
                            System.out.println(sw);
                            if (sw==0){
                                System.out.println("ソフトを終了します。");
                                break;
                            }
                        }


                    modulesosw.write(MSTR);
                    modulesosw.close();

                    localizationosw.write(LSTR);
                    localizationosw.close();

                    localization.close();
                    modules.close();
                }catch (IOException e) {
                    System.out.println(e);
                }
            }else {
                System.out.println("想定されていない番号です。ソフトを終了します。");
                System.exit(0);
            }

        }
        else if (mode == 3){
            System.out.println("人名ファイルの作成を開始します。");
            String Ctag = "";
            temp = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
            int sw = 1;
            int Type = 0;
            int num = 0;

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
                    System.out.println("[0]氏・名　[1]名・氏");
                    Type = Integer.parseInt(br.readLine());
                    if (Type == 0){
                        System.out.println("[0]氏・名で男性名の入力を開始します。");
                        temp = temp + "\tmale = { \n\t\tsurnames = {";
                        System.out.println("男性の名前を入力してください。");
                        while (true) {
                            num = num + 1;
                            System.out.println("男性の名前(" + num +"人目)を入力してください。");
                            temp = temp + " \"" + br.readLine() + "\" ";
                            System.out.println("男性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                            sw = Integer.parseInt(br.readLine());
                            if (sw == 0){
                                System.out.println("男性名の入力を終了します。男性のコールサインを入力を始めます。");
                                temp = temp +"\n" + "}\n";
                                sw = 1;
                                num = 0;
                                temp = temp + "callsigns = {\n\t";
                                while (true){
                                    num = num + 1;
                                    System.out.println("男性のコールサイン(" + num +"人目)を入力してください。");
                                    temp = temp + " \"" + br.readLine() + "\" ";
                                    System.out.println("男性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
                                    sw = Integer.parseInt(br.readLine());
                                    if (sw == 0){
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
                            System.out.println("女性の名前(" + num +"人目)を入力してください。");
                            temp = temp + " \"" + br.readLine() + "\" ";
                            System.out.println("女性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                            sw = Integer.parseInt(br.readLine());
                            if (sw == 0){
                                System.out.println("女性名の入力を終了します。女性のコールサインを入力を始めます。");
                                temp = temp +"\n" + "}\n";
                                sw = 1;
                                num = 0;
                                temp = temp + "\tcallsigns = {\n\t";
                                while (true){
                                    num = num + 1;
                                    System.out.println("女性のコールサイン(" + num +"人目)を入力してください。");
                                    temp = temp + " \"" + br.readLine() + "\" ";
                                    System.out.println("女性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
                                    sw = Integer.parseInt(br.readLine());
                                    if (sw == 0){
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
                        while (true){
                            num = num + 1;
                            System.out.println("苗字(" + num +"人目)を入力してください。");
                            temp = temp + " \"" + br.readLine() + "\" ";
                            System.out.println("苗字の入力を終了しますか？しない場合[1]する場合は[0]");
                            sw = Integer.parseInt(br.readLine());
                            if (sw == 0){
                                System.out.println("苗字の入力を終わります。");
                                temp = temp + "\n" + "}\n}\n";
                                break;
                            }
                        }

                    }else if (Type == 1){
                        {
                            System.out.println("[1]名・氏で男性名の入力を開始します。");
                            temp = temp + "\tmale = { \n\t\tnames = {";
                            System.out.println("男性の名前を入力してください。");
                            num = 0;
                            while (true) {
                                num = num + 1;
                                System.out.println("男性の名前(" + num +"人目)を入力してください。");
                                temp = temp + " \"" + br.readLine() + "\" ";
                                System.out.println("男性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                                sw = Integer.parseInt(br.readLine());
                                if (sw == 0){
                                    System.out.println("男性名の入力を終了します。男性のコールサインを入力を始めます。");
                                    temp = temp +"\n" + "}\n";
                                    sw = 1;
                                    num = 0;
                                    temp = temp + "callsigns = {\n\t";
                                    while (true){
                                        num = num + 1;
                                        System.out.println("男性のコールサイン(" + num +"人目)を入力してください。");
                                        temp = temp + " \"" + br.readLine() + "\" ";
                                        System.out.println("男性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
                                        sw = Integer.parseInt(br.readLine());
                                        if (sw == 0){
                                            System.out.println("男性のコールサインの入力を終わります。");
                                            temp = temp + "\n" + "}\n}\n";
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            System.out.println("続いて[1]名・氏で女性名の入力を開始します。");
                            temp = temp + "female = { \n\t\tnames = {";
                            num = 0;
                            while (true) {
                                num = num + 1;
                                System.out.println("女性の名前(" + num +"人目)を入力してください。");
                                temp = temp + " \"" + br.readLine() + "\" ";
                                System.out.println("女性の名前の入力を終了しますか？しない場合[1]する場合は[0]");
                                sw = Integer.parseInt(br.readLine());
                                if (sw == 0){
                                    System.out.println("女性名の入力を終了します。女性のコールサインを入力を始めます。");
                                    temp = temp +"\n" + "}\n";
                                    sw = 1;
                                    num = 0;
                                    temp = temp + "callsigns = {\n\t";
                                    while (true){
                                        num = num + 1;
                                        System.out.println("女性のコールサイン(" + num +"人目)を入力してください。");
                                        temp = temp + " \"" + br.readLine() + "\" ";
                                        System.out.println("女性のコールサインの入力を終了しますか？しない場合[1]する場合は[0]");
                                        sw = Integer.parseInt(br.readLine());
                                        if (sw == 0){
                                            System.out.println("女性のコールサインの入力を終わります。");
                                            temp = temp + "\n" + "}\n}\n";
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            System.out.println("続いて[0]氏・名で苗字の入力を開始します。");
                            temp = temp + "\n surnames = {\n";
                            num = 0;
                            while (true){
                                num = num + 1;
                                System.out.println("苗字(" + num +"人目)を入力してください。");
                                temp = temp + " \"" + br.readLine() + "\" ";
                                System.out.println("苗字の入力を終了しますか？しない場合[1]する場合は[0]");
                                sw = Integer.parseInt(br.readLine());
                                if (sw == 0){
                                    System.out.println("苗字の入力を終わります。");
                                    temp = "\n" + "}\n}\n";
                                    break;
                                }
                            }

                        }

                    }else {
                        System.out.println("予期せぬエラーが発生しました。ソフトを終了します。");
                        System.exit(0);
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
                System.out.println(e);
            }
        }//人名
        else if (mode == 4){
            System.out.println("艦名ファイルの作成を開始します。");

            temp = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
            System.out.println(System.getProperty("user.home"));
            SHIP_NAME_TYPES sn = new SHIP_NAME_TYPES();

            try {


                System.out.println("国家タグを入力してください。");
                Ctag = br.readLine();
                file_name = Ctag +"_ship_names.txt";
                System.out.println("file neme is「" + file_name + "」");
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + "/Desktop/" + file_name );
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

                while (true) {
                    System.out.println("船のタイプを下から選び、数字で入力してください。");
                    System.out.println("[0]駆逐艦　[1]軽巡洋艦 [2]重巡洋艦 [3]戦艦 [4]巡洋戦艦 [5]航空母艦 [6]軽空母 [7]大型潜水艦 [8]中型潜水艦 [9]小型潜水艦 [10]都市 [11]山  [12]川 [13]植物 [14]旧国名 [15]島 [16]海峡 [17]鳥 [18]魚");
                    Type = Integer.parseInt(br.readLine());
                    if (Type == 0){
                        sn.DESTROYER();
                    }else if (Type == 1){
                        sn.LIGHT_CRUISER();
                    }else if (Type == 2){
                        sn.HEAVY_CRUISER();
                    }else if (Type == 3){
                        sn.BATTLESHIP();
                    }else if (Type == 4){
                        sn.BATTLE_CRUISER();
                    }else if (Type == 5){
                        sn.AIRCRAFT_CARRIER();
                    }else if (Type == 6){
                       sn.LIGHT_CARRIER();
                    }else if (Type == 7){
                        sn.HEAVY_SUBMAIRNE();
                    }else if (Type == 8){
                        sn.SUBMARINE();
                    }else if (Type == 9){
                        sn.LIGHT_SUBMARINE();
                    }else if (Type == 10){
                        sn.CITIES();
                    }else if (Type == 11){
                        sn.MOUNTAINS();
                    }else if (Type == 12){
                        sn.RIVERS();
                    }else if (Type == 13){
                        sn.PLANTS();
                    }else if (Type == 14) {
                        sn.PREFECTURES();
                    }else if (Type == 15) {
                        sn.ISLANDS();
                    }else if (Type == 16) {
                        sn.STRAIT();
                    }else if (Type == 17) {
                        temp = temp + "###BIRD###\n";
                        temp = temp + Ctag +"_BIRD = {\n";
                        temp = temp + "\tname = NAME_THEME_BIRD\n";
                        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
                        temp = temp + "\ttype = ship\n";
                        temp = temp + "unique = {\n";
                        while (true) {
                            num = num +1;
                            Type = 0;
                            System.out.println("鳥の名前を入力してください。\n");
                            Stype = br.readLine();
                            temp = temp + "\"" + Stype + "\" ";
                            if (num == 10){
                                temp = temp + "\n\t";
                                num = 0;
                            }
                            System.out.println("鳥の名前の入力は全て終わりましたか？\n" );
                            System.out.println("続けて入力する場合は[0]を　終了する場合は[1]を入力してください。");
                            Type = Integer.parseInt(br.readLine());
                            temp = temp + "\t{\n{\n";

                            if (Type == 1){
                                System.out.println("おつかれさまでした。");
                                break;
                            }
                        }
                    }else if (Type == 18) {
                        temp = temp + "###FISH###\n";
                        temp = temp + Ctag +"_FISH = {\n";
                        temp = temp + "\tname = NAME_THEME_FISH\n";
                        temp = temp + "\tfor_countries = { " + Ctag + " }\n\n";
                        temp = temp + "\ttype = ship\n";
                        temp = temp + "unique = {\n";
                        while (true) {
                            num = num +1;
                            Type = 0;
                            System.out.println("魚の名前を入力してください。\n");
                            Stype = br.readLine();
                            temp = temp + "\"" + Stype + "\" ";
                            if (num == 10){
                                temp = temp + "\n\t";
                                num = 0;
                            }
                            System.out.println("魚の名前の入力は全て終わりましたか？\n" );
                            System.out.println("続けて入力する場合は[0]を　終了する場合は[1]を入力してください。");
                            Type = Integer.parseInt(br.readLine());
                            temp = temp + "\t{\n{\n";

                            if (Type == 1){
                                System.out.println("おつかれさまでした。");
                                break;
                            }
                        }
                    }else {
                        System.out.println("予期せぬエラーが発生しました。ソフトを終了します。");
                        System.exit(0);
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
                System.out.println(e);
            }
        }
        else if (mode == 5){//翻訳
            countryname cn = new countryname();
            cn.countrynames();
        }
        else {
            System.out.println("予期せぬエラーが発生しました。ソフトを終了します。");
            System.exit(0);
        }





    }
}

