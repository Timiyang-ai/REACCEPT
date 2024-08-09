@Test
    public void testGetSymbolEmissionProbabilities() {
        System.out.println("getSymbolEmissionProbabilities");
        HMM hmm = new HMM(pi, Matrix.of(a), Matrix.of(b));
        DenseMatrix result = hmm.getSymbolEmissionProbabilities();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                assertEquals(b[i][j], result.get(i, j), 1E-7);
            }
        }
    }