package com.shevtsod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
class RSAAlgorithmTest {
    private RSAAlgorithm r;

    @Test
    void rsa() {
        //Test different RSA outputs
        r = new RSAAlgorithm(5, 11, 2);
        assertEquals(55, r.getN());
        assertEquals(3, r.getE());
        assertEquals(8, r.getMEnc());
        assertEquals(2, r.getMDec());

        r = new RSAAlgorithm(3491, 4967, 692);
        assertEquals(17339797, r.getN());
        assertEquals(3, r.getE());
        assertEquals(1917745, r.getMEnc());
        assertEquals(692, r.getMDec());
    }

    @Test
    void isPrime() {
        //Test edge values
        assertTrue(RSAAlgorithm.isPrime(1));
        assertTrue(RSAAlgorithm.isPrime(2));
        assertTrue(RSAAlgorithm.isPrime(3));
        assertFalse(RSAAlgorithm.isPrime(4));
        assertTrue(RSAAlgorithm.isPrime(5));
        assertFalse(RSAAlgorithm.isPrime(6));
        assertTrue(RSAAlgorithm.isPrime(7));
        assertFalse(RSAAlgorithm.isPrime(8));

        //Test large values
        assertTrue(RSAAlgorithm.isPrime(172573));
        assertFalse(RSAAlgorithm.isPrime(95612962));
    }

    @Test
    void successiveSquareMod() {
        assertEquals(1, RSAAlgorithm.successiveSquareMod(0, 0, 0));
        assertEquals(1, RSAAlgorithm.successiveSquareMod(0, 0, 1));
        assertEquals(1, RSAAlgorithm.successiveSquareMod(0, 1, 0));
        assertEquals(0, RSAAlgorithm.successiveSquareMod(0, 1, 1));
        assertEquals(1, RSAAlgorithm.successiveSquareMod(1, 0, 0));
        assertEquals(1, RSAAlgorithm.successiveSquareMod(1, 0, 1));
        assertEquals(1, RSAAlgorithm.successiveSquareMod(1, 1, 0));
        assertEquals(0, RSAAlgorithm.successiveSquareMod(1, 1, 1));

        assertEquals(220, RSAAlgorithm.successiveSquareMod(1495, 1229, 1649));
        assertEquals(692, RSAAlgorithm.successiveSquareMod(1917745, 11554227, 17339797));
    }
}