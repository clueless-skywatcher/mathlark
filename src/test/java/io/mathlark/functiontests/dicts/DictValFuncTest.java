package io.mathlark.functiontests.dicts;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class DictValFuncTest {
    @Test
    public void testDictVal() {
        execute("m = {\"a\": 1, \"b\": True, \"c\": \"abc\", \"d\": {\"x\": 1, \"y\": 2}}");
        assertEquals(execute("DictVal(m, \"a\")"), 1);
        assertEquals(execute("DictVal(m, \"b\")"), "True");
        assertEquals(execute("DictVal(m, \"c\")"), "abc");
        assertEquals(execute("DictVal(m, \"d\")"), Map.of(
            new StringExpression("x"), new NumericExpression(1),
            new StringExpression("y"), new NumericExpression(2)
        ));

        execute("m = {1: StringLength(\"abc\"), 0: {2: \"def\", \"ghi\": 1111}, \"a\": 25}");
        assertEquals(execute("DictVal(m, 1)"), 3);
        assertEquals(execute("DictVal(m, 0)"), Map.of(
            new NumericExpression(2), new StringExpression("def"),
            new StringExpression("ghi"), new NumericExpression(1111)
        ));
        assertEquals(execute("DictVal(m, \"a\")"), 25);
        assertEquals(execute("DictVal(DictVal(m, 0), 2)"), "def");
        assertEquals(execute("DictVal(DictVal(m, 0), \"ghi\")"), 1111);
    }
}
