@Test
    public void testGunning_fog_score() {
        logger.info("gunning_fog_score");
        String strText = TEST_STRING;
        double expResult = 118.0;
        double result = ReadabilityStatistics.gunningFogScore(strText);
        assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
    }