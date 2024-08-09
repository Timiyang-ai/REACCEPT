@Test
    public void testPlusDays() {
        assertEquals(testDate.plusDays(2), MinguoDate.minguoDate(testYear, testMonthOfYear, testDayOfMonth+2));
    }