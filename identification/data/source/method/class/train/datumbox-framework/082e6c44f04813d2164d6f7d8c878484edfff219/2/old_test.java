@Test
    public void testBinomial() {
        logger.info("Binomial");
        int k = 3;
        double p = 0.5;
        int n = 10;
        double expResult = 0.11718750001462;
        double result = DiscreteDistributions.Binomial(k, p, n);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }