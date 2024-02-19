package io.mathlark.parsing;

import static io.mathlark.parsing.LarkTestingUtils.execute;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import io.mathlark.parser.expressions.NumericExpression;
import io.mathlark.parser.expressions.StringExpression;
import io.mathlark.parser.expressions.IExpression;

public class DictTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testDict() {
        execute("m = {\"a\": 1, \"b\": True, \"c\": \"abc\"}");
        assertEquals((Map<StringExpression, IExpression>) execute("m"), Map.of(
            new StringExpression("a"), new NumericExpression(1),
            new StringExpression("b"), new StringExpression("True"),
            new StringExpression("c"), new StringExpression("abc")
        ));
        execute("m = {}");
        assertEquals(execute("m"), Map.of());
    }

    @Test
    public void testAccess() {
        execute("m = {\"a\": 1, \"b\": True, \"c\": \"abc\"}");
        assertEquals((int) execute("m{\"a\"}"), 1);
        assertEquals((String) execute("m{\"b\"}"), "True");
        assertEquals((String) execute("m{\"c\"}"), "abc");
        assertEquals((String) execute("m{\"d\"}"), "Undefined");

        execute("m = {\"a\": 1, 1: True, 69.25: \"abc\"}");
        assertEquals(execute("m{\"a\"}"), 1);
        assertEquals(execute("m{1}"), "True");
        assertEquals(execute("m{69.25}"), "abc");
    }
    @Test
    public void testLength() {
        execute("m = {}");
        assertEquals((int) execute("Length(m)"), 0);
        assertEquals((int) execute("Length({\"a\": 1, \"b\": \"c\", \"c\": False})"), 3);
    }

    @Test
    public void testNest() {
        execute("m = {\"a\": 1, \"b\": \"abc\", \"c\": {\"x\": 69.52, \"y\": False}}");
        assertEquals(execute("m"), Map.of(
            new StringExpression("a"), new NumericExpression(1),
            new StringExpression("b"), new StringExpression("abc"),
            new StringExpression("c"), Map.of(
                new StringExpression("x"), new NumericExpression(69.52),
                new StringExpression("y"), new StringExpression("False")
            )
        ));
    }
}
