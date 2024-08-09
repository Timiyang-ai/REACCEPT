@Test
    public void testPoissonCdf() {
        logger.info("PoissonCdf");
        int k = 3;
        double lamda = 5.0;
        double expResult = 0.26502591533403;
        double result = DiscreteDistributions.poissonCdf(k, lamda);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }