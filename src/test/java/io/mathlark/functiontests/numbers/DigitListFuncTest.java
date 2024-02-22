package io.mathlark.functiontests.numbers;

import static io.mathlark.parsing.LarkTestingUtils.execute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class DigitListFuncTest {
    @Test
    public void testDigitList() {
        assertEquals(execute("DigitList(0)"), List.of(0));
        assertEquals(execute("DigitList(1)"), List.of(1));
        assertEquals(execute("DigitList(12)"), List.of(1, 2));
    }
}
