@Test
    public void testHypergeometric() {
        logger.info("Hypergeometric");
        int k = 3;
        int n = 10;
        int Kp = 30;
        int Np = 100;
        double expResult = 0.28116339430254;
        double result = DiscreteDistributions.Hypergeometric(k, n, Kp, Np);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }