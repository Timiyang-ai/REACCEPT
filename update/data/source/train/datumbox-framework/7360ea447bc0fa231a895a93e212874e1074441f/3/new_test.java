@Test
    public void testFlesch_kincaid_grade_level() {
        logger.info("flesch_kincaid_grade_level");
        String strText = TEST_STRING;
        double expResult = 112.3;
        double result = ReadabilityStatistics.fleschKincaidGradeLevel(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }