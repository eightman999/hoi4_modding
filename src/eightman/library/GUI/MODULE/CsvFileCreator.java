package eightman.library.GUI.MODULE;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CsvFileCreator {

    public static void writeModuleToFile(String csvFilePath) {
        List<Ship_Module> shipModules = getShipModules(); // Replace with your method to get the list of Ship_Module objects

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().setHeader("idcategorystats").build())) {
            for (Ship_Module module : shipModules) {
                csvPrinter.printRecord(module.getId() , module.getName()  , module.getCategory() ,  module.getBuildCostIc() , module.getManpower() , module.isCanLicense() , module.isIsConvertable() , module.getXpCost() ,module.getNavalSpeed() ,  module.getHgArmorPiercing()  , module.getHgAttack() ,        0.0 +
                        Arrays.toString(module.getCriticalParts()) , Arrays.toString(module.getCanConvertFromModuleCategorie()));
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Ship_Module> getShipModules() {
        // Implement your method to get the list of Ship_Module objects
        return null;
    }
}