@Test
    public void testChisquareCdf() {
        logger.info("ChisquareCdf");
        double x = 3.0;
        int df = 10;
        double expResult = 0.018575928421771;
        double result = ContinuousDistributions.ChisquareCdf(x, df);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }