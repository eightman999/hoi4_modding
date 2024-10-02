package eightman.library.GUI.System;

public class Production_ship {
    private String ship_Hull;
    private int amount;
    private String creator;
    private String versionName;

    public Production_ship() {
        super();
    }

    public String getShip_Hull() {
        return ship_Hull;
    }

    public void setShip_Hull(String ship_Hull) {
        this.ship_Hull = ship_Hull;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return creator;
    }

    public void setOwner(String creator) {
        this.creator = creator;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
