package com.shevtsod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class EuclideanAlgorithmTest runs some tests on class EuclideanAlgorithm using JUnit 5
 * @author Daniel Shevtsov (SID: 200351253)
 */
class FractionTest {
    private Fraction fraction;

    /**
     * Test method toLowestTerms()
     */
    @Test
    void toLowestTerms() {
        //Nominator > Denominator case
        fraction = new Fraction(10, 2).toLowestTerms();
        assertEquals(5, fraction.getNominator());
        assertEquals(1, fraction.getDenominator());

        //Denominator > Nominator case
        fraction = new Fraction(26, 78).toLowestTerms();
        assertEquals(1, fraction.getNominator());
        assertEquals(3, fraction.getDenominator());

        //Zero nominator case
        fraction = new Fraction(0, 1001).toLowestTerms();
        assertEquals(0, fraction.getNominator());
        assertEquals(1, fraction.getDenominator());

        //Zero denominator case
        //Assert that an exception is thrown when trying to set a negative denominator
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Cannot have denominator = 0");
        });

        //Negative nominator case
        fraction = new Fraction(-32, 6).toLowestTerms();
        assertEquals(-16, fraction.getNominator());
        assertEquals(3, fraction.getDenominator());

        //Negative denominator case
        fraction = new Fraction(32, -6).toLowestTerms();
        assertEquals(-16, fraction.getNominator());
        assertEquals(3, fraction.getDenominator());

        //Negative nominator and denominator case
        fraction = new Fraction(-32, -6).toLowestTerms();
        assertEquals(16, fraction.getNominator());
        assertEquals(3, fraction.getDenominator());
    }

}