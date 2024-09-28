package eightman.library.GUI.System;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static eightman.library.GUI.Main_GUI.naval_path;

public class NavalParser {

    public static class FS_DBList {
        public String fleetName;
        public String navalBase;
        public String taskForceName;
        public String taskForceLocation;
        public String shipName;
        public String shipDefinition;
        public String shipOwner;
        public String shipVersionName;

        @Override
        public String toString() {
            return "FS_DBList{" +
                    "fleetName='" + fleetName + '\'' +
                    ", navalBase='" + navalBase + '\'' +
                    ", taskForceName='" + taskForceName + '\'' +
                    ", taskForceLocation='" + taskForceLocation + '\'' +
                    ", shipName='" + shipName + '\'' +
                    ", shipDefinition='" + shipDefinition + '\'' +
                    ", shipOwner='" + shipOwner + '\'' +
                    ", shipVersionName='" + shipVersionName + '\'' +
                    '}';
        }
    }

    public List<FS_DBList> parseNavalData() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(naval_path)));
        List<FS_DBList> fsDbList = new ArrayList<>();
        int[] index = {0};

        while (index[0] < content.length()) {
            if (content.startsWith("fleet", index[0])) {
                Fleet fleet = parseFleet(content, index);
                for (TaskForce taskForce : fleet.taskForces) {
                    for (Ship ship : taskForce.ships) {
                        FS_DBList entry = new FS_DBList();
                        entry.fleetName = fleet.name;
                        entry.navalBase = fleet.navalBase;
                        entry.taskForceName = taskForce.name;
                        entry.taskForceLocation = taskForce.location;
                        entry.shipName = ship.name;
                        entry.shipDefinition = ship.definition;
                        entry.shipOwner = ship.owner;
                        entry.shipVersionName = ship.versionName;
                        fsDbList.add(entry);
                    }
                }
            } else {
                index[0]++;
            }
        }

        // Print the first 10 entries to the console
        for (int i = 0; i < Math.min(10, fsDbList.size()); i++) {
            System.out.println(fsDbList.get(i));
        }

        return fsDbList;
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
        index[0] = content.indexOf("}", index[0]) + 1;
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
        index[0] = content.indexOf("}", index[0]) + 1;
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
        index[0] = content.indexOf("}", index[0]) + 1;
        return ship;
    }

    private String extractValue(String content, int[] index) {
        int start = content.indexOf("=", index[0]) + 1;
        while (start < content.length() && (content.charAt(start) == ' ' || content.charAt(start) == '\n' || content.charAt(start) == '\t')) {
            start++;
        }
        int end;
        if (content.charAt(start) == '"') {
            start++;
            end = content.indexOf("\"", start);
        } else {
            end = content.indexOf("\n", start);
            if (end == -1) {
                end = content.length();
            }
        }
        index[0] = end + 1;
        return content.substring(start, end).trim();
    }

}