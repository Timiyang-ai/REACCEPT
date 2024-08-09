@Test
    public void testColMeans() {
        System.out.println("colMeans");
        double[][] A = {
            {0.7220180, 0.07121225, 0.6881997},
            {-0.2648886, -0.89044952, 0.3700456},
            {-0.6391588, 0.44947578, 0.6240573}
        };
        double[] r = {-0.06067647, -0.12325383, 0.56076753};

        double[] result = Math.colMeans(A);
        for (int i = 0; i < r.length; i++) {
            assertEquals(result[i], r[i], 1E-7);
        }
    }