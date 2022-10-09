package sunlight.library.inc.System;

import sunlight.library.inc.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ROOT {
    public void localize(){
        localize lc = new localize();
        lc.localization();
    }
    public void equipmenttool(){
        equipmenttool eq = new equipmenttool();
        eq.equipment_modues();
    }
    public void peoplename(){
        peoplename pn = new peoplename();
        pn.peoplename();
    }
    public void shipname(){
        shipname sn = new shipname();
        sn.shipname();
    }
    public void countryname(){
        countryname cn = new countryname();
        cn.countrynames();
    }
    public void start(){
        start st = new start();
        st.START();
    }
}
