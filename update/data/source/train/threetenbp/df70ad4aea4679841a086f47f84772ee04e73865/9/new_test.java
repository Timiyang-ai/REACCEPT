@Test
    public void testWithYear() {
        ThaiBuddhistDate date = testDate.withYear(ThaiBuddhistEra.BEFORE_BUDDHIST, 2010);
        assertEquals(date, ThaiBuddhistDate.of(ThaiBuddhistEra.BEFORE_BUDDHIST, 2010, testMonthOfYear, testDayOfMonth));
    }