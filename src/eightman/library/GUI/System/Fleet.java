// Fleet.java
package eightman.library.GUI.System;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private String name;
    private int navalBase;
    private List<TaskForce> taskForces = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNavalBase() {
        return navalBase;
    }

    public void setNavalBase(int navalBase) {
        this.navalBase = navalBase;
    }

    public List<TaskForce> getTaskForces() {
        return taskForces;
    }

    public void addTaskForce(TaskForce taskForce) {
        this.taskForces.add(taskForce);
    }
    @Override
    public String toString() {
        return getName(); // ここで表示したい情報を返す
    }
}