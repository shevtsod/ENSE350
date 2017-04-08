package com.shevtsod;

import java.util.Arrays;

/**
 * @author Daniel Shevtsov (SID: 200351253)
 */
public class Matrix {
    private double[][] data;
    private int rows;
    private int cols;


    public Matrix(double[][] data, int rows, int cols) {
        this.data = data;
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * Compare between two Matrix objects for equality
     * @param obj the second Matrix object to compare to
     * @return result of comparison (true if equal)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        else if (obj == null)
            return false;
        else if (obj instanceof Matrix) {
            boolean result = true;

            if(this.cols != ((Matrix) obj).cols) result = false;
            if(this.rows != ((Matrix) obj).cols) result = false;

            for(int i = 0; i < this.rows; i++)
                for(int j = 0; j < this.cols; j++)
                    if(this.data[i][j] != ((Matrix) obj).data[i][j])
                        return false;
            return result;
        }
        return false;
    }

    /**
     * This method takes a square matrix of arbitrary dimensions and returns the
     * triangular matrices L and U (both inside a single object LUDecomposition containing
     * a Matrix L and a Matrix U)
     * @return LUDecomposition of the matrix (LUDecomposition is an object that contains L and U Matrix objects)
     */
    public LUDecomposition getLUDecomposition() {
        //Ensure that a is an MxM matrix
        if(rows != cols)
            throw new IllegalArgumentException("Matrix is not square");

        //Matrix L
        double[][] l = new double[rows][cols];
        //Matrix U
        double[][] u = new double[rows][cols];
        //Factors to remember from matrix U to use for constructing matrix L
        double[][] factors = new double[rows][cols];

        //Computer matrix U
        //U is initially the same as the original matrix, so copy this matrix into U
        for(int i = 0; i < u.length; i++)
            u[i] = Arrays.copyOf(this.data[i], this.data[i].length);

        int colIndex;
        int numFactors = 1;

        //Start at 2nd row (index 1) and go through each row
        for(int i = 1; i < rows; i++) {
            colIndex = 0;
            //On each row, the number of factors in that row increases by 1
            for(int j = 0; j < numFactors; j++) {
                factors[i][j] = u[i][colIndex] / u[colIndex][colIndex];

                //Subtract this row times the factor minus the row of that factor
                for(int k = 0; k < cols; k++) {
                    u[i][k] -= factors[i][j] * u[colIndex][k];
                }

                colIndex++;
            }
            numFactors++;
        }

        //Compute matrix L
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                //Lower triangular elements are the factors from matrix U
                if(i > j)
                    l[i][j] = factors[i][j];
                //Diagonal elements are 1s
                else if(i == j)
                    l[i][j] = 1;
                //Lower triangular matrix, so top triangle is filled with 0s
                else
                    l[i][j] = 0;
            }
        }

        Matrix matrixL = new Matrix(l, rows, cols);
        Matrix matrixU = new Matrix(u, rows, cols);
        return new LUDecomposition(matrixL, matrixU);
    }

    /**
     * Multiply this Matrix of size MxN by a Matrix B of size NxS
     * @param B Matrix to multiply this Matrix by
     * @return MxS result Matrix
     */
    public Matrix multiply(Matrix B) {
        if(this.cols != B.rows)
            throw new IllegalArgumentException("Illegal Matrix sizes for multiplication");

        double[][] result = new double[this.rows][B.cols];

        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < B.cols; j++) {
                double sum = 0;
                for(int k = 0; k < this.cols; k++)
                    sum += this.data[i][k] * B.data[k][j];
                result[i][j] = sum;
            }
        }

        return new Matrix(result, this.rows, B.cols);
    }

    /**
     * This method takes a vector (Mx1 Matrix) B and an MxM lower triangular Matrix L and solves the system
     * @param matrixB Mx1 Matrix or vector
     * @param matrixL MxM lower triangular Matrix
     * @return 1xM solutions Matrix or vector
     */
    public static Matrix solveSystemByForwardSubstitution(Matrix matrixB, Matrix matrixL) {
        //Store matrix data in convenient variable names for the algorithm
        int m = matrixB.rows;
        double[][] Z = new double[m][1];
        double[][] B = matrixB.data;
        double[][] L = matrixL.data;

        //Forward substitute to create matrix Z
        for(int i =0; i < m; i++) {
            Z[i][0] = B[i][0];
            for(int j = 0; j < i; j++)
                Z[i][0] -= L[i][j] * Z[j][0];
            Z[i][0] /= L[i][i];
        }

        return new Matrix(Z, m, 1);
    }

    /**
     * This method takes a vector (Mx1 Matrix) Z and an MxM upper triangular Matrix U and solves the system
     * @param matrixZ Mx1 Matrix or vector
     * @param matrixU MxM upper triangular Matrix
     * @return 1xM solutions Matrix or vector
     */
    public static Matrix solveSystemByBackwardSubstitution(Matrix matrixZ, Matrix matrixU) {
        //Store matrix data in convenient variable names for the algorithm
        int m = matrixZ.rows;
        double[][] X = new double[m][1];
        double[][] Z = matrixZ.data;
        double[][] U = matrixU.data;

        //Backward substitute to create matrix X
        for(int i = m - 1; i >= 0; i--) {
            X[i][0] = Z[i][0];
            for(int j = i + 1; j < m; j++)
                X[i][0] -= U[i][j] * X[j][0];
            X[i][0] /= U[i][i];
        }

        return new Matrix(X, m, 1);
    }

    /**
     * Formats and outputs the matrix a to console
     * @param a Matrix to output to console
     */
    public static void print(Matrix a) {
        if(a == null)
            throw new NullPointerException("Null Matrix argument");

        for(int i = 0; i < a.rows; i++) {
            for(int j = 0; j < a.cols; j++) {
                System.out.printf("%10.3f", a.data[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Helper class to return L and U matrices in LU decomposition
     */
    public static class LUDecomposition {
        public Matrix L;
        public Matrix U;

        public LUDecomposition(Matrix L, Matrix U) {
            this.L = L;
            this.U = U;
        }
    }
}
