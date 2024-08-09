@Test
    public void testNegativeBinomialCdf() {
        logger.info("NegativeBinomialCdf");
        int n = 10;
        int r = 4;
        double p = 0.5;
        double expResult = 0.12705078125;
        double result = DiscreteDistributions.negativeBinomialCdf(n, r, p);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }