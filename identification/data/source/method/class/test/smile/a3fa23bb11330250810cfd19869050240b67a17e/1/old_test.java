@Test
    public void testSolve_doubleArrArr_doubleArrArr() {
        System.out.println("solve");
        double[][] A = {
            {0.9000, 0.4000, 0.7000},
            {0.4000, 0.5000, 0.3000},
            {0.7000, 0.3000, 0.8000}
        };
        double[][] B2 = {
            {0.5, 0.2},
            {0.5, 0.8},
            {0.5, 0.3}
        };
        double[][] X2 = {
            {-0.2027027, -1.2837838},
            {0.8783784, 2.2297297},
            {0.4729730, 0.6621622}
        };
        double[][] x = Math.solve(A, B2);
        assertEquals(X2.length, x.length);
        assertEquals(X2[0].length, x[0].length);
        for (int i = 0; i < X2.length; i++) {
            for (int j = 0; j < X2[i].length; j++) {
                assertEquals(X2[i][j], x[i][j], 1E-7);
            }
        }
    }