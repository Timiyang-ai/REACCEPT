@Test
    public void testWithDayOfMonth() {
        HijrahDate date = testDate.withDayOfMonth(4);
        assertEquals(date, HijrahDate.of(testYear, testMonthOfYear, 4));
    }