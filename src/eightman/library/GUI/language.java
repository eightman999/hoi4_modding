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
    public static String CCC = "It is described by eightman and software created by eightman";
    public static String IMAGE = "Image File";
    public static String PREF = "Preferences";
    public static String Loc_Pref = "Localization Preferences";
    public static String SAVE_SETTINGS = "設定保存";
    public static String FONT = "Font";
    public static String MODULE = "Module";
    public static String NAME = "Name";
    public static String ABOUT = "About";
    public static String TAG = "Tag";
    public static String DESC = "Description";
    public static String COLOR = "Color";
    public static String ICON = "Icon";
    public static String LOAD= "Load";
    public static String MAKER = "Maker";
    public static String Warn_M = "Warning: This will overwrite the current file.";
    public static String Warn_M2 = "Warning: This will overwrite the current file.";
    public static String SDI = "Ship Design Interface";
    public static String FIN = "";
    public static String CTR = "";
    public static String TEC = "";
    public static String LNG = "";
    public static String VIW = "";
    public static String Survey = "";
    public static String Quit = "";
    public static String CVRT = "";

    public static void C_languages() {
        String languageFile;
        if (Main_GUI.L_mode == 0) {
            languageFile = "jp_ja.json";
        } else if (Main_GUI.L_mode == 1) {
            languageFile = "en_en.json";
        } else if (Main_GUI.L_mode == 2) {
            languageFile = "en_us.json";
        } else {
            languageFile = "default.json"; // L_modeが0, 1, 2以外の場合のデフォルト値
        }
        System.out.println("Selected language: " + (Main_GUI.L_mode == 0 ? JAPANESE : ENGLISH));
        JSONObject langJson = new JSONObject(readFile(languageFile));
        if (langJson.has("DONE")) {
            DONE = langJson.getString("DONE");
        }
        DONE = langJson.getString("DONE");
        LOCALIZE = langJson.getString("LOCALIZE");
        EQUIPMENT = langJson.getString("EQUIPMENT");
        COUNTRY = langJson.getString("COUNTRY");
        NONE = langJson.getString("NONE");
        MODE_SELECT = langJson.getString("MODE_SELECT");
        LG_SELECT = langJson.getString("LG_SELECT");
        ENGLISH = langJson.getString("ENGLISH");
        JAPANESE = langJson.getString("JAPANESE");
        Please_Select_mode = langJson.getString("Please_Select_mode");
        GFX = langJson.getString("GFX");
        GOAL = langJson.getString("GOAL");
        SHL = langJson.getString("SHL");
        Title = langJson.getString("Title");
        File = langJson.getString("File");
        Save = langJson.getString("Save");
        combined = langJson.getString("combined");
        str_loc = langJson.getString("str_loc");
        LR_no = langJson.getString("LR_no");
        IMAGE = langJson.getString("IMAGE");
        PREF = langJson.getString("PREF");
        Loc_Pref = langJson.getString("Loc_Pref");
        SAVE_SETTINGS = langJson.getString("SAVE_SETTINGS");
        FONT = langJson.getString("FONT");
        MODULE = langJson.getString("MODULE");
        NAME = langJson.getString("NAME");
        ABOUT = langJson.getString("ABOUT");
        TAG = langJson.getString("TAG");
        DESC = langJson.getString("DESC");
        COLOR = langJson.getString("COLOR");
        ICON = langJson.getString("ICON");
        LOAD = langJson.getString("LOAD");
        MAKER = langJson.getString("MAKER");
        Warn_M = langJson.getString("Warn_M");
        Warn_M2 = langJson.getString("Warn_M2");
        SDI = langJson.getString("SDI");
        FIN = langJson.getString("FIN");
        CTR = langJson.getString("CTR");
        TEC = langJson.getString("TEC");
        LNG = langJson.getString("LNG");
        VIW = langJson.getString("VIW");
        Survey = langJson.getString("Survey");
        Quit = langJson.getString("Quit");
        CVRT = langJson.getString("CVRT");
    }

    private static String readFile(String filename) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("./src/eightman/library/GUI/Localize/json/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
