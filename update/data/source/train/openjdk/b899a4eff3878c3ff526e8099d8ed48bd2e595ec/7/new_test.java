@Test
    public void test_parseBest_firstOption() throws Exception {
        DateTimeFormatter test = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm[XXX]");
        TemporalAccessor result = test.parseBest("2011-06-30 12:30+03:00", ZonedDateTime::from, LocalDateTime::from);
        LocalDateTime ldt = LocalDateTime.of(2011, 6, 30, 12, 30);
        assertEquals(result, ZonedDateTime.of(ldt, ZoneOffset.ofHours(3)));
    }