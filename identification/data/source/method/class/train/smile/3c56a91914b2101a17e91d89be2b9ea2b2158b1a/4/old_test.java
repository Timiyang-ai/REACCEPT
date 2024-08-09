@Test
    public void testRowSum() {
        System.out.println("rowSum");
        double[][] A = {
            {0.7220180, 0.07121225, 0.6881997},
            {-0.2648886, -0.89044952, 0.3700456},
            {-0.6391588, 0.44947578, 0.6240573}
        };
        double[] r = {1.4814300, -0.7852925, 0.4343743};

        double[] result = Math.rowSum(A);
        for (int i = 0; i < r.length; i++) {
            assertEquals(result[i], r[i], 1E-7);
        }
    }