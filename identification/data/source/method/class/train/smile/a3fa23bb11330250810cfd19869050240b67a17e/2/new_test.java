@Test
    public void testEigen_doubleArrArr_int() {
        System.out.println("eigen");
        double[][] A = {
            {0.9000, 0.4000, 0.7000},
            {0.4000, 0.5000, 0.3000},
            {0.7000, 0.3000, 0.8000}
        };
        double[][] eigenVectors = {
            {0.6881997, -0.07121225, 0.7220180},
            {0.3700456, 0.89044952, -0.2648886},
            {0.6240573, -0.44947578, -0.6391588}
        };
        double[] eigenValues = {1.7498382, 0.3165784, 0.1335834};
        EigenValueDecomposition result = Math.eigen(A, 3);
        assertTrue(Math.equals(eigenValues, result.getEigenValues(), 1E-7));

        assertEquals(eigenVectors.length, result.getEigenVectors().nrows());
        assertEquals(eigenVectors[0].length, result.getEigenVectors().ncols());
        for (int i = 0; i < eigenVectors.length; i++) {
            for (int j = 0; j < eigenVectors[i].length; j++) {
                assertEquals(Math.abs(eigenVectors[i][j]), Math.abs(result.getEigenVectors().get(i, j)), 1E-7);
            }
        }
    }