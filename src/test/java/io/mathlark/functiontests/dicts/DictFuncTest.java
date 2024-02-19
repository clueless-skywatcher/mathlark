package io.mathlark.functiontests.dicts;

import java.util.Map;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class DictFuncTest {
    @Test
    public void testCall() {
        execute("m = Dict([\"a\", 1], [\"b\", True], [\"c\", \"abc\"], [1, False])");
        assertEquals(execute("m"), Map.of(
            new StringExpression("a"), new NumericExpression(1),
            new StringExpression("b"), new StringExpression("True"),
            new StringExpression("c"), new StringExpression("abc"),
            new NumericExpression(1), new StringExpression("False")
        ));
        execute("m = Dict()");
        assertEquals(execute("m"), Map.of());
    }

    @Test
    public void testNested() {
        execute("m = Dict([\"a\", 1], [\"b\", True], [\"c\", Dict([\"x\", 5])])");
        assertEquals(execute("m"), Map.of(
            new StringExpression("a"), new NumericExpression(1),
            new StringExpression("b"), new StringExpression("True"),
            new StringExpression("c"), Map.of(
                new StringExpression("x"), new NumericExpression(5)
            )
        ));
    }
}
