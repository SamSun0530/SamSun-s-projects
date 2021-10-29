package model;

import org.json.JSONObject;
import persistence.Writable;

// One single log of operation
public class Log implements Writable {
    private String log;

    // EFFECTS: take a string and store as log
    public Log(String log) {
        this.log = log;
    }

    // EFFECTS: return the log
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
