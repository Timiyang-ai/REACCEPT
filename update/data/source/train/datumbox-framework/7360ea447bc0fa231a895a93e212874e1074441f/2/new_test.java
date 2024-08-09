@Test
    public void testColeman_liau_index() {
        logger.info("coleman_liau_index");
        String strText = TEST_STRING;
        double expResult = 6.8;
        double result = ReadabilityStatistics.colemanLiauIndex(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }