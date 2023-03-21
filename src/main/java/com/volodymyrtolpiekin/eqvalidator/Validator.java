package com.volodymyrtolpiekin.eqvalidator;

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

    public boolean checkSolution(double d) {
        Calculator calculator = new Calculator();
        String right = rightSide.replace("x", String.valueOf(d));
        String left = leftSide.replace("x", String.valueOf(d));
        return calculator.calculate(left) == calculator.calculate(right);
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
}
