@Test
    public void testWithDayOfYear() {
        JapaneseDate date = testDate.withDayOfYear(15);
        assertEquals(date, JapaneseDate.japaneseDate(testYear, MonthOfYear.JANUARY, 15));
    }