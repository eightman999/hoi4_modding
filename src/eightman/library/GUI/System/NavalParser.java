package eightman.library.GUI.System;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static eightman.library.GUI.Main_GUI.naval_path;

public class NavalParser {

    public List<Fleet> parseNavalData() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(naval_path)));
        List<Fleet> fleets = new ArrayList<>();
        int[] index = {0};

        while (index[0] < content.length()) {
            if (content.startsWith("fleet", index[0])) {
                fleets.add(parseFleet(content, index));
            } else {
                index[0]++;
            }
        }

        // ツリー構造をコンソールに表示
        for (Fleet fleet : fleets) {
            printFleet(fleet, 0);
        }

        return fleets;
    }

    private Fleet parseFleet(String content, int[] index) {
        Fleet fleet = new Fleet();
        fleet.taskForces = new ArrayList<>();
        index[0] = content.indexOf("{", index[0]) + 1;

        while (index[0] < content.length() && content.charAt(index[0]) != '}') {
            if (content.startsWith("name", index[0])) {
                fleet.name = extractValue(content, index);
            } else if (content.startsWith("naval_base", index[0])) {
                fleet.navalBase = extractValue(content, index);
            } else if (content.startsWith("task_force", index[0])) {
                fleet.taskForces.add(parseTaskForce(content, index));
            } else {
                index[0]++;
            }
        }
        index[0]++;
        return fleet;
    }

    private TaskForce parseTaskForce(String content, int[] index) {
        TaskForce taskForce = new TaskForce();
        taskForce.ships = new ArrayList<>();
        index[0] = content.indexOf("{", index[0]) + 1;

        while (index[0] < content.length() && content.charAt(index[0]) != '}') {
            if (content.startsWith("name", index[0])) {
                taskForce.name = extractValue(content, index);
            } else if (content.startsWith("location", index[0])) {
                taskForce.location = extractValue(content, index);
            } else if (content.startsWith("ship", index[0])) {
                taskForce.ships.add(parseShip(content, index));
            } else {
                index[0]++;
            }
        }
        index[0]++;
        return taskForce;
    }

    private Ship parseShip(String content, int[] index) {
        Ship ship = new Ship();
        index[0] = content.indexOf("{", index[0]) + 1;

        while (index[0] < content.length() && content.charAt(index[0]) != '}') {
            if (content.startsWith("name", index[0])) {
                ship.name = extractValue(content, index);
            } else if (content.startsWith("definition", index[0])) {
                ship.definition = extractValue(content, index);
            } else if (content.startsWith("owner", index[0])) {
                ship.owner = extractValue(content, index);
            } else if (content.startsWith("version_name", index[0])) {
                ship.versionName = extractValue(content, index);
            } else {
                index[0]++;
            }
        }
        index[0]++;
        return ship;
    }

    private String extractValue(String content, int[] index) {
        int start = content.indexOf("=", index[0]) + 1;
        int end = content.indexOf("\n", start);
        index[0] = end;
        return content.substring(start, end).trim().replace("\"", "");
    }

    private void printFleet(Fleet fleet, int indent) {
        printIndented("Fleet: " + fleet.name + "(" + fleet.navalBase + ")", indent);
        for (TaskForce taskForce : fleet.taskForces) {
            printTaskForce(taskForce, indent + 2);
        }
    }

    private void printTaskForce(TaskForce taskForce, int indent) {
        printIndented("Task Force: " + taskForce.name + "(" + taskForce.location + ")", indent);
        for (Ship ship : taskForce.ships) {
            printShip(ship, indent + 2);
        }
    }

    private void printShip(Ship ship, int indent) {
        printIndented("Ship: " + ship.name, indent);
        printIndented("Definition: " + ship.definition, indent + 2);
        printIndented("Version: " + ship.versionName, indent + 2);
    }

    private void printIndented(String text, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }
}