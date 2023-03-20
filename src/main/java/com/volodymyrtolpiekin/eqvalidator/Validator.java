package com.volodymyrtolpiekin.eqvalidator;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private String leftSide;
    private String rightSide;

    public boolean validate(String inputToValidate) {
        if (!splitSides(inputToValidate))
            return false;
        if (equationHasX())
            return false;
        if (howManyTimes(leftSide, '(') != howManyTimes(leftSide, ')') ||
        howManyTimes(rightSide, '(') != howManyTimes(rightSide, ')'))
            return false;

        return true;
    }

    public boolean checkSolution(int n) {
        return evaluateInsideBraces(leftSide, n) == evaluateInsideBraces(rightSide, n);
    }

    private boolean equationHasX() {
        return howManyTimes(leftSide, 'x') + howManyTimes(rightSide, 'x') == 0;
    }

    private boolean splitSides(String inputToValidate) {

        if (howManyTimes(inputToValidate, '=') == 1) {
            int index = inputToValidate.indexOf('=');
            leftSide = inputToValidate.substring(0, index);
            rightSide = inputToValidate.substring(index + 1);
            return true;
        } else
            return false;
    }

    private long howManyTimes (String string, char character) {
        return string.chars().filter(c -> c == character).count();
    }

    private double evaluateInsideBraces(String expression, double x) {
        String newExpression = "";
        if (!expression.contains("(")) {
            calculate(expression, x);
        } else {
            int count = 0;
            int openIndex = 0;
            int closeIndex = 0;
            for (int i = 0; i < expression.length(); i ++) {
                if (expression.charAt(i) == '(') {
                    if (count == 0 && openIndex == 0) {
                        openIndex = i;
                    }
                    count++;
                } else if (expression.charAt(i) == ')') {
                    if (count == 1 && closeIndex == 0) {
                        closeIndex = i;
                    }
                    count--;
                }
            }
            newExpression = expression.substring(0, openIndex) +
                    evaluateInsideBraces(expression.substring(openIndex + 1, closeIndex), x) +
                    expression.substring(closeIndex +1);
        }
        return calculate(newExpression, x);
    }

    private double calculate(String expression, double x) {
        List<String> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        String acc = "";
        for (char c: expression.toCharArray()) {
            if (c == '-')
                acc = acc + "-";
            else if (c != '*' && c != '/' && c != '+')
                acc = acc + c;
            else if (c == 'x')
                acc = acc + x;
            else {
                numbers.add(acc);
                operators.add("" + c);
                acc = "";
            }
        }
        double result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            double currentNumber = Double.valueOf(numbers.get(i));
            if (i == 0)
                result = currentNumber;
            else {
                if (operators.get(i) == "+") {
                    result = result + currentNumber;
                } else if (operators.get(i) =="-") {
                    result = result - currentNumber;
                } else if (operators.get(i) =="*") {
                    result = result * currentNumber;
                } else if (operators.get(i) =="/") {
                    result = result / currentNumber;
                }

            }
        }
        return result;
    }
}
