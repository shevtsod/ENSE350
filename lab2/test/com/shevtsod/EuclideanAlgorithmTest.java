package com.shevtsod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class EuclideanAlgorithmTest runs some tests on class EuclideanAlgorithm using JUnit 5
 * @author Daniel Shevtsov (SID: 200351253)
 */
class EuclideanAlgorithmTest {
    private EuclideanAlgorithm ea;
    private PulverizerData pd;

    /**
     * Set up the tests
     */
    @BeforeEach
    void setUp() {
        ea = new EuclideanAlgorithm();
    }

    /**
     * Test method gcd()
     */
    @Test
    void gcd() {
        //Test normal cases
        assertEquals(1, ea.gcd(20, 1));
        assertEquals(10, ea.gcd(20, 10));
        assertEquals(20, ea.gcd(20, 20));
        assertEquals(5, ea.gcd(20, 15));
        assertEquals(4812, ea.gcd(2208708, 4812));

        //Test reverse cases
        assertEquals(10, ea.gcd(10, 20));
        assertEquals(1, ea.gcd(1, 20));
        assertEquals(4, ea.gcd(4, 20));
        assertEquals(4, ea.gcd(8, 20));
        assertEquals(4812, ea.gcd(4812, 2208708));

        //Test 0
        assertEquals(20, ea.gcd(20, 0));
        assertEquals(20, ea.gcd(0, 20));
        assertEquals(0, ea.gcd(0, 0));

        //Test negatives
        assertEquals(-5, ea.gcd(-5, 20));
        assertEquals(-8, ea.gcd(-40, -16));
        assertEquals(-1, ea.gcd(-46, 421));
    }

    /**
     * Test method pulverizer()
     */
    @Test
    void pulverizer() {
        //Test normal cases
        pd = ea.pulverizer(20, 1);
        assertEquals(1, pd.getGcd());
        assertEquals(0, pd.getS());
        assertEquals(1, pd.getT());
        pd = ea.pulverizer(27, 13);
        assertEquals(1, pd.getGcd());
        assertEquals(1, pd.getS());
        assertEquals(-2, pd.getT());

        //Test reverse cases
        pd = ea.pulverizer(46, 421);
        assertEquals(1, pd.getGcd());
        assertEquals(119, pd.getS());
        assertEquals(-13, pd.getT());

        //Test 0
        pd = ea.pulverizer(0, 0);
        assertEquals(0, pd.getGcd());
        assertEquals(1, pd.getS());
        assertEquals(0, pd.getT());
        pd = ea.pulverizer(0, 5);
        assertEquals(5, pd.getGcd());
        assertEquals(0, pd.getS());
        assertEquals(1, pd.getT());
        pd = ea.pulverizer(5, 0);
        assertEquals(5, pd.getGcd());
        assertEquals(1, pd.getS());
        assertEquals(0, pd.getT());

        //Test negatives
        pd = ea.pulverizer(-46, 421);
        assertEquals(-1, pd.getGcd());
        assertEquals(119, pd.getS());
        assertEquals(13, pd.getT());

    }

}