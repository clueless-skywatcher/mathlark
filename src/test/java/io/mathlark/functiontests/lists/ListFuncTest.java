package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.mathlark.parsing.LarkTestingUtils;

public class ListFuncTest {
    @Test
    public void testList() {
        assertEquals(execute("List(1, 2, 3)"), List.of(1, 2, 3));
        assertEquals(execute("List(1, Increment(1), \"abcd\", True)"), List.of(1, 2, "abcd", "True"));
        execute("a = List(1, Decrement(Increment(2)), \"abcd\", False)");
        assertEquals(execute("a"), List.of(1, 2, "abcd", "False"));
        assertEquals(execute("List(1, List(1, 2, 3), 3)"), List.of(1, List.of(1, 2, 3), 3));
    }

    @Test
    public void testAccess() {
        LarkTestingUtils.execute("a = List(1, True, \"abc\")");
        assertEquals((int) LarkTestingUtils.execute("a{0}"), 1);
        assertEquals((String) LarkTestingUtils.execute("a{1}"), "True");
        assertEquals((String) LarkTestingUtils.execute("a{2}"), "abc");

        execute("a = List(1, [1, 3, 4], 3)");
        assertEquals((List<?>) execute("a{1}"), List.of(1, 3, 4));

        execute("a = List(1, List(1, 3, 4), 3)");
        assertEquals((List<?>) execute("a{1}"), List.of(1, 3, 4));

        assertEquals((String) execute("a{5}"), "Undefined");

        LarkTestingUtils.execute("a = List(2, Undefined, Undefined)");
        assertEquals((int) LarkTestingUtils.execute("a{0}"), 2);
        assertEquals((String) LarkTestingUtils.execute("a{1}"), "Undefined");
        assertEquals((String) LarkTestingUtils.execute("a{2}"), "Undefined");
    }
}
