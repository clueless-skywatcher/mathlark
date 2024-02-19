package io.mathlark.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import static io.mathlark.parsing.LarkTestingUtils.execute;

public class ListTest {
    @Test
    public void testList() {
        assertEquals(execute("[1, 2, 3]"), List.of(1, 2, 3));
        assertEquals(execute("[1, Increment(1), \"abcd\", True]"), List.of(1, 2, "abcd", "True"));
        execute("a = [1, Decrement(Increment(2)), \"abcd\", False]");
        assertEquals(execute("a"), List.of(1, 2, "abcd", "False"));
        assertEquals(execute("[1, [1, 2, 3], 3]"), List.of(1, List.of(1, 2, 3), 3));
    }

    @Test
    public void testAccess() {
        LarkTestingUtils.execute("a = [1, True, \"abc\"]");
        assertEquals((int) LarkTestingUtils.execute("a{0}"), 1);
        assertEquals((String) LarkTestingUtils.execute("a{1}"), "True");
        assertEquals((String) LarkTestingUtils.execute("a{2}"), "abc");

        execute("a = [1, [1, 3, 4], 3]");
        assertEquals((List<?>) execute("a{1}"), List.of(1, 3, 4));

        execute("a = [1, [1, 3, 4], 4]");
        assertEquals(execute("a{-1}"), 4);
    }
}
