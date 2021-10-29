package persistence;

import model.Log;
import model.Logs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Logs logs = new Logs();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLogs() {
        try {
            Logs logs = new Logs();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogs.json");
            logs = reader.read();
            assertEquals(0, logs.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogs() {
        try {
            Logs logs = new Logs();
            logs.add(new Log("1 plus 1 = 2"));
            logs.add(new Log("2 multiply 2 = 4"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogs.json");
            logs = reader.read();
            assertEquals(2, logs.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
