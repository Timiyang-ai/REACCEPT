@Test
    public void testMinusDays() {
        assertEquals(testDate.minusDays(2), JapaneseDate.japaneseDate(testYear, testMonthOfYear, testDayOfMonth-2));
    }