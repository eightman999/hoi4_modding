package eightman.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import eightman.library.CUI.*;
import eightman.library.GUI.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static eightman.library.Core.out.logError;
import static eightman.library.GUI.Main_GUI.modList;
import static eightman.library.GUI.Main_GUI.naval_path;

public class Core {

    public static class ROOT {

        public void localize() {
            localize.localization();
        }

        public void equipmenttool() {
            equipmenttool eq = new equipmenttool();
            eq.equipment_modues();
        }

        public void peoplename() {
            peoplename pn = new peoplename();
            pn.peoplenames();
        }

        public void ship_name() {
            ship_name sn = new ship_name();
            sn.ship_names();
        }

        public void country_name() {
            country_name.country_names();
        }

        public void start() {
            start st = new start();
            st.START();
        }
    }

    public static void ERROR(){
        System.out.println("予期せぬエラーが発生しました。ソフトを終了します。");
        System.exit(0);
    }

    public static void BREAK(){
        System.out.println("ソフトを終了します。");
    }

    private static final Logger logger = Logger.getLogger(Core.class.getName());

    public static void setupLogger() {
        try {
            // ログメッセージのフォーマットを設定
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "[%1$tF %1$tT] [%4$-7s] %5$s %n");

            // 現在の日時を取得
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmm");
            String dateString = format.format(now);

            // ログを出力するディレクトリを作成
            Path logDir = Paths.get("./Hoi4_modding_Tool/logs/");
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
            }

            // ログを出力するファイルの FileHandler を作成
            FileHandler fileHandler = new FileHandler(logDir.resolve("System_" + dateString + ".log").toString(), true);
            fileHandler.setFormatter(new SimpleFormatter()); // ログのフォーマットを設定

            // Logger に FileHandler を追加
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
            throw new RuntimeException(e);
        }
    }

    public static class out {

        public static void logInfo(String message) {
            logger.info(message); // INFO レベルのログを出力
        }

        public static void logError(String message, Exception e) {
            logger.log(Level.SEVERE, message, e); // ERROR レベルのログを出力
        }

        public static void println(String message) {
            System.out.println(message); // コンソールに出力
            logInfo(message); // ログファイルに出力
        }

    }

    public static void load_config() {
        Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
        if (Files.exists(configPath)) {
            try (FileReader reader = new FileReader(configPath.toString())) {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> settings = gson.fromJson(reader, type);

                // modPathMapの設定を反映
                Map<String, String> modPathMap = (Map<String, String>) settings.get("modPathMap");
                System.out.println("modPathMap=" + modPathMap);


                naval_path = "src/eightman/library/GUI/System/Test_mtg_naval.txt";

                // その他の設定を反映
                String language = (String) settings.get("lang");
                if (language != null) {
                    if (language.equals("ENGLISH")) {
                        Main_GUI.L_mode = 1;
                    } else if (language.equals("JAPANESE")) {
                        Main_GUI.L_mode = 0;
                    }
                }

                Main_GUI.use_font = (String) settings.get("font");

                // mod名をmodListに設定
                modList = new ArrayList<>(modPathMap.keySet());
                Main_GUI.setModList(modList);
                Core.out.println("ModList=" + modList);

                System.out.println("Npath=" + naval_path);
                System.out.println("Config loaded.");
            } catch (IOException e) {
                logError("Failed to load config.", e);
                e.printStackTrace();
            }
        }
    }

    public static void run_counter() {
        Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
        if (Files.exists(configPath)) {
            try (FileReader reader = new FileReader(configPath.toString())) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> settings = gson.fromJson(reader, type);

                // 実行回数を取得し、1増やす
                Number runCountNumber_h = (Number) settings.get("runCount_h");
                Integer runCount_h = (runCountNumber_h != null) ? runCountNumber_h.intValue() : 0;
                Number runCountNumber = (Number) settings.get("runCount");
                Integer runCount = (runCountNumber != null) ? runCountNumber.intValue() : 0;
                runCount++;
                Main_GUI.run = runCount;
                if (Main_GUI.run == 100) {
                    Core.out.println("Thank you for 100 using Hoi4 Modding Tool!");
                    runCount_h++;
                    Main_GUI.runtime_h++;
                    Main_GUI.run = 0;
                }

                // 実行回数を設定に追加
                settings.put("runCount", runCount);
                settings.put("runCount_h", runCount_h);

                // 設定をファイルに保存
                String json = gson.toJson(settings);
                try (FileWriter writer = new FileWriter(configPath.toString())) {

                    writer.write(json);
                }
                int sum = runCount_h*100+ runCount;
                Core.out.println("実行回数: "+sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
