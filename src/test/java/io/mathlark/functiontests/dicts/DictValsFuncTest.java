package io.mathlark.functiontests.dicts;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;

public class DictValsFuncTest {
    @Test
    public void testDict() {
        execute("m = {\"a\": 1, \"b\": True, \"c\": \"abc\"}");
        assertEquals(execute("DictVals(m)"), List.of(
            new NumericExpression(1),
            new StringExpression("True"),
            new StringExpression("abc")
        ));
        execute("m = {}");
        assertEquals(execute("DictVals(m)"), List.of());
    }
}
