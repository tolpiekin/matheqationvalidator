package com.colodymyrtolpiekin.eqvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.colodymyrtolpiekin.eqvalidator.Constants.*;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final Validator validator = new Validator();
    public void start() {
        mainMenu();
    }

    private void mainMenu() {
        boolean isRunning = true;

        while(isRunning) {
            String inputToValidate = getEquation();

            if (inputToValidate.equals("x"))
                isRunning = false;
            else {
                if (validator.validate(inputToValidate)) {
                    System.out.println(EQUATION_IS_VALID);
                    List<Integer> roots = getRoots();
                }
            }
        }
    }

    private List<Integer> getRoots() {
        boolean nextRoot = true;
        List<Integer> roots = new ArrayList<>();
        while(nextRoot) {
            System.out.println(ENTER_SOLUTION);
            String input = sc.next();
            if (input.equals("x"))
                nextRoot = false;
            else {
                int n = Integer.valueOf(input);
                if (validator.checkSolution(n)) {
                    System.out.println(SOLUTION_IS_VALID);
                    roots.add(n);
                } else {
                    System.out.println(SOLUTION_IS_INVALID);
                }
            }
        }
        return roots;
    }

    private String getEquation() {
        System.out.println(ENTER_EQUATION);
        return sc.next();
    }
}
