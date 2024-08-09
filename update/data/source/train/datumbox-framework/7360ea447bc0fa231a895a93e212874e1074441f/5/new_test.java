@Test
    public void testFlesch_kincaid_reading_ease() {
        logger.info("flesch_kincaid_reading_ease");
        String strText = TEST_STRING;
        double expResult = -188.4;
        double result = ReadabilityStatistics.fleschKincaidReadingEase(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }