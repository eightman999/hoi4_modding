package eightman.library.inc.CUI.System;

import eightman.library.inc.CUI.*;

public class ROOT {

  public void localize() {
    localize.localization();
  }

  public void equipmenttool() {
    equipmenttool eq = new equipmenttool();
    eq.equipment_modues();
  }

  public void peoplename() {
    peoplename pn = new peoplename();
    pn.peoplenames();
  }

  public void ship_name() {
    ship_name sn = new ship_name();
    sn.ship_names();
  }

  public void country_name() {
    country_name.country_names();
  }

  public void start() {
    start st = new start();
    st.START();
  }
}
