@Test
    public void testLogp() {
        System.out.println("logp");
        HMM hmm = new HMM(pi, Matrix.of(a), Matrix.of(b));
        int[] o = {0, 0, 1, 1, 0, 1, 1, 0};
        double expResult = -5.609373;
        double result = hmm.logp(o);
        assertEquals(expResult, result, 1E-6);
    }