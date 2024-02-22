package io.mathlark.functiontests.numbers;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DigitSumFuncTest {
    @Test
    public void testDigitList() {
        assertEquals(execute("DigitSum(0)"), 0);
        assertEquals(execute("DigitSum(1)"), 1);
        assertEquals(execute("DigitSum(12)"), 3);
        assertEquals(execute("DigitSum(12345678)"), 36);
    }
}
