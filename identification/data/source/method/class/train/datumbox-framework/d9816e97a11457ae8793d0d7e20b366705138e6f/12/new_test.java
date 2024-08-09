    @Test
    public void testGeometricCdf() {
        logger.info("GeometricCdf");
        int k = 3;
        double p = 0.5;
        double expResult = 0.875;
        double result = DiscreteDistributions.geometricCdf(k, p);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }