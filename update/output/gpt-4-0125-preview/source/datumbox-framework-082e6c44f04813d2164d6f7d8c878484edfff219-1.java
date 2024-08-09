@Test
    public void testHypergeometricCdf() {
        logger.info("HypergeometricCdf");
        int k = 3;
        int n = 10;
        int Kp = 30;
        int Np = 100;
        double expResult = 0.65401998866081;
        double result = DiscreteDistributions.hypergeometricCdf(k, n, Kp, Np);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }