@Test
    public void testGetStateTransitionProbabilities() {
        System.out.println("getStateTransitionProbabilities");
        HMM hmm = new HMM(pi, a, b);
        double[][] expResult = a;
        double[][] result = hmm.getStateTransitionProbabilities();
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[i].length; j++) {
                assertEquals(expResult[i][j], result[i][j], 1E-7);
            }
        }
    }