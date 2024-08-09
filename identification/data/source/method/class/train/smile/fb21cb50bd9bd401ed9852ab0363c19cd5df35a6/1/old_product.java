public void solve(double[][] B, double[][] X) {
        if (!full) {
            throw new IllegalStateException("This is not a FULL singular value decomposition.");
        }

        if (B.length != n || X.length != n || B[0].length != X[0].length) {
            throw new IllegalArgumentException("Dimensions do not agree.");
        }

        double[] xx = new double[n];
        int p = B[0].length;
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                xx[i] = B[i][j];
            }

            solve(xx, xx);
            for (int i = 0; i < n; i++) {
                X[i][j] = xx[i];
            }
        }
    }