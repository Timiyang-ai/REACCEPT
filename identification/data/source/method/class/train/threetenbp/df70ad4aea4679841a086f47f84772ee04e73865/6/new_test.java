@Test
    public void testPlusDays() {
        assertEquals(testDate.plusDays(2), MinguoDate.of(testYear, testMonthOfYear, testDayOfMonth+2));
    }