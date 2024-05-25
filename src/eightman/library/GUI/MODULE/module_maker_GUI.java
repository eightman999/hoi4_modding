package eightman.library.GUI.MODULE;

import eightman.library.GUI.System.MT_core.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static eightman.library.GUI.Main_GUI.loading;
import static eightman.library.GUI.Main_GUI.modPathMap;
import static eightman.library.GUI.language.*;

public class module_maker_GUI extends JFrame {
    private JComboBox<String> modNameDropdown;
    private JLabel modPathLabel;
    private JLabel loadingLabel;
    private String modpath;
    private String modName = "empty";
    private JScrollPane listScrollPane;
    private JTextField csvFilePathField;
    private JButton loadCsvButton;
    private HashSet<String> existingIds = new HashSet<>();
    private HashMap<String, Integer> idCounts = new HashMap<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> moduleList = new JList<>(listModel);
    private String EQMs = "equipment_modules = {\n";

    public void module_maker_GUI() {
//        this.mainGui = mainGui;
        setTitle(MODULE + " " +MAKER);
        setupFrame();
        setupgui();
        setupAnimationLabel();
        setVisible(true);
    }

    private void setupFrame() {
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupgui() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        modNameDropdown = new JComboBox<>(modPathMap.keySet().toArray(new String[0]));
        modNameDropdown.addActionListener(e -> updateModPathLabel());

        modPathLabel = new JLabel();
        modPathLabel.setPreferredSize(new Dimension(500, 20));

        JButton loadButton = new JButton(LOAD);
        loadButton.addActionListener(e -> new Thread(this::loading_animation).start());

        JPanel topPanel = new JPanel();
        topPanel.add(modNameDropdown);
        topPanel.add(modPathLabel);
        topPanel.add(loadButton);

        add(topPanel);

        add(Box.createVerticalStrut(5)); // Add 5px margin

        csvFilePathField = new JTextField();
        csvFilePathField.setPreferredSize(new Dimension(500, 20));
        loadCsvButton = new JButton("CSV" + LOAD);
        loadCsvButton.addActionListener(e -> loadCsvFile());

        JPanel csvPanel = new JPanel();
        csvPanel.add(csvFilePathField);
        csvPanel.add(loadCsvButton);

        add(csvPanel);

        add(Box.createVerticalStrut(5)); // Add 5px margin

        moduleList = new JList<>();
        moduleList.setPreferredSize(new Dimension(700, 600));
        listScrollPane = new JScrollPane(moduleList);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(listScrollPane);
    }

