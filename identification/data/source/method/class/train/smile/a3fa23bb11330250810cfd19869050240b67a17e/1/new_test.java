@Test
    public void testInverse() {
        System.out.println("inverse");
        double[][] A = {
            {0.7220180, 0.07121225, 0.6881997},
            {-0.2648886, -0.89044952, 0.3700456},
            {-0.6391588, 0.44947578, 0.6240573}
        };
        DenseMatrix B = Math.inverse(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                assertEquals(A[j][i], B.get(i, j), 1E-7);
            }
        }
    }