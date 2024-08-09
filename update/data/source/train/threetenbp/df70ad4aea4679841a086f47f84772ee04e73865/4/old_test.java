@Test
    public void testWithMonthOfYear() {
        ThaiBuddhistDate date = testDate.withMonthOfYear(MonthOfYear.APRIL);
        assertEquals(date, ThaiBuddhistDate.thaiBuddhistDate(testYear, MonthOfYear.APRIL, testDayOfMonth));
    }