package io.mathlark.parser;

import io.mathlark.generated.LarkBaseVisitor;
import io.mathlark.generated.LarkParser;
import io.mathlark.parser.expressions.IExpression;

public class DefaultLarkVisitor extends LarkBaseVisitor<IExpression> {
    public IExpression visitExpr(LarkParser.ExprContext ctx) {
        return ctx.exprObject;
    }
}
