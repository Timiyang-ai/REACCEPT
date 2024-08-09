@Test
    public void testSmog_index() {
        logger.info("smog_index");
        String strText = TEST_STRING;
        double expResult = 14.1;
        double result = ReadabilityStatistics.smogIndex(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }