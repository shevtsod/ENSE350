package com.shevtsod;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Problem2 {
    private NumberFormat percent = new DecimalFormat("#,##0.00'%'");

    /**
     * Constructor for Problem2 object, calls any inner methods of the object
     * @param lLimit double initial lower limit for Bisection method
     * @param uLimit double initial upper limit for Bisection method
     * @param x_1 double initial value of x_1 for Secant method
     * @param x_2 double initial value of x_2 for Secant method
     */
    public Problem2(double lLimit, double uLimit, double x_1, double x_2) {
        bisectionMethod(lLimit, uLimit);
        secantMethod(x_1, x_2);

    }

    /**
     * This method implements the Bisection method to estimate the value of x for a method F(x).
     * The method iterates the upper and lower bound until they converge on the solution if it exists.
     * @param lLimit double initial lower limit to use in the algorithm
     * @param uLimit double initial upper limit to use in the algorithm
     */
    public void bisectionMethod(double lLimit, double uLimit) {
        //Test if lLimit > uLimit
        if(lLimit >= uLimit) {
            System.out.println("Invalid bounds - lower bound must be smaller than upper bound");
            return;
        }
        //Test if there is a solution between these points
        if(F(lLimit) * F(uLimit) >= 0) {
            System.out.println("  The Bisection Method cannot determine a root between these bounds.\n");
            return;
        }

        double error;
        int i = 1;

        System.out.format("%4s%20s%20s%16s%n", "#", "LOWER", "UPPER", "ERROR");

        do {
            //Calculate the middle point between the two bounds
            double middle = (lLimit + uLimit) / 2.0;

            //Calculate the error for this iteration
            error = AbsRelApproxError(uLimit, lLimit);

            //Output current iteration values
            System.out.format("%4d%20.10f%20.10f%16f%n", i++, lLimit, uLimit, error);

            //Update either lower or upper bound
            //If f(lower) * f(middle) < 0, set upper_limit = middle
            if(F(lLimit) * F(middle) < 0)
                uLimit = middle;
            //If f(lower) * f(middle) > 0, set lower_limit = middle
            else
                lLimit = middle;

        } while(error >= 0.01);

        System.out.println("  FINAL RESULT FOR BISECTION METHOD:\n" +
                "  The value is between " + lLimit + " and " + uLimit + "\n");

    }

    /**
     * This method implements the Secant method to estimate the value of x for a method F(x).
     * The method approximates x using two initial guesses and iterating to a closer approximation
     * The formula used by the method is as follows:
     *      x = x_1 - (f(x_1)*(x_1 - x_2)) / (f(x_1) - f(x_2))
     * NOTE: This method may not always converge
     * @param x_1 double first initial value for x_1 to use in the algorithm
     * @param x_2 double second initial value for x_2 to use in the algorithm
     */
    public void secantMethod(double x_1, double x_2) {
        //Test if x_1 = x_2
        if(x_1 == x_2) {
            System.out.println("Invalid values for secant method - x_1 and x_2 cannot be equal");
            return;
        }

        double x;
        double error;
        int i = 1;

        System.out.format("%4s%20s%16s%n", "#", "X", "ERROR");

        do {
            //Calculate x for this iteration
            x = x_1 - (F(x_1)*(x_1 - x_2)) / (F(x_1) - F(x_2));

            //Calculate the error for this iteration
            error = AbsRelApproxError(x_1, x_2);

            //Output current iteration values
            System.out.format("%4d%20.10f%16f%n", i++, x, error);

            //Update x_1 and x_2 for next iteration
            x_2 = x_1;
            x_1 = x;
        } while(error >= 0.01);

        System.out.println("  FINAL RESULT FOR SECANT METHOD:\n" +
                "  The value is approximately " + x + "\n");

    }

    /**
     * Calculates F(x) where F is the function given in the problem
     * @param x double parameter to the function
     * @return double result of the function
     */
    private double F(double x) {
        return 230 * Math.pow(x, 4) + 18 * Math.pow(x, 3) + 9 * Math.pow(x, 2) - 221 * x  - 9;
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
