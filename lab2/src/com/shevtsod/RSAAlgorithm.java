package com.shevtsod;

/**
 * Class RSAAlgorithm contains the following features:
 *  - Accepts two prime numbers p and q, and a message m to be encrypted
 *  - Computes and returns the public key, private key, encrypted message, and decrypted message
 *  NOTE: Does not handle large values of m well, values of m > 1000 produce unpredictable results due
 *  to integer overflow
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class RSAAlgorithm {
    //First prime number
    private int p;
    //Second prime number
    private int q;
    //Original user supplied integer message
    private int m;

    //n = pq
    private int n = 0;
    //(e, n) is the public key
    private int e = 0;
    //(d, n) is the private key
    private int d = 0;
    //Message encrypted by the RSA algorithm
    private int mEnc = 0;
    //Message decrypted by the RSA algorithm
    private int mDec = 0;

    private EuclideanAlgorithm ea;

    /**
     * Constructor. Creates a new RSAAlgorithm object with user given input parameters
     * This method assumes that p an q are prime, so they must be checked outside of this method
     * @param p First prime number
     * @param q Second prime number
     * @param m The original message (a
     */
    public RSAAlgorithm(int p, int q, int m) {
        this.p = p;
        this.q = q;
        this.m = m;

        this.n = p * q;
        ea = new EuclideanAlgorithm();

        //Calculate the RSA values based on these parameters
        rsa();
    }

    /**
     * Perform the RSA algorithm. Use getters to get the values of computed values after this
     * method is called.
     */
    private void rsa() {
        calculatePublicKey();
        calculatePrivateKey();
        rsaEncrypt();
        rsaDecrypt();
    }

    /**
     * Calculate the public key based on p and q
     */
    private void calculatePublicKey() {
        //Iterate to a suitable integer such that gcd(e, (p-1)(q-1)) = 1
        e = 2;
        while(ea.gcd(e, (p - 1)*(q - 1)) != 1)
            e++;
    }

    /**
     * Calculate the private key based on the public key
     */
    private void calculatePrivateKey() {
        PulverizerData pd = ea.pulverizer(e, (p -1) * (q - 1));
        d = pd.getS();

        //Choose a positive value over a negative value for d
        if(d < 0)
            d += (p - 1) * (q - 1);
    }

    /**
     * Encrypt the message m using the RSA algorithm
     */
    private void rsaEncrypt() {
        mEnc = successiveSquareMod(m, e, n);
    }

    /**
     * Decrypt the message m using the RSA algorithm
     */
    private void rsaDecrypt() {
        mDec = successiveSquareMod(mEnc, d, n);
    }

    /**
     * Helper function to determine if an integer is a prime number
     * @param x the number to be tested
     * @return boolean restult of the test
     */
    public static boolean isPrime(int x) {
        //Check to see if the number is not 2 and is even (bit 0 is 0)
        if(x > 2 && (x & 1) == 0)
            return false;

        //Test odd values from 3 to x, since the number has been determined to not be a multiple of 2
        for(int i = 3; i * i <= x; i+= 2) {
            if(x % i == 0)
                return false;
        }

        //If reach here, the number must be prime
        return true;
    }

    /**
     * Returns the value of rem(a ^ b, n) using successive squaring for a positive value b
     * @return integer result of a^b % n
     */
    public static int successiveSquareMod(int a, int b, int n) {
        //Return 1 if calculating a^0 or a^b % 0
        if(b == 0 || n == 0) return 1;

        //return value, holds 1 if
        long result = 1;
        long a1 = a;

        //Iterate until b (exponent) becomes 0
        while(b > 0) {
            //
            if(b % 2 == 1)
                result = (result * a1) % n;

            a1 = (a1 * a1) % n;
            b /= 2;
        }

        return (int)(result % n);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        GETTERS AND SETTERS                                           //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }

    public int getD() {
        return d;
    }

    public int getMEnc() {
        return mEnc;
    }

    public int getMDec() {
        return mDec;
    }
}
