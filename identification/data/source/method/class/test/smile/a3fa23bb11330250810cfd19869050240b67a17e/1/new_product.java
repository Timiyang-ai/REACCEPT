public static DenseMatrix solve(double[][] A, double[][] B) {
        DenseMatrix b = new ColumnMajorMatrix(B);
        DenseMatrix X = new ColumnMajorMatrix(A[0].length, B[0].length);
        if (A.length == A[0].length) {
            LUDecomposition lu = new LUDecomposition(A);
            lu.solve(b, X);
        } else {
            QRDecomposition qr = new QRDecomposition(A);
            qr.solve(b, X);
        }

        return X;
    }