package eightman.library.GUI.System;

public class Equipment {
    private String owner;
    private String versionName;
    private int amount;
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

    public void setAmount(int tempEAmount) {
        this.amount = tempEAmount;
    }
}