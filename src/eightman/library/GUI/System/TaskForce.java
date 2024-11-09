package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class TaskForce {
    private static String name;
    private static int location;
    private List<Ship> ships = new ArrayList<>();

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
    @Override
    public String toString() {
        return getName(); // ここで表示したい情報を返す
    }
}