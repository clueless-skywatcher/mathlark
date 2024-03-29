package io.mathlark.functiontests.general;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static io.mathlark.parsing.LarkTestingUtils.execute;

public class MapFuncTest {
    @Test
    public void testMap() {
        assertEquals(execute("Map(EvenQ, [1, 2, 3, 4, 5])"), List.of(
            "False", "True", "False", "True", "False"
        ));
        execute("m = {1: 25, \"b\": 69, 420: False}");
        assertEquals(execute("Map(Increment, [1, 2, 3, 4, 5])"), List.of(2, 3, 4, 5, 6));
        assertEquals(execute("Map(DictVal, [[m, 1], [m, \"b\"], [m, 420]])"), List.of(
            25, 69, "False"
        ));
        execute("<f>");
        assertEquals(execute("Map(f, [1, 2, 3])"), List.of("f(1)", "f(2)", "f(3)"));
    }
}
