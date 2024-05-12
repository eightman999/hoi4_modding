package eightman.library.GUI.System;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eightman.library.GUI.Main_GUI;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static eightman.library.GUI.Main_GUI.modPathListModel;
import static eightman.library.GUI.Main_GUI.modPathMap;
import static eightman.library.GUI.language.ENGLISH;
import static eightman.library.GUI.language.JAPANESE;

public class MT_core {
    public static void load_config() {

            Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
            if (Files.exists(configPath)) {
                try (FileReader reader = new FileReader(configPath.toString())) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Map<String, Object>>(){}.getType();
                    Map<String, Object> settings = gson.fromJson(reader, type);

                    // modPathMapの設定を反映
                    modPathMap = (Map<String, String>) settings.get("modPathMap");
                    if (modPathMap != null) {
                        for (Map.Entry<String, String> entry : modPathMap.entrySet()) {
                            modPathListModel.addElement(entry.getKey() + ": " + entry.getValue());
                        }
                    }

                    // 言語設定を反映
                    String language = (String) settings.get("lang");
                    if (language != null) {
                        if (language.equals(ENGLISH)) {
                            Main_GUI.L_mode = 1;
                        } else if (language.equals(JAPANESE)) {
                            Main_GUI.L_mode = 0;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void run_counter() {
        Path configPath = Paths.get("Hoi4_modding_Tool", "config", "setting.config");
        if (Files.exists(configPath)) {
            try (FileReader reader = new FileReader(configPath.toString())) {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> settings = gson.fromJson(reader, type);

                // 実行回数を取得し、1増やす
                Number runCountNumber = (Number) settings.get("runCount");
                Integer runCount = (runCountNumber != null) ? runCountNumber.intValue() : 0;
                runCount++;
                Main_GUI.run = runCount;
                // 実行回数を設定に追加
                settings.put("runCount", runCount);

                // 設定をファイルに保存
                String json = gson.toJson(settings);
                try (FileWriter writer = new FileWriter(configPath.toString())) {
                    writer.write(json);
                }
                MT_System.out.println("実行回数: " + runCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MT_System {
        private static final Logger logger = Logger.getLogger(MT_System.class.getName());
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
    }
}

