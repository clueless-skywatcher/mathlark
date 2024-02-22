package io.mathlark.functiontests.numbers;

import static io.mathlark.parsing.LarkTestingUtils.execute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EvenQFuncTest {
    @Test
    public void testEven() {
        assertEquals(execute("EvenQ(1)"), "False");
        assertEquals(execute("EvenQ(2)"), "True");
        assertEquals(execute("EvenQ(3)"), "False");
        assertEquals(execute("EvenQ(4)"), "True");
        assertEquals(execute("EvenQ(5)"), "False");
        assertEquals(execute("EvenQ(6)"), "True");

        assertEquals(execute("EvenQ(2.5)"), "False");
        assertEquals(execute("EvenQ(0)"), "True");
        assertEquals(execute("EvenQ(\"abc\")"), "False");
        assertEquals(execute("EvenQ(True)"), "False");
        assertEquals(execute("EvenQ(False)"), "False");
        assertEquals(execute("EvenQ([1, 2, 3])"), "False");
    }
}
