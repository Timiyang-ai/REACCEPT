@Test
    public void test_parseBest_firstOption() throws Exception {
        DateTimeFormatter test = DateTimeFormatters.pattern("yyyy-MM-dd[ZZZ]");
        TemporalAccessor result = test.parseBest("2011-06-30+03:00", OffsetDate::from, LocalDate::from);
        assertEquals(result, OffsetDate.of(LocalDate.of(2011, 6, 30), ZoneOffset.ofHours(3)));
    }