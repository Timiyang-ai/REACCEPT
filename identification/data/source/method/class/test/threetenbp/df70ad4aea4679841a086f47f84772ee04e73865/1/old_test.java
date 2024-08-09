@Test
    public void testWithDayOfMonth() {
        HijrahDate date = testDate.withDayOfMonth(4);
        assertEquals(date, HijrahDate.hijrahDate(testYear, testMonthOfYear, 4));
    }