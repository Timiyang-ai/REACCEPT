@Test
    public void testGaussCdf() {
        logger.info("GaussCdf");
        double z = 3.0;
        double expResult = 0.9986501025724;
        double result = ContinuousDistributions.gaussCdf(z);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }