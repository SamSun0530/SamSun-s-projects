package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

// A list of log
public class Logs implements Writable {
    private List<Log> logs;

    // EFFECTS: construct a empty log list
    public Logs() {
        logs = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add log to list of log
    public void add(Log log) {
        logs.add(log);
        EventLog.getInstance().logEvent(new Event("A calculation log is added."));
    }

    // EFFECTS: return the size of logs
    public int size() {
        return logs.size();
    }

    // REQUIRES: index cannot < 0 or >= size
    // EFFECTS: return the log object with index
    public Log get(int index) {
        return logs.get(index);
    }

    // MODIFIES: this
    // EFFECTS: clear the existing logs
    public void clearLogs() {
        logs = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Calculation log history is cleared."));
    }

    @Override
    public String toString() {
        return "logs:" + logs;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("logs", logsToJson());
        return json;
    }

    // EFFECTS: returns things in logs as a JSON array
    private JSONArray logsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Log log : logs) {
            jsonArray.put(log.toJson());
        }

        return jsonArray;
    }
}

