package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class AccessTest {
    @Test
    public void testList() {
        assertEquals((int) execute("List(1, 2, 3){0}"), 1);
        assertEquals((int) execute("List(1, 2, 3){1}"), 2);
        assertEquals((int) execute("List(1, 2, 3){2}"), 3);
        assertEquals((int) execute("List(69, 81, 95){-1}"), 95);
        assertEquals((int) execute("List(69, 81, 95){-2}"), 81);
        assertEquals((int) execute("List(69, 81, 95){-3}"), 69);
    }

    @Test
    public void testElementAt() {
        execute("a = List(1, 2, 3)");
        assertEquals(execute("ElementAt(a, 0)"), 1);
        assertEquals(execute("ElementAt(a, 1)"), 2);
        assertEquals(execute("ElementAt(a, 2)"), 3);
        execute("a = List(1, List(11, 23, 69), 3)");
        assertEquals(execute("ElementAt(a, 1)"), List.of(11, 23, 69));
        assertEquals(execute("ElementAt(ElementAt(a, 1), 2)"), 69);
        assertEquals(execute("ElementAt(a, -1)"), 3);
        assertEquals(execute("ElementAt(a, -2)"), List.of(11, 23, 69));
        assertEquals(execute("ElementAt(a, -3)"), 1);
        assertEquals(execute("ElementAt(ElementAt(a, -2), -1)"), 69);
        assertEquals(execute("ElementAt(ElementAt(a, -2), -2)"), 23);
        assertEquals(execute("ElementAt(ElementAt(a, -2), -3)"), 11);
    }
}
