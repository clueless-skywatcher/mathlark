package io.mathlark.functiontests.numbers;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumQFuncTest {
    @Test
    public void testNum() {
        assertEquals(execute("NumQ(1)"), "True");
        assertEquals(execute("NumQ(1.5)"), "True");
        assertEquals(execute("NumQ(0)"), "True");
        assertEquals(execute("NumQ(-101)"), "True");
        assertEquals(execute("NumQ(-1.8875)"), "True");
        assertEquals(execute("NumQ(Pi)"), "True");
        assertEquals(execute("NumQ(E)"), "True");

        assertEquals(execute("NumQ(True)"), "False");
        assertEquals(execute("NumQ(False)"), "False");
        assertEquals(execute("NumQ(\"a\")"), "False");
        assertEquals(execute("NumQ(Undefined)"), "False");
        assertEquals(execute("NumQ([1, 2, 3])"), "False");
        assertEquals(execute("NumQ({1: \"a\", \"c\": 81})"), "False");
    }
}
