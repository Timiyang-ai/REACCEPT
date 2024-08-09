@Test
    public void testGetSymbolEmissionProbabilities() {
        System.out.println("getSymbolEmissionProbabilities");
        HMM hmm = new HMM(pi, a, b);
        double[][] expResult = b;
        double[][] result = hmm.getSymbolEmissionProbabilities();
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[i].length; j++) {
                assertEquals(expResult[i][j], result[i][j], 1E-7);
            }
        }
    }