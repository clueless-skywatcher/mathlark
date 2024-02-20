package io.mathlark.functiontests.general;

import java.util.List;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FuncDefTest {
    @Test
    public void testFuncDef() {
        assertEquals(execute("FuncDef(\"f\")"), "Defined function f");
        assertEquals(execute("FuncDef(\"Quinoa\")"), "Defined function Quinoa");

        assertEquals(execute("f(1)"), "f(1)");
        assertEquals(execute("Quinoa(True)"), "Quinoa(True)");

        assertEquals(execute("f(EvenQ(65))"), "f(False)");
    }

    @Test
    public void testMap() {
        execute("FuncDef(\"f\")");
        execute("FuncDef(\"g\")");
        assertEquals(execute("Map(f, [1, 2, 3])"), List.of("f(1)", "f(2)", "f(3)"));
        assertEquals(execute("Map(f, Map(g, [1, 2, 3]))"), List.of("f(g(1))", "f(g(2))", "f(g(3))"));
    }
}
