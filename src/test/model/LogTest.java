package model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {
    private Log log;

    @Test
    public void testLogString() {
        log = new Log("1 plus 2 = 3");
        assertEquals("1 plus 2 = 3", log.getLog());
        assertEquals("1 plus 2 = 3", log.toString());
    }
}
