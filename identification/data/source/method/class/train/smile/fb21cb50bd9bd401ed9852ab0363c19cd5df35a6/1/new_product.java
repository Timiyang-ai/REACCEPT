public void solve(DenseMatrix B) {
        if (!full) {
            throw new IllegalStateException("This is not a FULL singular value decomposition.");
        }

        if (B.nrows() != m) {
            throw new IllegalArgumentException("Dimensions do not agree.");
        }

        double[] b = new double[m];
        double[] x = new double[n];
        int p = B.ncols();
        for (int j = 0; j < p; j++) {
            for (int i = 0; i < m; i++) {
                b[i] = B.get(i, j);
            }

            solve(b, x);
            for (int i = 0; i < n; i++) {
                B.set(i, j, x[i]);
            }
        }
    }