package io.mathlark.functiontests.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static io.mathlark.parsing.LarkTestingUtils.execute;

public class NestFuncTest {
    @Test
    public void testNest() {
        execute("<f>");
        execute("|x|");

        assertEquals(execute("Nest(f, x, 0)"), "x");
        assertEquals(execute("Nest(f, x, 1)"), "f(x)");
        assertEquals(execute("Nest(f, x, 2)"), "f(f(x))");
        assertEquals(execute("Nest(f, x, 3)"), "f(f(f(x)))");
        assertEquals(execute("Nest(f, x, 4)"), "f(f(f(f(x))))");

        assertEquals(execute("Nest(Increment, 1, 0)"), 1);
        assertEquals(execute("Nest(Increment, 1, 1)"), 2);
        assertEquals(execute("Nest(Increment, 1, 2)"), 3);
        assertEquals(execute("Nest(Increment, 1, 3)"), 4);
        assertEquals(execute("Nest(Increment, 1, 4)"), 5);
        assertEquals(execute("Nest(Increment, 1, 5)"), 6);
    }
}
