@Test
    public void testWithDayOfYear() {
        JapaneseDate date = testDate.withDayOfYear(15);
        assertEquals(date, JapaneseDate.of(testYear, MonthOfYear.JANUARY, 15));
    }