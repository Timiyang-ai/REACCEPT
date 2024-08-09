@Test
    public void testPlusYears() {
        assertEquals(testDate.plusYears(10), JapaneseDate.japaneseDate(testYear+10, testMonthOfYear, testDayOfMonth));
    }