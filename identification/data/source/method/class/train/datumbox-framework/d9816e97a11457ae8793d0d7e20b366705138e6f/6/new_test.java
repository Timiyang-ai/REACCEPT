    @Test
    public void testUniform() {
        logger.info("Uniform");
        int n = 10;
        double expResult = 0.1;
        double result = DiscreteDistributions.uniform(n);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }