package sunlight.library.inc;

import sunlight.library.inc.System.Error;
import sunlight.library.inc.equipment.ship_equipment_modules;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static sunlight.library.inc.Main.*;

public class equipmenttool {
    public void equipment_modues(){
        int mode = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("\nモードを以下から選び、番号でキーボードから入力してください");
        System.out.println("\n[1]Air-equipments  [2]Ship-modules");
        MSTR = "#This file was created using software created by \n#Sunlight.library and the STRaDA (Sunlight Technology Research and Development Association).\n";
        if(L_mode == 1) {
            LSTR = "l_english:\n";
        }else if (L_mode == 2){
            LSTR = "l_japanese:\n";
        }
        try {
            mode = Integer.parseInt(br.readLine());
            if (mode == 1||mode == 2){

            }else {
                Error.ERROR();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mode == 1){

        }
        else if (mode == 2){
            ship_equipment_modules.SEM();
        }else {
            Error.ERROR();
        }

    }

}
