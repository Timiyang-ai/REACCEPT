public static double[][] inverse(double[][] A) {
        double[][] inv = eye(A[0].length, A.length);

        if (A.length == A[0].length) {
            LUDecomposition lu = new LUDecomposition(A);
            lu.solve(inv);
        } else {
            QRDecomposition qr = new QRDecomposition(A);
            qr.solve(inv);
        }

        return inv;
    }