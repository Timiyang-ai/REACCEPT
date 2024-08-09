@Test
    public void testUniform() {
        logger.info("Uniform");
        int n = 10;
        double expResult = 0.1;
        double result = DiscreteDistributions.Uniform(n);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }