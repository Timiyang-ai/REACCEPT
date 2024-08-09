@Test
    public void testNegativeBinomial() {
        logger.info("NegativeBinomial");
        int n = 10;
        int r = 4;
        double p = 0.5;
        double expResult = 0.08203125;
        double result = DiscreteDistributions.NegativeBinomial(n, r, p);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }