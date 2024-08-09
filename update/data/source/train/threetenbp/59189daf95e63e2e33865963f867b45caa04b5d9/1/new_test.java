@Test(groups={"tck"})
    public void now_Clock() {
        Instant instant = OffsetDateTime.of(2010, 12, 31, 0, 0, ZoneOffset.UTC).toInstant();
        Clock clock = Clock.fixed(instant, ZoneId.UTC);
        YearMonth test = YearMonth.now(clock);
        assertEquals(test.getYear(), 2010);
        assertEquals(test.getMonth(), Month.DECEMBER);
    }