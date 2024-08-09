@Test
    public void testGeometric() {
        logger.info("Geometric");
        int k = 3;
        double p = 0.5;
        double expResult = 0.125;
        double result = DiscreteDistributions.geometric(k, p);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }