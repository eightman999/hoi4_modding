// Ship.java
package eightman.library.GUI.System;

public class Ship {
    private String name;
    private String definition;
    private double startExperienceFactor;
    private Equipment equipment;
    private String ship_Hull;


    public Ship() {
        this.equipment = new Equipment();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public double getStartExperienceFactor() {
        return startExperienceFactor;
    }

    public void setStartExperienceFactor(double startExperienceFactor) {
        this.startExperienceFactor = startExperienceFactor;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(String ship_Hull) {
        this.ship_Hull = ship_Hull;
    }

    public void setAmount(int amount) {
        this.equipment.setAmount(amount);
    }

    public void setOwner(String owner) {
        this.equipment.setOwner(owner);
    }

    public void setVersionName(String versionName) {
        this.equipment.setVersionName(versionName);
    }
}