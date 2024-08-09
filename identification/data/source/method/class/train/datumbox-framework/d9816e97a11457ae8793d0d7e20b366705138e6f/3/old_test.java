    @Test
    public void testUniformCdf() {
        logger.info("UniformCdf");
        int k = 3;
        int n = 10;
        double expResult = 0.3;
        double result = DiscreteDistributions.uniformCdf(k, n);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }