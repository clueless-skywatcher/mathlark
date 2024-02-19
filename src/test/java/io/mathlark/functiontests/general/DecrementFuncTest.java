package io.mathlark.functiontests.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.mathlark.parsing.LarkTestingUtils;

public class DecrementFuncTest {
    @Test
    public void test() {
        assertEquals((int) LarkTestingUtils.execute("Decrement(1)"), 0);
        assertEquals((double) LarkTestingUtils.execute("Decrement(2.5)"), 1.5);
        assertEquals((int) LarkTestingUtils.execute("Decrement(Decrement(1))"), -1);
        assertEquals((int) LarkTestingUtils.execute("Decrement(10000000)"), 9999999);
        assertEquals((double) LarkTestingUtils.execute("Decrement(10.12341234)"), 9.12341234);
        assertEquals((int) LarkTestingUtils.execute("Decrement(-1)"), -2);
        assertEquals((int) LarkTestingUtils.execute("Decrement(-10)"), -11);
        assertEquals((double) LarkTestingUtils.execute("Decrement(-10.1245)"), -11.1245);

        LarkTestingUtils.execute("a = Decrement(1)");
        assertEquals((int) LarkTestingUtils.execute("a"), 0);
        LarkTestingUtils.execute("a = Decrement(Decrement(1))");
        assertEquals((int) LarkTestingUtils.execute("a"), -1);
        LarkTestingUtils.execute("a = Decrement(Decrement(a))");
        assertEquals((int) LarkTestingUtils.execute("a"), -3);
    }

    @Test
    public void testWithIncrement() {
        assertEquals((int) LarkTestingUtils.execute("Decrement(Increment(1))"), 1);
        assertEquals((double) LarkTestingUtils.execute("Decrement(Increment(2.285))"), 2.285);
    }
}
