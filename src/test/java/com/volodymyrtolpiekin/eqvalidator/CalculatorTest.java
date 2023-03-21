package com.volodymyrtolpiekin.eqvalidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void passingSimpleExpressionShouldReturnThree() {
        assertEquals(3.0, calculator.calculate("1+2"));
    }

    @Test
    void calculateMoreComplicatedExpression() {
        assertEquals(13.0, calculator.calculate("(1+2)*3+4"));
    }

    @Test
    void passingExpression() {
        assertEquals(-1.8, calculator.calculate("(((1+2)-4*3)/5)"));
    }

    @Test
    void passingExpressionOther() {
        assertEquals(3.4, calculator.calculate("1.0+2.4"));
    }

    @Test
    void testNegativeNumbersPresent() {
        assertEquals(-2.2, calculator.calculate("(((1+-2)-4*-3)/-5)"));
    }
}