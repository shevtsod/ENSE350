package com.shevtsod;

/**
 * Class fraction contains a nominator and a denominator, and allows the calculation of
 * the nominator and denominator in lowest terms using the Euclidean algorithm.
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Fraction {
    private int nominator;
    private int denominator;

    /**
     * Constructor. Creates a new Fraction
     * @param nom The integer nominator of the fraction
     * @param dnm The integer denominator of the fraction
     */
    public Fraction(int nom, int dnm) {
        nominator = nom;

        //Prevent division by 0
        if(dnm == 0)
            throw new IllegalArgumentException("Cannot have denominator = 0");
        else
            denominator = dnm;
    }

    /**
     * Reduces the nominator and denominator to their lowest terms
     * @return This Fraction object for convenience
     */
    public Fraction toLowestTerms() {

        //Get the gcd of the nominator and denominator
        EuclideanAlgorithm ea = new EuclideanAlgorithm();
        int gcd = ea.gcd(nominator, denominator);

        //Simplify the nominator and denominator by reducing both by their gcd
        nominator /= gcd;
        denominator /= gcd;

        //Prefer to have the negative on the nominator
        if(nominator > 0 && denominator < 0) {
            nominator *= -1;
            denominator *= -1;
        }

        return this;
    }


    //Getters and setters

    public int getNominator() {
        return nominator;
    }

    public void setNominator(int nominator) {
        this.nominator = nominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        //Prevent division by 0
        if(denominator == 0)
            throw new IllegalArgumentException("Cannot have denominator = 0");
        else
            this.denominator = denominator;
    }
}
