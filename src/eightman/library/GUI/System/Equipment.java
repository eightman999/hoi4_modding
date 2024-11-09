// Equipment.java
package eightman.library.GUI.System;

public class Equipment {
    private String owner;
    private String versionName;
    private int amount;
    private String ship_Hull; // Add ship_Hull field

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getShipHull() {
        return ship_Hull;
    }

    public void setShipHull(String ship_Hull) {
        this.ship_Hull = ship_Hull;
    }
}