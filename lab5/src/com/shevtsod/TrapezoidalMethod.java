package com.shevtsod;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class TrapezoidalMethod {
	
	public static final double LOWER_BOUND = -10.0;
	public static final double UPPER_BOUND = 10.0;
	
	/**
	 * The method f(x) given in the problem. Returns the value of
	 * the function at a given coordinate x
	 * @param x double value of x parameter to f(x)
	 * @return double value of f(x)
	 */
	public double f(double x) {
		return 2 - 5 * x + 10 * Math.pow(x, 2) + 0.5 * Math.pow(x, 3);
	}
	
	/**
	 * This method calculates the integral of f(x) using the
	 * Trapezoidal Method with n segments.
	 * @param n int number of segments for the trapezoidal method
	 * @return double approximate value of integral 
	 *         between LOWER_BOUND and UPPER_BOUND
	 */
	public double calculateIntegral(int n) {	
		// Get value of h = (b - a) / n
		double h = (UPPER_BOUND - LOWER_BOUND) / n;
		
		// Calculate the inner summation
		double sum = 0;
		for(double innerValue = LOWER_BOUND + h; innerValue < UPPER_BOUND; innerValue += h)
			sum += f(innerValue);
		
		// Calculate the boundary values and sum them
		return (h / 2) * (f(LOWER_BOUND) + 2 * sum + f(UPPER_BOUND));
	}
}
