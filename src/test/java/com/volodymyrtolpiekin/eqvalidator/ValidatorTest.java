package com.volodymyrtolpiekin.eqvalidator;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    Validator validator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @org.junit.jupiter.api.Test
    void testSimpleEquationReturnsTrue() {
        assertTrue(validator.validate("2+x=3"));
    }

    @org.junit.jupiter.api.Test
    void testEmptyInputReturnsFalse() {
        assertFalse(validator.validate(""));
    }

    @org.junit.jupiter.api.Test
    void testBracesEqualOpenAndCloseShouldReturnTrue() {
        assertTrue(validator.validate("(1+2)+(x+3)=((1+2)+4)"));
    }

    @org.junit.jupiter.api.Test
    void testBracesNonEqualOpenAndCloseShouldReturnFalse() {
        assertFalse(validator.validate("(1+2+(x+3)=(1+2)+4)"));
    }

    @org.junit.jupiter.api.Test
    void testIfEquastionHasX () {
        assertTrue(validator.validate("1+x=2"));
        assertFalse(validator.validate("1+2=3"));
    }
}