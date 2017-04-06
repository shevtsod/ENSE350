package com.shevtsod;

/**
 * Class PulverizerData acts as a structure to contain three pieces of information:
 *  - Integer s
 *  - Integer t
 *  - gcd(a, b)
 *  Where gcd(a, b) = sa + tb
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class PulverizerData {
    private int s;
    private int t;
    private int gcd;

    /**
     * Constructor. Creates a new PulverizerData instance.
     * @param s Integer s from sa + tb
     * @param t Integer t from sa + tb
     * @param gcd Integer gcd(a, b) = sa + tb
     */
    PulverizerData(int s, int t, int gcd) {
        this.s = s;
        this.t = t;
        this.gcd = gcd;
    }


    // Getters and setters

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getGcd() {
        return gcd;
    }

    public void setGcd(int gcd) {
        this.gcd = gcd;
    }
}
