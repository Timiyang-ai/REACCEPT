@Test
    public void testWithMonthOfYear() {
        ThaiBuddhistDate date = testDate.withMonthOfYear(MonthOfYear.APRIL);
        assertEquals(date, ThaiBuddhistDate.of(testYear, MonthOfYear.APRIL, testDayOfMonth));
    }