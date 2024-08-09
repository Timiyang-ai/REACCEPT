public static DenseMatrix inverse(double[][] A) {
        if (A.length == A[0].length) {
            LUDecomposition lu = new LUDecomposition(A);
            return lu.inverse();
        } else {
            QRDecomposition qr = new QRDecomposition(A);
            return qr.inverse();
        }
    }