@Test
    public void test_from_TemporalAccessor() {
        LocalDateTime base = LocalDateTime.of(2007, 7, 15, 17, 30);
        assertEquals(LocalDateTime.from(base), base);
        assertEquals(LocalDateTime.from(ZonedDateTime.of(base, ZoneOffset.ofHours(2))), base);
    }