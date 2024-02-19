package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstFuncTest {
    @Test
    public void testFirst() {
        assertEquals((int) execute("First(List(1, 2, 3))"), 1);
        execute("a = List(False, True, False)");
        assertEquals((String) execute("First(a)"), "False");  
        assertEquals((int) execute("First([1, 2, 3])"), 1);
        execute("a = [False, True, False]");
        assertEquals((String) execute("First(a)"), "False");
    }
}
