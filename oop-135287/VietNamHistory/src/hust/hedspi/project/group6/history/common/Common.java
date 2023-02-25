package hust.hedspi.project.group6.history.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Common {
    public static void saveToJson(List<Map<String, Object>> list, String file) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(list);
        String root = System.getProperty("user.dir");
        String path = root + "/json/" + file;
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
