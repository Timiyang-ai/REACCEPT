@Test(dataProvider="sampleDates")
    public void test_toLocalYear(int year, int month, int day, ZoneOffset offset) {
        OffsetDate t = OffsetDate.date(year, month, day, offset);
        assertEquals(t.toYear(), Year.isoYear(year));
    }