package persistence;


import model.Log;
import model.Logs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads log history from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads logs from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Logs read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLogs(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses logs from JSON object and returns it
    private Logs parseLogs(JSONObject jsonObject) {
        Logs logs = new Logs();
        JSONArray logsArray = jsonObject.getJSONArray("logs");

        for (int i = 0; i < logsArray.length(); i++) {
            JSONObject logJson = logsArray.getJSONObject(i);
            Log l = new Log(logJson.getString("log"));
            logs.add(l);
        }
        return logs;
    }
}
