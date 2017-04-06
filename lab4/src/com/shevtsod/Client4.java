package com.shevtsod;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Client4 {
    private ScannerWrapper input = new ScannerWrapper();

    /**
     * Main program entry point
     * @param args String arguments from console (unused)
     */
    public static void main(String[] args) {
        System.out.println("ENSE 350 Lab 3 - Daniel Shevtsov (SID: 200351253)");
        System.out.println("Enter \"h\" or \"help\" for a list of commands\n");

        Client4 client = new Client4();
        String command;

        //Loop to take user commands and execute appropriate actions
        do {
            command = client.input.getString().toLowerCase();

            switch(command) {
                case "p1":case "problem 1":
                    client.clientProblem1();
                    break;
                case "p2":case "problem 2":
                    client.clientProblem2();
                    break;
                case "h":case "help":
                    client.clientHelp();
                    break;
                case "q":case "quit":
                    client.clientQuit();
                    break;
                default:
                    System.out.println("ERROR: Unknown command \"" + command + "\". Enter \"help\" for a " +
                            "list of available commands.");
                    break;
            }

            //Simple line break divider
            System.out.println();

        } while(!command.equals("q") && !command.equals("quit"));

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          COMMAND HANDLERS                                            //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A client program for problem 1. Approximates x to an error of <0.01%  using the Newton-Raphson method
     */
    private void clientProblem1() {
        System.out.println("*******************************************************************************");
        System.out.println("* NOTE: For this method, only values greater than -0.5 and less than");
        System.out.println("*       or equal to 10000 should be used. There are two different roots that");
        System.out.println("*       the method can converge to, depending on the initial value.");
        System.out.println("*******************************************************************************");

        System.out.println("Enter initial value for x:");
        double initX = input.getDouble();

        //This object's constructor automatically calls methods to output results to the console
        new Problem1(initX);
    }

    /**
     * A client program for problem 2. Performs both the bisection and secant methods to solve problem 2
     */
    private void clientProblem2() {
        System.out.println("*******************************************************************************");
        System.out.println("* NOTE: For this problem, the only two required bounds are");
        System.out.println("*          lower bound = -1 , upper bound = 0");
        System.out.println("*                          AND");
        System.out.println("*          lower bound = 0 , upper bound = 1");
        System.out.println("*       Any other values have not been tested and may produce unexpected");
        System.out.println("*       behaviour.");
        System.out.println("*       For the secant method, enter two different approximate values for the");
        System.out.println("*       root you wish the algorithm to approximate. (i.e. 1 & 2, -1 & 0, etc.)");
        System.out.println("*******************************************************************************");

        System.out.println("Enter lower limit for Bisection Method: ");
        double initL = input.getDouble();
        System.out.println("Enter upper limit for Bisection Method: ");
        double initU = input.getDouble();
        System.out.println("Enter initial value for x_1 for Secant Method:");
        double x_1 = input.getDouble();
        System.out.println("Enter initial value for x_2 for Secant Method:");
        double x_2 = input.getDouble();

        //This object's constructor automatically calls methods to output results to the console
        new Problem2(initL, initU, x_1, x_2);
    }

    /**
     * Print a formatted list of available commands to the user
     */
    private void clientHelp() {
        System.out.println("LIST OF AVAILABLE COMMANDS:\n" +
                "COMMAND          DESCRIPTION\n" +
                "p1 / problem 1 - Solution to problem 1 (Newton-Raphson Method)\n" +
                "p2 / problem 2 - Solution to problem 2 (Bisection and Secant Methods)\n" +
                "h / help       - Display list of available commands\n" +
                "q / quit       - Quit the program");
    }

    /**
     * Perform final operations needed before quitting the program
     */
    private void clientQuit() {
        System.out.println("INFO: Quitting program");
    }
}
