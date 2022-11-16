package sunlight.library.inc.CUI.System;

import sunlight.library.inc.CUI.*;

public class ROOT {

  public void localize() {
    localize lc = new localize();
    lc.localization();
  }

  public void equipmenttool() {
    equipmenttool eq = new equipmenttool();
    eq.equipment_modues();
  }

  public void peoplename() {
    peoplename pn = new peoplename();
    pn.peoplename();
  }

  public void ship_name() {
    ship_name sn = new ship_name();
    sn.ship_names();
  }

  public void country_name() {
    country_name cn = new country_name();
    cn.country_names();
  }

  public void start() {
    start st = new start();
    st.START();
  }
}
