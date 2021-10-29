package persistence;

import model.Logs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Logs log = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLogs() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogs.json");
        try {
            Logs logs = reader.read();
            assertEquals(0, logs.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLogs() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogs.json");
        try {
            Logs logs = reader.read();
            assertEquals(2, logs.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