    private void loadCsvFile() {
        String csvFilePath = csvFilePathField.getText();
        File csvFile = new File(csvFilePath);
        String fileName = csvFile.getName();

        if (!fileName.startsWith("SM_")) {
            JOptionPane.showMessageDialog(this, Warn_M, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] validTypes = {"heavy", "medium", "light", "secondary", "anti_air", "anti_sub", "mine", "elect", "torpedo", "aircraft", "rocket", "special", "role", "hull", "material"};
        String fileType = fileName.substring(3, fileName.indexOf('.'));

        if (!Arrays.asList(validTypes).contains(fileType)) {
            JOptionPane.showMessageDialog(this, Warn_M2, "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EquipmentModulesWriter(fileType);
        ymlwriter(fileType);

        MT_System.out.println("Loaded CSV file: " + csvFilePath);
    }

    private void EquipmentModulesWriter(String fileType){
        String csvFilePath = csvFilePathField.getText();
        String directoryPath = "Hoi4_modding_Tool/modules/";
        switch (fileType){
            case "heavy": ;
                String outputFileName = "00_S_hev_battery.txt";
                File directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File outputFile = new File(directoryPath + outputFileName);

                try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                    writer.write(EQMs);

                    String line = br.readLine(); // Skip the header line
                    while ((line = br.readLine()) != null) {
                        Ship_Module module = createHeavyShipModuleFromCsvLine(line);
                        if (module != null) {
                            writeSHGToFile(writer, module);
                        }
                    }

                    writer.write("}\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeBracketsFromFile(outputFile.getPath());
                break;
            case "medium":
                 outputFileName = "00_S_med_battery.txt";
                 directory = new File(directoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                outputFile = new File(directoryPath + outputFileName);

                try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                    writer.write(EQMs);

                    String line = br.readLine(); // Skip the header line
                    while ((line = br.readLine()) != null) {
                        Ship_Module module = createMediumShipModuleFromCsvLine(line);
                        if (module != null) {
                            writeSHGToFile(writer, module);
                        }
                    }

                    writer.write("}\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeBracketsFromFile(outputFile.getPath());
                break;

            default:
                break;
        }
    }

    private void ymlwriter(String fileType){
        String csvFilePath = csvFilePathField.getText();
        String ymlDirectoryPath = "Hoi4_modding_Tool/localization/japanese/";
        String ymlFileName = modName + "_S_hev_battery_l_japanese.yml";
        File ymlDirectory = new File(ymlDirectoryPath);
        if (!ymlDirectory.exists()) {
            ymlDirectory.mkdirs();
        }
        File ymlFile = new File(ymlDirectoryPath + ymlFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            BufferedWriter ymlWriter = new BufferedWriter(new FileWriter(ymlFile))) {
            ymlWriter.write("l_japanese:\n");
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                Ship_Module module = createHeavyShipModuleFromCsvLine(line);
                if (module != null) {
                    switch (fileType){
                        case "heavy":
                            String escapedModuleName = module.getName().replace("\"", "\\\"");
                            String tmp = "Hoi4_modding_Tool/database/" + modName + "_SHG.csv";
                            CsvFileCreator.writeModuleToFile(tmp);
                            ymlWriter.write("  " + module.getId() + ":0 \"" + escapedModuleName + "\"\n");
                            break;
                        case "medium":
                            escapedModuleName = module.getName().replace("\"", "\\\"");
                            tmp = "Hoi4_modding_Tool/database/" + modName + "_SMG.csv";
                            CsvFileCreator.writeModuleToFile(tmp);
                            ymlWriter.write("  " + module.getId() + ":0 \"" + escapedModuleName + "\"\n");
                            break;
                        default:
                            break;
                    }// Write to the YML file
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private Ship_Module createHeavyShipModuleFromCsvLine(String line) {
        String[] values = line.split(",");
        String name = values[0];
        String category = values[1];
        String originalId = values[2];
        String id = originalId;

        if (existingIds.contains(originalId)) {
            int count = idCounts.getOrDefault(originalId, 0);
            id = originalId + "_" + count;
            idCounts.put(originalId, count + 1);
        }

        existingIds.add(id);
        double build_cost_ic = Double.parseDouble(values[3]);
        double convert_cost_ic = Double.parseDouble(values[4]);
        double hg_attack = Double.parseDouble(values[5]);
        MT_System.out.println("hg_attack: " + hg_attack);
        double hg_armor_piercing = Double.parseDouble(values[6]);
        double naval_speed = Double.parseDouble(values[7]);
        int manpower = Integer.parseInt(values[8]);
        double xp_cost = Math.log(build_cost_ic) / Math.log(2);
        String[] can_convert_from_module_categorie = {category};
        String[] critical_parts = {"damaged_heavy_guns"};
        boolean can_license = true;
        boolean is_convertable = true;
        return new Ship_Module(id, name, "", category, 0.0, build_cost_ic, manpower, can_license, is_convertable, xp_cost,
                naval_speed, 0.0,
                0.0, 0.0, hg_armor_piercing,
                hg_attack, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0,
                0.0, 0.0, 0.0,
                critical_parts, can_convert_from_module_categorie, new double[]{convert_cost_ic});
    }

    private Ship_Module createMediumShipModuleFromCsvLine(String line){
        String[] data = line.split(",");
        String originalId = data[2];
        String id = originalId;

        if (existingIds.contains(originalId)) {
            int count = idCounts.getOrDefault(originalId, 0);
            id = originalId + "_" + count;
            idCounts.put(originalId, count + 1);
        }

        String name = data[0];
        String category = data[1];
        double build_cost_ic = Double.parseDouble(data[3]);
        double convert_cost_ic = Double.parseDouble(data[4]);
        double lg_attack = Double.parseDouble(data[5]);
        double lg_armor_piercing = Double.parseDouble(data[6]);
        double anti_air_attack = Double.parseDouble(data[7]);
        double hg_attack = Double.parseDouble(data[8]);
        double hg_armor_piercing = Double.parseDouble(data[9]);
        int manpower = Integer.parseInt(data[10]);
        double naval_speed = Double.parseDouble(data[11]);
        double xp_cost = Math.log(build_cost_ic) / Math.log(2); // log2(build_cost_ic)を計算
        String[] can_convert_from_module_categorie = {category}; // この例ではカテゴリーをそのまま使用
        String[] critical_parts = {"damaged_medium_guns"}; // 仮の値

        // Ship_Moduleのインスタンスを生成して返す
        return new Ship_Module(id, name, "", category, 0.0, build_cost_ic, manpower, true, true, xp_cost,
                naval_speed, 0.0, lg_armor_piercing, lg_attack, hg_armor_piercing, hg_attack,
                anti_air_attack, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0,
                0.0,0.0,critical_parts
            , can_convert_from_module_categorie, new double[]{convert_cost_ic});
    }

    private void writeSHGToFile(BufferedWriter writer, Ship_Module module) throws IOException {

        writer.write("\t" + module.getId() + " = {#" + module.getName() +  "\n");
        writer.write("\t\tcategory = " + module.getCategory() + "\n");
        writer.write("\t\tsfx = sfx_ui_sd_module_turret\n");
        writer.write("\t\tadd_equipment_type = capital_ship\n");
        writer.write("\t\tadd_stats = {\n");
        writer.write("\t\t\thg_attack = " + module.getHgAttack() + "\n");
        writer.write("\t\t\tbuild_cost_ic = " + module.getBuildCostIc() + "\n");
        writer.write("\t\t}\n");
        writer.write("\t\tmultiply_stats = {\n");
        writer.write("\t\t\tnaval_speed = " + module.getNavalSpeed() + "\n");
        writer.write("\t\t}\n");
        writer.write("\t\tmanpower = "+ module.getManpower() + "\n");
        writer.write("\t\tadd_average_stats = {\n");
        writer.write("\t\t\thg_armor_piercing = " + module.getHgArmorPiercing() + "\n");
        writer.write("\t\t}\n");
        writer.write("\t\tbuild_cost_resources = {\n");
        writer.write("\t\t\tsteel = " + (int)Math.floor((Math.log(module.getBuildCostIc()) / Math.log(2)) / 2) + "\n");
        writer.write("\t\t}\n");
        writer.write("\t\tcan_convert_from = {\n");
        writer.write("\t\t\tmodule_category = " + Arrays.toString(module.getCanConvertFromModuleCategorie()) + "\n");
        writer.write("\t\t\tconvert_cost_ic = " + Arrays.toString(module.getConvertCostIc()) + "\n");
        writer.write("\t\t}\n");
        writer.write("\t\tcritical_parts = {\n");
        writer.write("\t\t\t" + Arrays.toString(module.getCriticalParts()) + "\n");
        writer.write("\t\t}\n");
        writer.write("\t}\n");
        MT_System.out.println("Loaded " + module.getId() + ", " + module.getName() + ", " + "" + ", " + module.getCategory() + ", " + 0.0 + ", " + module.getBuildCostIc() + ", " + module.getManpower() + ", " + module.isCanLicense() + ", " + module.isIsConvertable() + ", " + module.getXpCost() +
                ", " + module.getNavalSpeed() + ", " + 0.0 + ", " + module.getHgArmorPiercing()  + ", " + module.getHgAttack() + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 +
                ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + 0.0 + ", " + Arrays.toString(module.getCriticalParts()) + ", " + Arrays.toString(module.getCanConvertFromModuleCategorie()) + ", ");
        listModel.addElement("ID: " + module.getId() + ", Name: " + module.getName() + ", Category: " + module.getCategory() + ", Build Cost: " + module.getBuildCostIc() + ", Manpower: " + module.getManpower());
        copyFileToDatabase(modName, csvFilePathField.getText());
        moduleList.setModel(listModel);
    }

    private void copyFileToDatabase(String modName, String sourceFilePath) throws IOException {
        Path sourcePath = Path.of(sourceFilePath);
        String destinationFileName = modName + "_SHG.csv";
        Path destinationPath = Path.of("Hoi4_modding_Tool/database", destinationFileName);

        // 元のファイルを新しい場所にコピーする
        Files.copy(sourcePath, destinationPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    private void removeBracketsFromFile(String filePath) {
        String outputFilePath = filePath + "_temp";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("[", "").replace("]", "");
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rename the new file to the original file
        new File(filePath).delete();
        new File(outputFilePath).renameTo(new File(filePath));
    }

    private void updateModPathLabel() {
        String selectedModName = (String) modNameDropdown.getSelectedItem();
        modName = selectedModName;
        String modPath = modPathMap.get(selectedModName);
        modpath = modPath;
        modPathLabel.setText(modPath);
    }

    private void setupAnimationLabel() {
        ImageIcon loadingIcon = new ImageIcon(loading); // loading is a BufferedImage
        loadingLabel = new JLabel(loadingIcon);
        loadingLabel.setVisible(false);
        JPanel animationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        animationPanel.add(loadingLabel);
        add(animationPanel, BorderLayout.PAGE_END);
    }

    private void loading_animation() {
        // アニメーションを表示
        loadingLabel.setVisible(true);



        // ロード処理が完了したらアニメーションを非表示にする
        loadingLabel.setVisible(false);
    }
    
}