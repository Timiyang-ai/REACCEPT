@Test
    public void testUniformCdf() {
        logger.info("UniformCdf");
        double x = 3.0;
        double a = 2.0;
        double b = 10.0;
        double expResult = 0.125;
        double result = ContinuousDistributions.uniformCdf(x, a, b);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }