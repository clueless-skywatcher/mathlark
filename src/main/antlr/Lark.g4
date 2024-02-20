grammar Lark;

@header {

import java.util.*;

import lombok.Getter;

import io.mathlark.parser.expressions.*;
import io.mathlark.parser.SymbolTables;
import io.mathlark.funcs.AllFunctionRegistry;

}

@members {

class MapEntry {
    private @Getter IExpression key;
    private @Getter IExpression value;

    public MapEntry(IExpression key, IExpression value) {
        this.key = key;
        this.value = value;
    }
}

}

prog
    : expr (NEWLINE+ expr?)* 
    ;

expr returns [IExpression exprObject]
    : expr '**' expr
    | expr ('*' | '//') expr
    | expr ('+' | '-') expr
    | '-' val=expr
    | numberConstant { $exprObject = new NumericExpression($numberConstant.numVal); } // Numbers, e.g. 123
    | stringConstant { $exprObject = new StringExpression($stringConstant.str); } // Strings
    | symbol LPAREN exprList RPAREN {
        if (!AllFunctionRegistry.isFunc($symbol.symVal)) {
            $exprObject = new FunctionCallExpression($symbol.symVal, $exprList.exprs);
        }
        $exprObject = new FunctionCallExpression($symbol.symVal, $exprList.exprs).evaluate();
    }// Function(Expression)
    | LPAREN inner=expr RPAREN {
        $exprObject = $inner.exprObject;
    }
    | symbol ASSIGN expr {
        $exprObject = new AssignmentExpression($symbol.symVal, $expr.exprObject);
    } // Variable assignment, e.g: a = 1, b = List(1, 2, 3)
    | symbol {
        String symStr = $symbol.symVal;
        if (AllFunctionRegistry.isFunc(symStr)) {
            $exprObject = new StringExpression("Inbuilt function: " + symStr);
        }
        else if (SymbolTables.isGlobal(symStr)) {
            $exprObject = SymbolTables.evalGlobal(symStr);
        }
        else {
            $exprObject = SymbolTables.eval(symStr);
        }
    } // Some value/symbol
    | LBRACE mapExprs RBRACE {
        $exprObject = new DictExpression($mapExprs.map);
    } // Dicts, e.g: {a: 1, b: 2, c: False}
    | a=expr LBRACE b=mapKey RBRACE {
        $exprObject = new AccessExpression($a.exprObject, $b.mapKeyExp);
    } // a{1}: Fetches the 1st entry of a 0-indexed iterable a
    | LSQUARE exprList RSQUARE {
        $exprObject = new ListExpression($exprList.exprs);
    } // Lists, e.g: [1, 2, 3]
    ;

mapExprs returns [Map<IExpression, IExpression> map]
    @init {
        $map = new HashMap<>();
    }
    : mapExpr? { 
        if ($mapExpr.text != null) {
            $map.put($mapExpr.entry.getKey(), $mapExpr.entry.getValue());
        }
    }
    (COMMA mapExpr? {
        if ($mapExpr.text != null) {
            $map.put($mapExpr.entry.getKey(), $mapExpr.entry.getValue());
        }
    })*
    ;

mapExpr returns [MapEntry entry]
    : key=mapKey ':' value=expr {
        $entry = new MapEntry($mapKey.mapKeyExp, $value.exprObject);
    }
    ;

mapKey returns [IExpression mapKeyExp]
    : stringConstant {
        $mapKeyExp = new StringExpression($stringConstant.str);
    }
    | numberConstant {
        $mapKeyExp = new NumericExpression($numberConstant.numVal);
    }
    ;

symbol returns [String symVal]
    : identifier { $symVal = $identifier.text; }
    ;

exprList returns [List<IExpression> exprs]
    @init {
        $exprs = new ArrayList<>();
    }
    : expr? { if($expr.text != null) $exprs.add($expr.exprObject); } (COMMA expr? { if ($expr.text != null) $exprs.add($expr.exprObject); } )*
    ;

numberConstant returns [Number numVal]
    : val=INT { $numVal = Integer.parseInt($val.text); }
    | val=DECIMAL { $numVal = Double.parseDouble($val.text); }
    ;

stringConstant returns [String str]
    : '"' validChars '"' { $str = $validChars.text; }
    ;

validChars
    :  (LETTER | DIGIT)*
    ;

identifier
    : LETTER+ (LETTER | DIGIT)*
    ;

INT: ('-')? DIGIT+;
DECIMAL: ('-')? (DIGIT+ '.' DIGIT+);

ASSIGN: '=';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LSQUARE: '[';
RSQUARE: ']';
COMMA: ',';
NEWLINE: '\n';

DIGIT: [0-9];
LETTER: [a-zA-Z];

WS: [ \t\r\n] -> skip;