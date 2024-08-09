@Test
    public void testRowMean() {
        System.out.println("rowMean");
        double[][] A = {
            {0.7220180, 0.07121225, 0.6881997},
            {-0.2648886, -0.89044952, 0.3700456},
            {-0.6391588, 0.44947578, 0.6240573}
        };
        double[] r = {0.4938100, -0.2617642, 0.1447914};

        double[] result = Math.rowMean(A);
        for (int i = 0; i < r.length; i++) {
            assertEquals(result[i], r[i], 1E-7);
        }
    }