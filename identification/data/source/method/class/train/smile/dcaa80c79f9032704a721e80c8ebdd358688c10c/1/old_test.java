@Test
    public void testSolve_doubleArr_doubleArr() {
        System.out.println("solve");
        double[][] A = {
            {0.9000, 0.4000, 0.7000},
            {0.4000, 0.5000, 0.3000},
            {0.7000, 0.3000, 0.8000}
        };
        double[] B = {0.5, 0.5, 0.5};
        double[] X = {-0.2027027, 0.8783784, 0.4729730};

        SVD result = Matrix.newInstance(A).svd();
        double[] x = new double[B.length];
        result.solve(B, x);
        assertEquals(X.length, x.length);
        for (int i = 0; i < X.length; i++) {
            assertEquals(X[i], x[i], 1E-7);
        }
    }