package com.colodymyrtolpiekin.eqvalidator;

public class Validator {
    private String leftSide;
    private String rightSide;

    public boolean validate(String inputToValidate) {
        return splitSides(inputToValidate);
    }

    public boolean checkSolution(int n) {
        return true;
    }

    private boolean splitSides(String inputToValidate) {
        long equalSignsCount = inputToValidate.chars().filter(c -> c == '=').count();
        if (equalSignsCount == 1) {
            int index = inputToValidate.indexOf('=');
            leftSide = inputToValidate.substring(0, index);
            rightSide = inputToValidate.substring(index + 1);
            System.out.println(leftSide);
            System.out.println(rightSide);
            return true;
        } else
            return false;
    }
}
