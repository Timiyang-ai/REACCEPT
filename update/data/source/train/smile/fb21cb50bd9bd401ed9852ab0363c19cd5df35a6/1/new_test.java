@Test
    public void testSolve_doubleArrArr_doubleArrArr() {
        System.out.println("solve");
        double[][] A = {
            {0.9000, 0.4000, 0.7000},
            {0.4000, 0.5000, 0.3000},
            {0.7000, 0.3000, 0.8000}
        };
        double[][] B = {
            {0.5, 0.2},
            {0.5, 0.8},
            {0.5, 0.3}
        };
        double[][] X = {
            {-0.2027027, -1.2837838},
            {0.8783784, 2.2297297},
            {0.4729730, 0.6621622}
        };

        SVD result = Matrix.newInstance(A).svd();
        DenseMatrix x = Matrix.newInstance(B);
        result.solve(x);
        for (int i = 0; i < x.nrows(); i++) {
            for (int j = 0; j < x.ncols(); j++) {
                assertEquals(X[i][j], x.get(i, j), 1E-7);
            }
        }
    }