@Test
    public void testBernoulli() {
        logger.info("Bernoulli");
        boolean k = true;
        double p = 0.5;
        double expResult = 0.5;
        double result = DiscreteDistributions.bernoulli(k, p);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }