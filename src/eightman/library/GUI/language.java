package eightman.library.GUI;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class language {

    public static String DONE = "Done";
    public static String LOCALIZE = "Localize";
    public static String EQUIPMENT = "Equipment";
    public static String COUNTRY = "country";
    public static String NONE = "None";
    public static String MODE_SELECT = "Select Mode";
    public static String LG_SELECT = "Select language";
    public static String ENGLISH = "English";
    public static String JAPANESE = "Japanese";
    public static String Please_Select_mode = "Please Select mode";
    public static String GFX = "GFX";
    public static String GOAL = "Goals";
    public static String SHL = "Ship Hull";
    public static String Title = "Hoi4 modding tool";
    public static String File = "File";
    public static String Save = "Save";
    public static String combined = "combined";
    public static String str_loc = "Starts the creation of a translation file.";
    public static String LR_no = "";
    public static String CCC =
            "It is described by eightman.library and software created by eightman";
    public static String IMAGE = "Image File";

    public static void C_languages() {
        String languageFile = Main_GUI.L_mode == 0 ? "jp_ja.json" : "en_en.json";
        JSONObject langJson = new JSONObject(readFile(languageFile));
        DONE = langJson.getString("DONE");
        LOCALIZE = langJson.getString("LOCALIZE");
        EQUIPMENT = langJson.getString("EQUIPMENT");
        COUNTRY = langJson.getString("country");
        NONE = langJson.getString("None");
        MODE_SELECT = langJson.getString("Select Mode");
        LG_SELECT = langJson.getString("Select language");
        ENGLISH = langJson.getString("English");
        JAPANESE = langJson.getString("Japanese");
        Please_Select_mode = langJson.getString("Please Select mode");
        GFX = langJson.getString("GFX");
        GOAL = langJson.getString("Goals");
        SHL = langJson.getString("Ship Hull");
        Title = langJson.getString("Hoi4 modding tool");
        File = langJson.getString("File");
        Save = langJson.getString("Save");
        combined = langJson.getString("combined");
        str_loc = langJson.getString("Starts the creation of a translation file.");
        LR_no = langJson.getString("The number of lines is different on the left and right.");
        IMAGE = langJson.getString("Image File");
        private static String readFile (String filename){
            String content = "";
            try {
                content = new String(Files.readAllBytes(Paths.get(filename)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }
    }
}
