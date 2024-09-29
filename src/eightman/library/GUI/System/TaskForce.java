package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class TaskForce {
    private List<Ship> ships;
    private String name;
    private int location;

    public TaskForce() {
        this.ships = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}