package com.shevtsod;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Problem1 {
    //Constant definitions
    public static final double E0 = 8.85E-12;
    public static final double q = 2E-5;
    public static final double Q = 2E-5;
    public static final double F = 1;
    public static final double a = 0.9;

    /**
     * Constructor for Problem1 object, calls any inner methods of the object
     * @param initX double initial value of x from user input to pass to newtonRaphson method
     */
    public Problem1(double initX) {
        newtonRaphson(initX);
    }

    /**
     * This method implements the Newton Raphson method to estimate the value of x for a method F(x)
     * using iteration with the formula
     *      x_i+1 = x_i - (f(x_i) / f'(x_i))
     * Where
     *   x_i = initial guess
     *   x_i+1 = next guess
     *   f(x_i) = value of f with parameter x_i
     *   f'(x_i) = value of derivative of f with parameter x_i
     * This method outputs the current value of x at each iteration and stops when the absolute relative
     * approximate error is <0.01%
     * @param initX double initial value of x to use in the algorithm
     */
    private void newtonRaphson(double initX) {
        double currX;
        double prevX = initX;
        double error;

        NumberFormat percent = new DecimalFormat("#,##0.00'%'");

        //Accumulator to keep track of current iteration
        int i = 1;

        //Output initial value of x
        System.out.format("%4s%20s%16s%n", "#", "X", "ERROR");
        System.out.format("%4d%20.10f%n", i++, prevX);

        //Iterate until the absolute relative approximate error is less than 0.01%
        do {
            //Calculate the next value of x
            currX = prevX - (F(prevX) / Fd(prevX));

            //Calculate the absolute relative approximate error
            error = AbsRelApproxError(currX, prevX);

            //Output the values for this iteration
            System.out.format("%4d%20.10f%16s%n", i++, currX, percent.format(error));

            //Set current x to previous x for next iteration, increment accumulator
            prevX = currX;

        } while(error >= 0.01);

    }

    /**
     * Calculates F(x) where F is the function given in the problem
     * @param x double parameter to the function
     * @return double result of the function
     */
    private double F(double x) {
        double nominator = (q * Q * x);
        double denominator = (4 * Math.PI * E0) * Math.pow((Math.pow(x, 2) + Math.pow(a, 2)), (3 / 2.0));
        return (nominator / denominator) - F;
    }

    /**
     * Calculates Fd(x) where Fd is the derivative of the function F given in the problem (Fd = dF/dx)
     * @param x double parameter to the function
     * @return double result of the function
     */
    private double Fd(double x) {
        double nominator = q * Q * (Math.pow(a, 2) - 2 * Math.pow(x, 2));
        double denominator = 4 * Math.PI * E0 * Math.pow((Math.pow(a, 2) + Math.pow(x, 2)), (5 / 2.0));
        return (nominator / denominator) - F;
    }

    /**
     * Helper method to calculate the absolute relative approximate error |e_a|% as given by
     *      |e_a|% = ((curr - prev) / curr) * 100%
     * @param curr double current value
     * @param prev double previous value
     * @return double absolute relative approximate error result
     */
    private double AbsRelApproxError(double curr, double prev) {
        return Math.abs(((curr - prev) / curr)) * 100;
    }
}
