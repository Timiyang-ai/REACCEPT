@Test(groups={"tck"})
    public void test_with_DateTimeField() {
        OffsetTime test = OffsetTime.of(12, 30, 40, 987654321, OFFSET_PONE);
        assertEquals(test.with(ChronoField.HOUR_OF_DAY, 15), OffsetTime.of(15, 30, 40, 987654321, OFFSET_PONE));
        assertEquals(test.with(ChronoField.MINUTE_OF_HOUR, 50), OffsetTime.of(12, 50, 40, 987654321, OFFSET_PONE));
        assertEquals(test.with(ChronoField.SECOND_OF_MINUTE, 50), OffsetTime.of(12, 30, 50, 987654321, OFFSET_PONE));
        assertEquals(test.with(ChronoField.NANO_OF_SECOND, 12345), OffsetTime.of(12, 30, 40, 12345, OFFSET_PONE));
        assertEquals(test.with(ChronoField.HOUR_OF_AMPM, 6), OffsetTime.of(18, 30, 40, 987654321, OFFSET_PONE));
        assertEquals(test.with(ChronoField.AMPM_OF_DAY, 0), OffsetTime.of(0, 30, 40, 987654321, OFFSET_PONE));

        assertEquals(test.with(ChronoField.OFFSET_SECONDS, 7205), OffsetTime.of(12, 30, 40, 987654321, ZoneOffset.ofHoursMinutesSeconds(2, 0, 5)));
    }