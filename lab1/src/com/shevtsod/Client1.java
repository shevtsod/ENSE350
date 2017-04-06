package com.shevtsod;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program client (command line interface)
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Client1 {

    //Enable or disable debugging mode
    public static final boolean IS_DEBUG = true;

	/**
	 * Main program entry point
	 * @param args String arguments from console (unused)
	 */
	public static void main(String[] args) {
        System.out.println("ENSE 350 Lab 1 - Daniel Shevtsov (SID: 200351253)");

        Scanner input = new Scanner(System.in, "UTF-8");
        int nInput = 0;
        boolean correctInput;

        //Get user input and pass the program to the correct method to handle further
        //user input
        do {
            System.out.println(" Enter one of the following:\n" +
                    "  0 - Calculate gcd(a, b)\n" +
                    "  1 - Calculate pulverizer(a, b)\n" +
                    "  2 - Calculate a fraction a/b in lowest terms\n" +
                    "  3 - Exit program\n");

            correctInput = false;
            do {
                System.out.print("\tINPUT: ");
                try {
                    nInput = input.nextInt();
                    if (nInput >= 0 && nInput <= 3)
                        correctInput = true;
                    else
                        System.out.println(" - ERROR: Incorrect input value");
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println(" - ERROR: Incorrect input type");
                    input.nextLine();
                }
            } while (!correctInput);

            //Process input and send the program to the correct method:
            switch(nInput) {
                case 0:
                    clientGcd(input);
                    break;
                case 1:
                    clientPulverizer(input);
                    break;
                case 2:
                    clientFraction(input);
                    break;
            }

        } while(nInput != 3);

        input.close();
        System.out.println("OUTPUT: Program completed successfully");
    }

    /**
     * A client program for gcd(). Allows the user to input values into the gcd()
     * method and outputs the return value
     * @param input A Scanner object to some input stream
     */
	private static void clientGcd(Scanner input) {
	    EuclideanAlgorithm ea = new EuclideanAlgorithm();

        System.out.println("Enter integer a:");
        int a = getIntFromScanner(input);

        System.out.println("Enter integer b:");
        int b = getIntFromScanner(input);

        System.out.println("OUTPUT: gcd(" + a + ", " + b + ") = " + ea.gcd(a, b) + "\n");
    }

    /**
     * A client program for pulverizer(). Allows the user to input values into the pulverizer()
     * method and outputs the return values
     * @param input A Scanner object to some input stream
     */
    private static void clientPulverizer(Scanner input) {
        EuclideanAlgorithm ea = new EuclideanAlgorithm();

        System.out.println("Enter integer a:");
        int a = getIntFromScanner(input);

        System.out.println("Enter integer b:");
        int b = getIntFromScanner(input);

        //Store the output data from the pulverizer temporarily to output
        PulverizerData pd = ea.pulverizer(a, b);

        System.out.println("OUTPUT: gcd(" + a + ", " + b + ") = " + pd.getGcd());
        System.out.println("        s = " + pd.getS());
        System.out.println("        t = " + pd.getT() + "\n");
    }

    /**
     * A client program for Fraction class. Allows the user to input values into a fraction
     * and returns that fraction in lowest terms
     * @param input A Scanner object to some input stream
     */
    private static void clientFraction(Scanner input) {
        System.out.println("Enter integer a:");
        int a = getIntFromScanner(input);

        System.out.println("Enter integer b:");
        int b = getIntFromScanner(input);

        Fraction fraction = new Fraction(a, b).toLowestTerms();

        System.out.println("OUTPUT: a / b  =  "
                + a + " / " + b + "  =  "
                + fraction.getNominator() + " / " + fraction.getDenominator() + "\n");
    }

    /**
     * Helper method for retrieving an integer between INT_MIN and INT_MAX from an input Scanner
     * @param input A Scanner object to some input stream
     * @return Integer from user input
     */
    private static int getIntFromScanner(Scanner input) {
        boolean correctInput = false;
        int nInput = 0;
        correctInput = false;
        do {
            System.out.print("\tINPUT: ");
            try {
                nInput = input.nextInt();
                if (nInput > Integer.MIN_VALUE && nInput < Integer.MAX_VALUE)
                    correctInput = true;
                else
                    System.out.println(" - ERROR: Incorrect input value");
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(" - ERROR: Incorrect input type");
                input.nextLine();
            }
        } while (!correctInput);

        return nInput;
    }
}
