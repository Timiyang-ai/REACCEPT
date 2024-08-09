@Test
    public void testCombination() {
        logger.info("combination");
        int n = 52;
        int k = 5;
        double expResult = 2598960.0;
        double result = Arithmetics.combination(n, k);
        assertEquals(expResult, result, Constants.DOUBLE_ACCURACY_HIGH);
    }