public static double[][] solve(double[][] A, double[][] B) {
        if (A.length == A[0].length) {
            LUDecomposition lu = new LUDecomposition(A);
            lu.solve(B);
            return B;
        } else {
            double[][] X = new double[A[0].length][B[0].length];
            QRDecomposition qr = new QRDecomposition(A);
            qr.solve(B, X);
            return X;
        }
    }