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
    }
}
