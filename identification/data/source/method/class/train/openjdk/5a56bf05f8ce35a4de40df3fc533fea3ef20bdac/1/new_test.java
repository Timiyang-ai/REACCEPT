@Test(dataProvider="monthDays")
    public void test_lastDayOfMonth(int year, int month, int numDays) {
        HijrahDate hDate = HijrahChronology.INSTANCE.date(year, month, 1);
        hDate = hDate.with(TemporalAdjusters.lastDayOfMonth());
        assertEquals(hDate.get(ChronoField.DAY_OF_MONTH), numDays);
    }