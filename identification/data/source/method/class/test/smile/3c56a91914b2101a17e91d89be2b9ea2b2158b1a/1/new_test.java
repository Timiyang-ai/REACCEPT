@Test
    public void testSolve() {
        System.out.println("solve");
        double[][] A = {
            {0.9000, 0.4000, 0.0000},
            {0.4000, 0.5000, 0.3000},
            {0.0000, 0.3000, 0.8000}
        };
        double[] b = {0.5, 0.5, 0.5};

        DenseMatrix a = Matrix.newInstance(A);
        LU lu = a.lu();
        double[] x = b.clone();
        lu.solve(x);

        BandMatrix instance = new BandMatrix(3, 1, 1);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++)
                if (A[i][j] != 0.0)
                    instance.set(i, j, A[i][j]);
        }

        instance.decompose();
        double[] result = new double[b.length];
        instance.solve(b, result);

        assertEquals(result.length, x.length);
        for (int i = 0; i < x.length; i++) {
            assertEquals(result[i], x[i], 1E-7);
        }

        instance.improve(b, result);
        for (int i = 0; i < x.length; i++) {
            assertEquals(result[i], x[i], 1E-15);
        }
    }