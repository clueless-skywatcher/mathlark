package io.mathlark.functiontests.dicts;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.mathlark.parser.expressions.StringExpression;

public class DictKeysFuncTest {
    @Test
    public void testDict() {
        execute("m = {\"a\": 1, \"b\": True, \"c\": \"abc\"}");
        assertEquals(execute("DictKeys(m)"), List.of(
            new StringExpression("a"),
            new StringExpression("b"),
            new StringExpression("c")
        ));
        execute("m = {}");
        assertEquals(execute("DictKeys(m)"), List.of());
    }
}
