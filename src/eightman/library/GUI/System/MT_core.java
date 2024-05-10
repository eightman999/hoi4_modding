package eightman.library.GUI.System;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eightman.library.GUI.Main_GUI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
                System.out.println("実行回数: " + runCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
