package io.mathlark.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicParserTest {
    @Test
    public void testNumeric() {
        assertEquals((int) LarkTestingUtils.execute("5"), 5);
        assertEquals((double) LarkTestingUtils.execute("2.5"), 2.5);
        assertEquals((double) LarkTestingUtils.execute("2.0"), 2.0);
        assertEquals((double) LarkTestingUtils.execute("0.015"), 0.015);
        assertEquals((int) LarkTestingUtils.execute("-5"), -5);
        assertEquals((double) LarkTestingUtils.execute("-2.5"), -2.5);
    }

    @Test
    public void testString() {
        assertEquals((String) LarkTestingUtils.execute("\"abcd\""), "abcd");
    }

    @Test
    public void testBoolean() {
        assertEquals(LarkTestingUtils.execute("True"), "True");
        assertEquals(LarkTestingUtils.execute("False"), "False");
    }

    @Test
    public void testUninitializedVariable() {
        assertEquals((String) LarkTestingUtils.execute("a"), "Undefined");
        assertEquals((String) LarkTestingUtils.execute("ab"), "Undefined");
    }

    @Test
    public void testVariable() {
        assertEquals((String) LarkTestingUtils.execute("a"), "Undefined");
        assertEquals(LarkTestingUtils.execute("a = 5"), null);
        assertEquals((int) LarkTestingUtils.execute("a"), 5);

        assertEquals((String) LarkTestingUtils.execute("ab"), "Undefined");
        assertEquals(LarkTestingUtils.execute("ab = 69"), null);
        assertEquals((int) LarkTestingUtils.execute("ab"), 69);

        assertEquals((String) LarkTestingUtils.execute("abc"), "Undefined");
        assertEquals(LarkTestingUtils.execute("abc = 69.420"), null);
        assertEquals((double) LarkTestingUtils.execute("abc"), 69.420);

        assertEquals((String) LarkTestingUtils.execute("abcd"), "Undefined");
        assertEquals(LarkTestingUtils.execute("abcd = -1"), null);
        assertEquals((int) LarkTestingUtils.execute("abcd"), -1);
        assertEquals(LarkTestingUtils.execute("abcd = -1.5"), null);
        assertEquals((double) LarkTestingUtils.execute("abcd"), -1.5);

        assertEquals(LarkTestingUtils.execute("abcd = ab"), null);
        assertEquals((int) LarkTestingUtils.execute("abcd"), 69);
    }

    @Test
    public void testFunctionCall() {
        assertEquals((int) LarkTestingUtils.execute("Increment(1)"), 2);
        assertEquals((double) LarkTestingUtils.execute("Increment(2.5)"), 3.5);
        assertEquals((int) LarkTestingUtils.execute("Increment(Increment(1))"), 3);

        assertEquals((int) LarkTestingUtils.execute("Decrement(1)"), 0);
        assertEquals((double) LarkTestingUtils.execute("Decrement(2.5)"), 1.5);
        assertEquals((int) LarkTestingUtils.execute("Decrement(Decrement(1))"), -1);
    }

    @Test
    public void testPi() {
        assertEquals((double) LarkTestingUtils.execute("Pi"), Math.PI);
    }

    @Test
    public void testE() {
        assertEquals((double) LarkTestingUtils.execute("E"), Math.E);
    }

    @Test
    public void testDefineFunc() {
        assertEquals(LarkTestingUtils.execute("f"), "Undefined");
        LarkTestingUtils.execute("<f>");
        assertEquals(LarkTestingUtils.execute("f"), "f");
        assertEquals(LarkTestingUtils.execute("f(1)"), "f(1)");
    }
}
