package model;

import org.json.JSONObject;
import persistence.Writable;

public class Log implements Writable {
    private String log;

    // EFFECTS: take a string and store as log
    public Log(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    @Override
    public String toString() {
        return log;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("log", log);
        return json;
    }
}
