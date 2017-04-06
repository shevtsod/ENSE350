package com.shevtsod;

/**
 * Class EuclideanAlgorithm contains the following features:
 *  - A recursive method that calculates gcd(a, b) using the Euclidean algorithm
 *  - A recursive method that calculates gcd(a, b), s, and t using the Extended Euclidean algorithm
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class EuclideanAlgorithm {

    /**
     * Calculates the gcd of two integers a and b recursively following the Euclidean algorithm:
     *      gcd(a, b) = gcd(b, rem(a, b))
     * @param a First integer
     * @param b Second integer
     * @return The greatest common divisor of a and b
     */
	public int gcd(int a, int b) {
	    //Base case
	    if(b == 0)
	        return a;
	    //Call gcd() recursively as gcd(b, rem(a, b))
	    else
            return gcd(b, a % b);
	}

    /**
     * Calculates s, t, and the gcd of two integers a and b recursively following the Extended Euclidean
     * algorithm:
     *      gcd(a, b) = sa + tb
     * @param a First integer
     * @param b Second integer
     * @return PulverizerData object containing s, t, and gcd(a, b)
     */
	public PulverizerData pulverizer(int a, int b) {
	    //Next s and t (r = b)
        //b = (0)a + (1)b -> s = 0, t = 1
	    int ns = 0, nt = 1;
	    //Current s and t (r = a)
        //a = (1)a + (0)b -> ps = 1, pt = 0
        int s = 1, t = 0;

        int q, temp;

	    //Get s and t iteratively using the formula r = x - qy, where q is the quotient and r is the remainder
	    //Stop iterating when integer b becomes 0 (same as the recursive method termination case)
	    while(b != 0) {
            //Get the quotient of a and b
            q = a / b;

            //Calculate (next s) = s - q * (next s)
            //Store (next s) before this calculation into s for the next iteration
            temp = ns;
            ns = s - q * ns;
            s = temp;

            //Calculate (next t) = t - q * (next t)
            //Store (next t) before this calculation into s for the next iteration
            temp = nt;
            nt = t - q * nt;
            t = temp;

            //Calculate gcd(a, b) = gcd(b, rem(a, b)) iteratively
            temp = a;
            a = b;
            b = temp % a;
        }

	    //Store s, t, and gcd (stored in a) in a new PulverizerData object to return
	    return new PulverizerData(s, t, a);
    }
}
