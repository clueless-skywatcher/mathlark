package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastFuncTest {
    @Test
    public void testLast() {
        assertEquals((int) execute("Last(List(1, 2, 3))"), 3);
        execute("a = List(False, True, 6)");
        assertEquals((int) execute("Last(a)"), 6);  
        assertEquals((int) execute("Last([1, 2, 3])"), 3);
        execute("a = [False, True, 69]");
        assertEquals((int) execute("Last(a)"), 69);
    }
}
