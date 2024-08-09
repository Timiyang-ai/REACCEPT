public void solve(double[] b) {
        int m = lu.nrows();
        int n = lu.ncols();

        if (m != n) {
            throw new UnsupportedOperationException("The matrix is not square.");
        }

        if (b.length != m) {
            throw new IllegalArgumentException(String.format("Row dimensions do not agree: A is %d x %d, but b is %d x 1", lu.nrows(), lu.ncols(), b.length));
        }

        if (isSingular()) {
            throw new RuntimeException("Matrix is singular.");
        }

        double[] x = new double[b.length];
        // Copy right hand side with pivoting
        for (int i = 0; i < m; i++) {
            x[i] = b[piv[i]];
        }

        // Solve L*Y = B(piv,:)
        for (int k = 0; k < n; k++) {
            for (int i = k + 1; i < n; i++) {
                x[i] -= x[k] * lu.get(i, k);
            }
        }

        // Solve U*X = Y;
        for (int k = n - 1; k >= 0; k--) {
            x[k] /= lu.get(k, k);

            for (int i = 0; i < k; i++) {
                x[i] -= x[k] * lu.get(i, k);
            }
        }

        // Copy the result back to B.
        for (int i = 0; i < m; i++) {
            b[i] = x[i];
        }
    }