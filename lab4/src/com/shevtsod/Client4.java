package com.shevtsod;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Client4 {
    /**
     * Main program entry point
     *
     * @param args String arguments from console (unused)
     */
    public static void main(String[] args) {
        System.out.println("ENSE 350 Lab 4 - Daniel Shevtsov (SID: 200351253)");
        System.out.println("\nTesting LU decomposition program with given matrix");
        System.out.println("    3x_1  - 2x_2 + x_3  = -10");
        System.out.println("    2x_1  + 6x_2 - 4x_3 =  44");
        System.out.println("    -8x_1 - 2x_2 + 5x_3 = -26\n");
        System.out.println("This equation is solved using LU decomposition following:");
        System.out.println("    A * X = B   (A = coefficient matrix, X = solution vector, B = constants vector)");
        System.out.println("    A = L * U   (decompose A into L and U)");
        System.out.println("    B = L * Z   (solve for Z)");
        System.out.println("    Z = U * X   (solve for X)");

        System.out.println("\n**************************************************\n");

        Matrix matrixA, matrixL, matrixU, matrixB, matrixZ, matrixX, matrixLU;

        //Create a new matrix A that corresponds to the system of equations
        double[][] a = {
                {3, -2, 1},
                {2, 6, -4},
                {-8, -2, 5}
        };
        matrixA = new Matrix(a, 3, 3);

        //Create the constants vector B
        double[][] b = {{-10}, {44}, {-26}};
        matrixB = new Matrix(b, 3, 1);

        System.out.println("MATRIX A:");
        Matrix.print(matrixA);
        System.out.println("\nVECTOR B:");
        Matrix.print(matrixB);

        //Perform LU decomposition and extract L and U matrices from the returned LUDecomposition object
        System.out.println("\nPerforming LU Decomposition...\n");
        Matrix.LUDecomposition lu = matrixA.getLUDecomposition();
        matrixL = lu.L;
        matrixU = lu.U;

        System.out.println("MATRIX L:");
        Matrix.print(matrixL);
        System.out.println("\nMATRIX U:");
        Matrix.print(matrixU);

        System.out.println("\nTesting that L * U = A");
        System.out.println("\nMatrix L * U: ");
        matrixLU = matrixL.multiply(matrixU);
        Matrix.print(matrixLU);

        System.out.println("\nL * U == A? " + (matrixLU.equals(matrixA) ? "TRUE" : "FALSE"));

        System.out.println("\nSolving B = L * Z for Z");
        matrixZ = Matrix.solveSystemByForwardSubstitution(matrixB, matrixL);

        System.out.println("\nVECTOR Z:");
        Matrix.print(matrixZ);

        System.out.println("\nSolving Z = U * X for X");
        matrixX = Matrix.solveSystemByBackwardSubstitution(matrixZ, matrixU);

        System.out.println("\nVECTOR X (SOLUTIONS):");
        Matrix.print(matrixX);

        System.out.println("\nProgram completed successfully");
    }

}
