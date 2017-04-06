package com.shevtsod;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Client2 {
    private ScannerWrapper input;

    /**
     * Constructor. Creates a new Client2 object
     */
    private Client2() {
        input = new ScannerWrapper();
    }

    /**
     * Main program entry point
     * @param args String arguments from console (unused)
     */
    public static void main(String[] args) {
        System.out.println("ENSE 350 Lab 2 - Daniel Shevtsov (SID: 200351253)");
        System.out.println("Enter \"help\" for list of commands\n");

        Client2 client = new Client2();
        String command;

        //Loop to take user commands and execute appropriate actions
        do {
            command = client.input.getString().toLowerCase();

            switch(command) {
                case "g":case "gcd":
                    client.clientGcd();
                    break;
                case "p":case "pulv":
                    client.clientPulverizer();
                    break;
                case "r":case "rsa":
                    client.clientRSA();
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
     * A client program for gcd(). Allows the user to input values into the gcd()
     * method and outputs the return value
     */
    private void clientGcd() {
        EuclideanAlgorithm ea = new EuclideanAlgorithm();

        System.out.println("Enter integer a:");
        int a = input.getInt();

        System.out.println("Enter integer b:");
        int b = input.getInt();

        System.out.println("OUTPUT: gcd(" + a + ", " + b + ") = " + ea.gcd(a, b) + "\n");
    }

    /**
     * A client program for pulverizer(). Allows the user to input values into the pulverizer()
     * method and outputs the return values
     */
    private void clientPulverizer() {
        EuclideanAlgorithm ea = new EuclideanAlgorithm();

        System.out.println("Enter integer a:");
        int a = input.getInt();

        System.out.println("Enter integer b:");
        int b = input.getInt();

        //Store the output data from the pulverizer temporarily to output
        PulverizerData pd = ea.pulverizer(a, b);

        System.out.println("OUTPUT: gcd(" + a + ", " + b + ") = " + pd.getGcd());
        System.out.println("        s = " + pd.getS());
        System.out.println("        t = " + pd.getT() + "\n");
    }

    /**
     * A client program for rsa(). Allows the user to input values into the rsa()
     * method and outputs the return values
     */
    private void clientRSA() {
        int p;
        do {
            System.out.println("Enter first prime p:");
            p = input.getInt();
        } while(!RSAAlgorithm.isPrime(p));

        int q;
        do {
            System.out.println("Enter second prime q:");
            q = input.getInt();
        } while(!RSAAlgorithm.isPrime(q) || (p == q));

        System.out.println("Enter an integer message m:");
        int m = input.getInt();

        RSAAlgorithm r = new RSAAlgorithm(p, q, m);

        //The constructor calls the method to perform the RSA algorithm, the getters contain the
        //computed RSA values
        System.out.println("RSA ALGORITHM OUTPUT:\n" +
                " Public key: (" + r.getE() + ", " + r.getN() + ")\n" +
                " Private key: (" + r.getD() + ", " + r.getN() + ")\n" +
                " Original message: " + r.getM() + "\n" +
                " Encrypted message: " + r.getMEnc() + "\n" +
                " Decrypted message: " + r.getMDec());

        System.out.println( (r.getM() == r.getMDec()) ? " MESSAGES MATCH" : " MESSAGES DO NOT MATCH");

    }

    /**
     * Print a formatted list of available commands to the user
     */
    private void clientHelp() {
        System.out.println("LIST OF AVAILABLE COMMANDS:\n" +
                "g / gcd  - Calculate gcd(a, b)\n" +
                "p / pulv - Calculate pulverizer(a, b) = sa + tb\n" +
                "r / rsa  - Perform the RSA algorithm using two primes p, q and a message m\n" +
                "h / help - Display list of available commands\n" +
                "q / quit - Quit the program");
    }

    /**
     * Perform final operations needed before quitting the program
     */
    private void clientQuit() {
        System.out.println("INFO: Quitting program");
    }
}
