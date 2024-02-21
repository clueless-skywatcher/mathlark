package io.mathlark.functiontests.general;

import java.util.List;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NestListFuncTest {
    @Test
    public void testNestList() {
        execute("<f>");
        execute("|x|");

        assertEquals(execute("NestList(f, x, 0)"), List.of("x"));
        assertEquals(execute("NestList(f, x, 1)"), List.of("x", "f(x)"));
        assertEquals(execute("NestList(f, x, 2)"), List.of("x", "f(x)", "f(f(x))"));

        assertEquals(execute("NestList(Increment, 1, 0)"), List.of(1));
        assertEquals(execute("NestList(Increment, 1, 1)"), List.of(1, 2));
        assertEquals(execute("NestList(Increment, 1, 2)"), List.of(1, 2, 3));
    }
}
