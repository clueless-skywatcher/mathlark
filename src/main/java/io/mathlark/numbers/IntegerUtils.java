package io.mathlark.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.mathlark.parser.expressions.IExpression;
import io.mathlark.parser.expressions.ListExpression;
import io.mathlark.parser.expressions.NumericExpression;

public class IntegerUtils {
    public static List<Integer> digits(int num) {
        if (num == 0) {
            return List.of(0);
        }

        List<Integer> digits = new ArrayList<>();
        while (num > 0) {
            digits.add(num % 10);
            num = num / 10;
        }
        
        Collections.reverse(digits);
        return digits;
    }

    public static ListExpression digits(NumericExpression num) {
        List<Integer> digs = digits((int) num.getVal());
        List<IExpression> result = new ArrayList<>();

        for (int dig: digs) {
            result.add(new NumericExpression(dig));
        }
        return new ListExpression(result);
    }

    public static int digitSum(int num) {
        List<Integer> digits = digits(num);
        int result = 0;
        for (int dig: digits) {
            result += dig;
        }

        return result;
    }

    public static NumericExpression digitSum(NumericExpression num) {
        int digitSum = digitSum((int) num.getVal());
        return new NumericExpression(digitSum);
    }

    public static void main(String[] args) {
        System.out.println(digits(new NumericExpression(121242345)));
        System.out.println(digitSum(new NumericExpression(121242345)));
    }
}
