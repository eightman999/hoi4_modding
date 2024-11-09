package eightman.library.GUI.System;

import java.util.List;

public class Units {
    private static List<Fleet> fleets;

    public Units(List<Fleet> fleets) {
        this.fleets = fleets;
    }

    public static List<Fleet> getFleets() {
        return fleets;
    }

    public void setFleets(List<Fleet> fleets) {
        this.fleets = fleets;
    }

}