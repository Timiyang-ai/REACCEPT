@Test
    public void testBernoulliCdf() {
        logger.info("BernoulliCdf");
        int k = 1;
        double p = 0.5;
        double expResult = 1.0;
        double result = DiscreteDistributions.bernoulliCdf(k, p);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }