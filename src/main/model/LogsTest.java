package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogsTest {
    private Logs logs;

    @BeforeEach
    public void setup() {
        logs = new Logs();
    }

    @Test
    public void testEmptyLogs() {
        assertEquals(0, logs.size());
    }

    @Test
    public void testGeneralLogs() {
        logs.add(new Log("1 plus 1 = 2"));
        logs.add(new Log("2 minus 1 = 1"));
        assertEquals(2, logs.size());
        assertEquals("1 plus 1 = 2", logs.get(0).getLog());
        assertEquals("2 minus 1 = 1", logs.get(1).getLog());
    }
}
