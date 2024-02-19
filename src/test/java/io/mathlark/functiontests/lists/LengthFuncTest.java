package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthFuncTest {
    @Test
    public void testList() {
        assertEquals((int) execute("Length(List(1, 2, 3))"), 3);
        assertEquals((int) execute("Length(List())"), 0);
        assertEquals((int) execute("Length([1, 2, 3])"), 3);
        assertEquals((int) execute("Length([])"), 0);
    }

    @Test
    public void testNum() {
        assertEquals((int) execute("Length(1)"), 0);
        assertEquals((int) execute("Length(1.2)"), 0);
        assertEquals((int) execute("Length(1.23)"), 0);
        assertEquals((int) execute("Length(1.234)"), 0);
        assertEquals((int) execute("Length(1.2345)"), 0);
    }

    @Test
    public void testStr() {
        assertEquals((int) execute("Length(\"\")"), 0);
        assertEquals((int) execute("Length(\"a\")"), 0);
        assertEquals((int) execute("Length(\"ab\")"), 0);
        assertEquals((int) execute("Length(\"abc\")"), 0);
        assertEquals((int) execute("Length(\"abcd\")"), 0);
    }

    @Test
    public void testBool() {
        assertEquals((int) execute("Length(True)"), 0);
        assertEquals((int) execute("Length(False)"), 0);
    }

    @Test
    public void testDict() {
        assertEquals((int) execute("Length({})"), 0);
        assertEquals((int) execute("Length({\"a\": 1})"), 1);
        assertEquals((int) execute("Length({\"a\": 1, \"b\": \"abc\"})"), 2);
        assertEquals((int) execute("Length({\"a\": 1, \"b\": \"abc\", \"c\": False})"), 3);
    }
}
