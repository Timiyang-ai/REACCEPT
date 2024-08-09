    @Test
    public void testBinomialCdf() {
        logger.info("BinomialCdf");
        int k = 3;
        double p = 0.5;
        int n = 10;
        double expResult = 0.17187500002003;
        double result = DiscreteDistributions.binomialCdf(k, p, n);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }