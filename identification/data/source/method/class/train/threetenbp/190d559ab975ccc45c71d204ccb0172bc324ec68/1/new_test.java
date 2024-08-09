@Test(groups={"tck"})
    public void test_with_DateTimeField() {
        OffsetDate test = OffsetDate.of(2008, 6, 30, OFFSET_PONE);
        assertEquals(test.with(ChronoField.YEAR, 2009), OffsetDate.of(2009, 6, 30, OFFSET_PONE));
        assertEquals(test.with(ChronoField.MONTH_OF_YEAR, 7), OffsetDate.of(2008, 7, 30, OFFSET_PONE));
        assertEquals(test.with(ChronoField.DAY_OF_MONTH, 1), OffsetDate.of(2008, 6, 1, OFFSET_PONE));
        assertEquals(test.with(ChronoField.DAY_OF_WEEK, 2), OffsetDate.of(2008, 7, 1, OFFSET_PONE));
        assertEquals(test.with(ChronoField.DAY_OF_YEAR, 183), OffsetDate.of(2008, 7, 1, OFFSET_PONE));

        assertEquals(test.with(ChronoField.OFFSET_SECONDS, 7205), OffsetDate.of(2008, 6, 30, ZoneOffset.ofHoursMinutesSeconds(2, 0, 5)));
    }