package sunlight.library.inc;

import sunlight.library.inc.System.Error;
import sunlight.library.inc.ship_name_type.SHIP_NAME_TYPES;

import java.io.*;

import static sunlight.library.inc.Main.*;
public class shipname {
    public void shipname(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
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
                    sn.BIRD();
                }else if (Type == 18) {
                    sn.FISH();
                }else {
                    Error.ERROR();
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
}
