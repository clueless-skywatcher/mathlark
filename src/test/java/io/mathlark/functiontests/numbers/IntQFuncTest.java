package io.mathlark.functiontests.numbers;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IntQFuncTest {
    @Test
    public void testInt() {
        assertEquals(execute("IntQ(1)"), "True");
        assertEquals(execute("IntQ(2)"), "True");
        assertEquals(execute("IntQ(3)"), "True");
        assertEquals(execute("IntQ(4)"), "True");
        assertEquals(execute("IntQ(5)"), "True");
        assertEquals(execute("IntQ(6)"), "True");

        assertEquals(execute("IntQ(2.5)"), "False");
        assertEquals(execute("IntQ(0)"), "True");
        assertEquals(execute("IntQ(-1)"), "True");
        assertEquals(execute("IntQ(\"abc\")"), "False");
        assertEquals(execute("IntQ(True)"), "False");
        assertEquals(execute("IntQ(False)"), "False");
        assertEquals(execute("IntQ([1, 2, 3])"), "False");
    }
}
