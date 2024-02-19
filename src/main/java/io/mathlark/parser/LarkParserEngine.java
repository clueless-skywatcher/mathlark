package io.mathlark.parser;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import io.mathlark.generated.LarkLexer;
import io.mathlark.generated.LarkParser;
import io.mathlark.parser.expressions.IExpression;

public class LarkParserEngine {
    public Object parse(String input) {
        CharStream inputStream = CharStreams.fromString(input);
        LarkLexer lexer = new LarkLexer(inputStream);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        LarkParser parser = new LarkParser(stream);
        parser.setErrorHandler(new BailErrorStrategy());

        try {
            ParseTree tree = parser.prog();
            DefaultLarkVisitor visitor = new DefaultLarkVisitor();
            IExpression expr = visitor.visit(tree).evaluate();
            if (expr != null) {
                return expr.evaluate().getVal();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
