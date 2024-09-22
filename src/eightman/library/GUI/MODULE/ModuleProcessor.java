package eightman.library.GUI.MODULE;

import eightman.library.GUI.System.MT_core.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class ModuleProcessor {
    private String modpath;
    private String modName;

    public ModuleProcessor(String modpath, String modName) {
        this.modpath = modpath;
        this.modName = modName;
    }

    public void processModules() {
        File moduleDir = new File(modpath + "/common/units/equipment/modules");
        File[] moduleFiles = moduleDir.listFiles();
        Arrays.sort(moduleFiles);

        Pattern modulePattern = Pattern.compile("(dp_ship_|ship_|SM_|sm_|tank_|Tank)(.*?)=\\{(.*?)\\}", Pattern.DOTALL);
        Pattern statsPattern = Pattern.compile("(multiply_stats|add_stats|add_average_stats)=\\{(.*?)\\}", Pattern.DOTALL);

        for (File file : moduleFiles) {
            String category = determineCategory(file);
            List<String> temp_lage = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("#")) {
                        temp_lage.add(line);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            MT_System.out.println("Processing " + file.getName());
            for (String line : temp_lage) {
                Matcher moduleMatcher = modulePattern.matcher(line);
                while (moduleMatcher.find()) {
                    String moduleID = moduleMatcher.group(1);
                    String moduleBlock = moduleMatcher.group(2);

                    if (category.equals("Ship")) {
                        Ship_Module shipModule = createShipModule(moduleID, moduleBlock);
                    } else if (category.equals("Air")) {
                        Air_Module airModule = createAirModule(moduleID, moduleBlock);
                    } else if (category.equals("Tank")) {
                        Tank_Module tankModule = createTankModule(moduleID, moduleBlock);
                    }

                    Matcher statsMatcher = statsPattern.matcher(moduleBlock);
                    while (statsMatcher.find()) {
                        String statsCategory = statsMatcher.group(1);
                        String statsBlock = statsMatcher.group(2);

                        String csvFilePath = "./Hoi4_modding_Tool/database/" + modName + "_" + category + "modules" + statsCategory + ".csv";
                        File csvFile = new File(csvFilePath);
                        if (!csvFile.exists()) {
                            try {
                                csvFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
                            writer.append(moduleID);
                            writer.append(",");
                            writer.append(category);
                            writer.append(",");
                            writer.append(statsCategory);
                            writer.append(",");
                            writer.append(statsBlock);
                            writer.append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

//    private void writeToCSV(String moduleID, String moduleBlock) {
//        String[] statsCategories = {"multiply_stats", "add_stats", "add_average_stats"};
//        Pattern pattern = Pattern.compile("(multiply_stats|add_stats|add_average_stats)=\\{(.*?)\\}", Pattern.DOTALL);
//
//        Matcher matcher = pattern.matcher(moduleBlock);
//        Map<String, String> statsMap = new HashMap<>();
//        while (matcher.find()) {
//            statsMap.put(matcher.group(1), matcher.group(2));
//        }
//
//        try (FileWriter fw = new FileWriter("output.csv", true);
//             PrintWriter pw = new PrintWriter(fw)) {
//
//            for (String category : statsCategories) {
//                if (statsMap.containsKey(category)) {
//                    String stats = statsMap.get(category);
//                    pw.println(moduleID + "," + category + "," + stats);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private Ship_Module createShipModule(String moduleID, String moduleBlock) {
        Pattern pattern = Pattern.compile("(.*?)=(.*)");
        Matcher matcher = pattern.matcher(moduleBlock);

        Map<String, String> stats = new HashMap<>();
        while (matcher.find()) {
            stats.put(matcher.group(1).trim(), matcher.group(2).trim());
        }

        String content = stats.getOrDefault("content", "N/A");
        String category = stats.getOrDefault("category", "N/A");
        String name = stats.getOrDefault("name", "N/A");
        double lend_lease_cost = Double.parseDouble(stats.getOrDefault("lend_lease_cost", "0.0"));
        double build_cost_ic = Double.parseDouble(stats.getOrDefault("build_cost_ic", "0.0"));
        int manpower = Integer.parseInt(stats.getOrDefault("manpower", "0"));
        boolean can_license = Boolean.parseBoolean(stats.getOrDefault("can_license", "false"));
        boolean is_convertable = Boolean.parseBoolean(stats.getOrDefault("is_convertable", "false"));
        double xp_cost = Double.parseDouble(stats.getOrDefault("xp_cost", "0.0"));

        double naval_speed = Double.parseDouble(stats.getOrDefault("naval_speed", "0.0"));
        double fire_range = Double.parseDouble(stats.getOrDefault("fire_range", "0.0"));
        double lg_armor_piercing = Double.parseDouble(stats.getOrDefault("lg_armor_piercing", "0.0"));
        double lg_attack = Double.parseDouble(stats.getOrDefault("lg_attack", "0.0"));
        double hg_armor_piercing = Double.parseDouble(stats.getOrDefault("hg_armor_piercing", "0.0"));
        double hg_attack = Double.parseDouble(stats.getOrDefault("hg_attack", "0.0"));
        double anti_air_attack = Double.parseDouble(stats.getOrDefault("anti_air_attack", "0.0"));
        double shore_bombardment = Double.parseDouble(stats.getOrDefault("shore_bombardment", "0.0"));
        double evasion = Double.parseDouble(stats.getOrDefault("evasion", "0.0"));
        double surface_detection = Double.parseDouble(stats.getOrDefault("surface_detection", "0.0"));
        double sub_attack = Double.parseDouble(stats.getOrDefault("sub_attack", "0.0"));
        double sub_detection = Double.parseDouble(stats.getOrDefault("sub_detection", "0.0"));
        double surface_visibility = Double.parseDouble(stats.getOrDefault("surface_visibility", "0.0"));
        double sub_visibility = Double.parseDouble(stats.getOrDefault("sub_visibility", "0.0"));
        double naval_range = Double.parseDouble(stats.getOrDefault("naval_range", "0.0"));
        double port_capacity_usage = Double.parseDouble(stats.getOrDefault("port_capacity_usage", "0.0"));
        double search_and_destroy_coordination = Double.parseDouble(stats.getOrDefault("search_and_destroy_coordination", "0.0"));
        double convoy_raiding_coordination = Double.parseDouble(stats.getOrDefault("convoy_raiding_coordination", "0.0"));
        String[] critical_parts = stats.getOrDefault("critical_parts", "N/A").split(" ");
        String[] can_convert_from_module_categorie = stats.getOrDefault("can_convert_from_module_categorie", "N/A").split(" ");
        double[] convert_cost_ic = Arrays.stream(stats.getOrDefault("convert_cost_ic", "0.0").split(" ")).mapToDouble(Double::parseDouble).toArray();

        return new Ship_Module(moduleID,name, content, category, lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable, xp_cost,
                naval_speed, fire_range, lg_armor_piercing, lg_attack, hg_armor_piercing, hg_attack, anti_air_attack, shore_bombardment, evasion, surface_detection, sub_attack, sub_detection,
                surface_visibility, sub_visibility, naval_range, port_capacity_usage, search_and_destroy_coordination, convoy_raiding_coordination, critical_parts, can_convert_from_module_categorie, convert_cost_ic);
    }

    private Air_Module createAirModule(String moduleID, String moduleBlock) {
        Pattern pattern = Pattern.compile("(.*?)=(.*)");
        Matcher matcher = pattern.matcher(moduleBlock);

        Map<String, String> stats = new HashMap<>();
        while (matcher.find()) {
            stats.put(matcher.group(1).trim(), matcher.group(2).trim());
        }

        String content = stats.getOrDefault("content", "N/A");
        String name = stats.getOrDefault("name", "N/A");
        String category = stats.getOrDefault("category", "N/A");
        double lend_lease_cost = Double.parseDouble(stats.getOrDefault("lend_lease_cost", "0.0"));
        double build_cost_ic = Double.parseDouble(stats.getOrDefault("build_cost_ic", "0.0"));
        int manpower = Integer.parseInt(stats.getOrDefault("manpower", "0"));
        boolean can_license = Boolean.parseBoolean(stats.getOrDefault("can_license", "false"));
        boolean is_convertable = Boolean.parseBoolean(stats.getOrDefault("is_convertable", "false"));
        double xp_cost = Double.parseDouble(stats.getOrDefault("xp_cost", "0.0"));

        double air_attack = Double.parseDouble(stats.getOrDefault("air_attack", "0.0"));
        double air_defence = Double.parseDouble(stats.getOrDefault("air_defence", "0.0"));
        double air_range = Double.parseDouble(stats.getOrDefault("air_range", "0.0"));
        double air_agility = Double.parseDouble(stats.getOrDefault("air_agility", "0.0"));
        double air_ground_attack = Double.parseDouble(stats.getOrDefault("air_ground_attack", "0.0"));
        double air_bombing = Double.parseDouble(stats.getOrDefault("air_bombing", "0.0"));
        double air_superiority = Double.parseDouble(stats.getOrDefault("air_superiority", "0.0"));
        double naval_strike_attack = Double.parseDouble(stats.getOrDefault("naval_strike_attack", "0.0"));
        double naval_strike_targetting = Double.parseDouble(stats.getOrDefault("naval_strike_targetting", "0.0"));
        double carrier_size = Double.parseDouble(stats.getOrDefault("carrier_size", "0.0"));
        double default_carrier_composition_weight = Double.parseDouble(stats.getOrDefault("default_carrier_composition_weight", "0.0"));
        boolean carrier_capable = Boolean.parseBoolean(stats.getOrDefault("carrier_capable", "false"));
        String[] can_convert_from_module_categorie = stats.getOrDefault("can_convert_from_module_categorie", "N/A").split(" ");
        double[] convert_cost_ic = Arrays.stream(stats.getOrDefault("convert_cost_ic", "0.0").split(" ")).mapToDouble(Double::parseDouble).toArray();

        return new Air_Module(moduleID,name, content, category, lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable, xp_cost,
                air_attack, air_defence, air_range, air_agility, air_ground_attack, air_bombing, air_superiority, naval_strike_attack, naval_strike_targetting, carrier_size,
                default_carrier_composition_weight, carrier_capable, can_convert_from_module_categorie, convert_cost_ic);
    }

    private Tank_Module createTankModule(String moduleID, String moduleBlock) {
        Pattern pattern = Pattern.compile("(.*?)=(.*)");
        Matcher matcher = pattern.matcher(moduleBlock);

        Map<String, String> stats = new HashMap<>();
        while (matcher.find()) {
            stats.put(matcher.group(1).trim(), matcher.group(2).trim());
        }

        String content = stats.getOrDefault("content", "N/A");
        String category = stats.getOrDefault("category", "N/A");
        String name = stats.getOrDefault("name", "N/A");
        double lend_lease_cost = Double.parseDouble(stats.getOrDefault("lend_lease_cost", "0.0"));
        double build_cost_ic = Double.parseDouble(stats.getOrDefault("build_cost_ic", "0.0"));
        int manpower = Integer.parseInt(stats.getOrDefault("manpower", "0"));
        boolean can_license = Boolean.parseBoolean(stats.getOrDefault("can_license", "false"));
        boolean is_convertable = Boolean.parseBoolean(stats.getOrDefault("is_convertable", "false"));
        double xp_cost = Double.parseDouble(stats.getOrDefault("xp_cost", "0.0"));

        double reliability = Double.parseDouble(stats.getOrDefault("reliability", "0.0"));
        double maximum_speed = Double.parseDouble(stats.getOrDefault("maximum_speed", "0.0"));
        double soft_attack = Double.parseDouble(stats.getOrDefault("soft_attack", "0.0"));
        double hard_attack = Double.parseDouble(stats.getOrDefault("hard_attack", "0.0"));
        double air_attack = Double.parseDouble(stats.getOrDefault("air_attack", "0.0"));
        double ap_attack = Double.parseDouble(stats.getOrDefault("ap_attack", "0.0"));
        double breakthrough = Double.parseDouble(stats.getOrDefault("breakthrough", "0.0"));
        double defense = Double.parseDouble(stats.getOrDefault("defense", "0.0"));
        double max_strength = Double.parseDouble(stats.getOrDefault("max_strength", "0.0"));
        double armor_value = Double.parseDouble(stats.getOrDefault("armor_value", "0.0"));
        double hardness = Double.parseDouble(stats.getOrDefault("hardness", "0.0"));
        double entrenchment = Double.parseDouble(stats.getOrDefault("entrenchment", "0.0"));
        double recon = Double.parseDouble(stats.getOrDefault("recon", "0.0"));
        String forbid_equipment_type = stats.getOrDefault("forbid_equipment_type", "N/A");
        String[] can_convert_from_module_categorie = stats.getOrDefault("can_convert_from_module_categorie", "N/A").split(" ");
        double[] convert_cost_ic = Arrays.stream(stats.getOrDefault("convert_cost_ic", "0.0").split(" ")).mapToDouble(Double::parseDouble).toArray();

        return new Tank_Module(moduleID,name, content, category, lend_lease_cost, build_cost_ic, manpower, can_license, is_convertable, xp_cost,
                reliability, maximum_speed, soft_attack, hard_attack, air_attack, ap_attack, breakthrough, defense, max_strength, armor_value, hardness, entrenchment, recon, forbid_equipment_type, can_convert_from_module_categorie, convert_cost_ic);
    }

    private String determineCategory(File file) {
        String fileName = file.getName();
        if (fileName.startsWith("00_plane") || fileName.startsWith("00_P")) {
            return "Air";
        } else if (fileName.startsWith("00_ship") || fileName.startsWith("00_S")) {
            return "Ship";
        } else if (fileName.startsWith("00_tank") || fileName.startsWith("00_T")) {
            return "Tank";
        } else {
            return "Unknown";
        }
    }
}
