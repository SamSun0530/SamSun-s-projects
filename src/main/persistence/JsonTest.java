package persistence;

import model.Log;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkLog(String logString, Log log) {
        assertEquals(logString, log.getLog());
    }
}
