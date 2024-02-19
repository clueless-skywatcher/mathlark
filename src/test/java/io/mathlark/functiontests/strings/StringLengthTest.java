package io.mathlark.functiontests.strings;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringLengthTest {
    @Test
    public void testStrLength() {
        assertEquals(execute("StringLength(\"\")"), 0);
        assertEquals(execute("StringLength(\"a\")"), 1);
        assertEquals(execute("StringLength(\"ab\")"), 2);
        assertEquals(execute("StringLength(\"abc\")"), 3);
    }
}
