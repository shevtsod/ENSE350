package com.shevtsod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Client5 {

    public static final double TRUE_VALUE = 6706.67;

    /**
     * Main program entry point
     *
     * @param args String arguments from console (unused)
     */
    public static void main(String[] args) throws IOException {
        System.out.println("ENSE 350 Lab 5 - Daniel Shevtsov (SID: 200351253)");
        System.out.println("This program integrates a function");
        System.out.println("    f(x) = 2 - 5x + 10x^2 + 0.5x^3");
        System.out.println("from -10 to 10 using the Trapezoidal Method with n segments\n");
        System.out.println("When 0 is entered, the program outputs error for 0 to 1,000");
        System.out.println("and terminates.");

        System.out.println("\n**************************************************\n");

        ScannerWrapper input = new ScannerWrapper();
        TrapezoidalMethod tm = new TrapezoidalMethod();
        
        // Continuously take user input for n until n = 0, then quit program
        int n;        
        do {
        	System.out.println("Enter n: (n >= 0, 0 to quit program)");
        	n = input.getInt(0, Integer.MAX_VALUE);       
        	
        	if(n == 0) break;        	
        	
        	System.out.println("OUTPUT: " + tm.calculateIntegral(n) + "\n");
        } while(n != 0);
        
        //Output to a file values of the integral from 1 to 1,000
        System.out.println("\nOutputting error for n = 1 to n = 1,000 to file 'data.csv'...");
        BufferedWriter fout = new BufferedWriter(new FileWriter(new File("data.csv")));

        fout.write("n,error\n");
        for(n = 1; n < 1000; n++) {
            fout.write(n + "," + getPercentRelativeError(TRUE_VALUE, tm.calculateIntegral(n)) + "\n");
        }

        fout.close();

        System.out.println("\nProgram completed successfully");
    }

    /**
     * Helper method to calculate the true percent relative error
     * @param trueValue double true value
     * @param approxValue double approximate value
     * @return double true percent relative error
     */
    public static double getPercentRelativeError(double trueValue, double approxValue) {
        return (trueValue - approxValue) / trueValue * 100;
    }
}
