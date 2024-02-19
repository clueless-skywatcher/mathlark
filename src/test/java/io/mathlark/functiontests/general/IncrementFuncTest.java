package io.mathlark.functiontests.general;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.mathlark.parsing.LarkTestingUtils;

public class IncrementFuncTest {
    @Test
    public void test() {
        assertEquals((int) LarkTestingUtils.execute("Increment(1)"), 2);
        assertEquals((double) LarkTestingUtils.execute("Increment(2.5)"), 3.5);
        assertEquals((int) LarkTestingUtils.execute("Increment(Increment(1))"), 3);
        assertEquals((int) LarkTestingUtils.execute("Increment(10000000)"), 10000001);
        assertEquals((double) LarkTestingUtils.execute("Increment(10.12341234)"), 11.12341234);
        assertEquals((int) LarkTestingUtils.execute("Increment(-1)"), 0);
        assertEquals((int) LarkTestingUtils.execute("Increment(-10)"), -9);
        assertEquals((double) LarkTestingUtils.execute("Increment(-10.1245)"), -9.1245);

        LarkTestingUtils.execute("a = Increment(1)");
        assertEquals((int) LarkTestingUtils.execute("a"), 2);
        LarkTestingUtils.execute("a = Increment(Increment(1))");
        assertEquals((int) LarkTestingUtils.execute("a"), 3);
        LarkTestingUtils.execute("a = Increment(Increment(a))");
        assertEquals((int) LarkTestingUtils.execute("a"), 5);
    }
}
