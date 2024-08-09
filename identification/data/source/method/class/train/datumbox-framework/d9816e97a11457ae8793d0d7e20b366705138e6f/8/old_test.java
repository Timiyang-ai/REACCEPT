    @Test
    public void testPoisson() {
        logger.info("Poisson");
        int k = 3;
        double lamda = 5.0;
        double expResult = 0.14037389583692;
        double result = DiscreteDistributions.poisson(k, lamda);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }