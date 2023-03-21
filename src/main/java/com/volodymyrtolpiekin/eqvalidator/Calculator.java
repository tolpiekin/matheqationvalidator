package com.volodymyrtolpiekin.eqvalidator;

import java.util.Stack;
import static com.volodymyrtolpiekin.eqvalidator.Constants.*;

public class Calculator {
    private final Stack<Double> numbers = new Stack<>();
    private final Stack<Character> operators = new Stack<>();

    public double calculate (String expression) {
        char[] c = expression.toCharArray();
        boolean negativeFlag = true;

        for (int i = 0; i < c.length; i++ ) {

            if (ifCharisDigit(c[i]) || (negativeFlag && c[i] == MINUS_SIGN)) {
                StringBuilder sb = new StringBuilder();
                if (c[i] == MINUS_SIGN) {
                    sb.append(c[i]);
                    i++;
                }
                while (i < c.length && ifCharisDigit(c[i])) {
                    sb.append(c[i]);
                    i++;
                }
                numbers.push(Double.valueOf(sb.toString()));
                i--;
                negativeFlag = false;
            } else if (c[i] == OPENING_BRACE) {
                operators.push(c[i]);
            } else if (c[i] == CLOSING_BRACE) {
                while (operators.peek() != OPENING_BRACE) {
                    runOperator();
                }
                operators.pop();
            } else if (isAnOperator(c[i])) {
                while (!operators.empty() && hasHigherPriority(c[i], operators.peek())) {
                    runOperator();
                }
                operators.push(c[i]);
                negativeFlag = true;
            }
        }

        while (!operators.empty()) {
            runOperator();
        }

        return numbers.pop();
    }

    private static boolean isAnOperator(char c) {
        return c == PLUS_SIGN || c == MINUS_SIGN || c == MULTIPLY_SIGN
                || c == DIVISION_SIGN;
    }

    private void runOperator() {
        double result = 0;
        char operator = operators.pop();
        double d1 = numbers.pop();
        double d2 = numbers.pop();

        if (operator == MULTIPLY_SIGN)
            result = d2*d1;
        else if (operator == DIVISION_SIGN)
            result = d2/d1;
        else if (operator == MINUS_SIGN)
            result = d2-d1;
        else if (operator == PLUS_SIGN)
            result = d2+d1;

        numbers.push(result);
    }

    private boolean hasHigherPriority(char c1, char c2) {
        if ((c2 == OPENING_BRACE) || (c2 == CLOSING_BRACE)) {
            return false;
        }
        return (c1 != MULTIPLY_SIGN && c1 != DIVISION_SIGN) || (c2 != PLUS_SIGN && c2 != MINUS_SIGN);
    }

    private boolean ifCharisDigit(char c) {
        return c == DIGIT_SEPARATOR || (c >= ZERO && c<= NIINE);
    }
}
