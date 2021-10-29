package persistence;

import org.json.JSONObject;

// This class uses codes from the JsonSerializationDemo-master
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
