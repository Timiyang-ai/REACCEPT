@Test
    public void testCombination() {
        logger.info("combination");
        int n = 10;
        int k = 3;
        double expResult = 120.0;
        double result = Arithmetics.combination(n, k);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }