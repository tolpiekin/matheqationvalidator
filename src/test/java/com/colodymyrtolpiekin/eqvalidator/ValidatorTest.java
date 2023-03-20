package com.colodymyrtolpiekin.eqvalidator;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    Validator validator;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @org.junit.jupiter.api.Test
    void TestSimpleEquationReturnsTrue() {
        assertTrue(validator.validate("2+x=3"));
    }

    @org.junit.jupiter.api.Test
    void TestEmptyInputReturnsFalse() {
        assertFalse(validator.validate(""));
    }

    @org.junit.jupiter.api.Test
    void checkSolution() {

    }
}