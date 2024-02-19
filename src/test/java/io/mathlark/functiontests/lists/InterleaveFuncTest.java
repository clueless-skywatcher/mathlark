package io.mathlark.functiontests.lists;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InterleaveFuncTest {
    @Test
    public void testInterleave() {
        assertEquals(execute("Interleave([1, 2, 3], [\"a\", \"b\", \"c\"])"), List.of(
            1, "a", 2, "b", 3, "c"
        ));
        assertEquals(execute("Interleave([1, 2, 3], 4)"), List.of(1, 4, 2, 4, 3));
        assertEquals(execute("Interleave([1, 2], [\"a\", \"b\", \"c\"])"), List.of(1, "a", 2));
        assertEquals(execute("Interleave([1, 2, 3, 4, 5, 6, 7, 8, 9], [\"x\", \"y\"])"), Arrays.asList(
            new Object[] {1, "x", 2, "y", 3, "x", 4, "y", 5, "x", 6, "y", 7, "x", 8, "y", 9}
        ));
    }
}
