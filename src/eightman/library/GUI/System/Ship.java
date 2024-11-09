// Ship.java
package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private static String name;
    private static String definition;
    private static double startExperienceFactor;
    private static boolean prideOfTheFleet;
    private List<Equipment> equipmentList = new ArrayList<>();
    private List<Ship> children = new ArrayList<>();

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public static double getStartExperienceFactor() {
        return startExperienceFactor;
    }

    public void setStartExperienceFactor(double startExperienceFactor) {
        this.startExperienceFactor = startExperienceFactor;
    }

    public static boolean isPrideOfTheFleet() {
        return prideOfTheFleet;
    }

    public void setPrideOfTheFleet(boolean prideOfTheFleet) {
        this.prideOfTheFleet = prideOfTheFleet;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void addEquipment(Equipment equipment) {
        this.equipmentList.add(equipment);
    }

    public List<Ship> getChildren() {
        return children;
    }

    public void addChild(Ship child) {
        this.children.add(child);
    }

    public String getShipHull() {
        return equipmentList.isEmpty() ? null : equipmentList.get(0).getShipHull();
    }
    @Override
    public String toString() {
        return getName(); // ここで表示したい情報を返す
    }
}