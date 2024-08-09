@Test
    public void testWithYear() {
        ThaiBuddhistDate date = testDate.withYear(ThaiBuddhistEra.BEFORE_BUDDHIST, 2010);
        assertEquals(date, ThaiBuddhistDate.thaiBuddhistDate(ThaiBuddhistEra.BEFORE_BUDDHIST, 2010, testMonthOfYear, testDayOfMonth));
    }