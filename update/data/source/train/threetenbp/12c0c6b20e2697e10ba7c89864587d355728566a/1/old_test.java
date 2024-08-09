@Test
    public void testWithMonthOfYear() {
        ThaiBuddhistDate date = testDate.withMonthOfYear(4);
        assertEquals(date, ThaiBuddhistDate.thaiBuddhistDate(testYear, 4, testDayOfMonth));
    }