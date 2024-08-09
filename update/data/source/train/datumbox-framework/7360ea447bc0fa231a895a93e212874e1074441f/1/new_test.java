@Test
    public void testAutomated_readability_index() {
        logger.info("automated_readability_index");
        String strText = TEST_STRING;
        double expResult = 143.2;
        double result = ReadabilityStatistics.automatedReadabilityIndex(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }