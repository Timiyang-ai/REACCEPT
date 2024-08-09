@Test
    public void testMinusDays() {
        assertEquals(testDate.minusDays(2), JapaneseDate.of(testYear, testMonthOfYear, testDayOfMonth-2));
    }