@Test
    public void testGetStateTransitionProbabilities() {
        System.out.println("getStateTransitionProbabilities");
        HMM hmm = new HMM(pi, Matrix.of(a), Matrix.of(b));
        DenseMatrix result = hmm.getStateTransitionProbabilities();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                assertEquals(a[i][j], result.get(i, j), 1E-7);
            }
        }
    }