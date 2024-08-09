@Test
    public void testInverse() {
        System.out.println("inverse");
        double[][] A = {
            {0.7220180, 0.07121225, 0.6881997},
            {-0.2648886, -0.89044952, 0.3700456},
            {-0.6391588, 0.44947578, 0.6240573}
        };
        double[][] B = Math.inverse(A);
        assertTrue(Math.equals(B, Math.transpose(A), 1E-7));
    }