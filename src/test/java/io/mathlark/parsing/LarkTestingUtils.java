package io.mathlark.parsing;

import io.mathlark.parser.LarkParserEngine;

public class LarkTestingUtils {
    public static Object execute(String input) {
        LarkParserEngine engine = new LarkParserEngine();
        return engine.parse(input);
    }
}
