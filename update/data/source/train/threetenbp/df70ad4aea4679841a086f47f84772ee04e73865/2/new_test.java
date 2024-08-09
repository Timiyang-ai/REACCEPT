@Test
    public void testPlusYears() {
        assertEquals(testDate.plusYears(10), JapaneseDate.of(testYear+10, testMonthOfYear, testDayOfMonth));
    }