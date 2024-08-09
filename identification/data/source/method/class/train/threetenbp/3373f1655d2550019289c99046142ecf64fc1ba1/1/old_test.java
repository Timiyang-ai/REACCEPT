@Test(groups={"tck"})
    public void test_extract_Class() {
        LocalDateTime test = LocalDateTime.of(2008, 6, 30, 12, 30, 40, 987654321);
        assertEquals(test.extract(LocalDate.class), test.getDate());
        assertEquals(test.extract(LocalTime.class), test.getTime());
        assertEquals(test.extract(LocalDateTime.class), null);
        assertEquals(test.extract(OffsetDate.class), null);
        assertEquals(test.extract(OffsetTime.class), null);
        assertEquals(test.extract(OffsetDateTime.class), null);
        assertEquals(test.extract(ZonedDateTime.class), null);
        assertEquals(test.extract(ZoneOffset.class), null);
        assertEquals(test.extract(ZoneId.class), null);
        assertEquals(test.extract(Instant.class), null);
        assertEquals(test.extract(String.class), null);
        assertEquals(test.extract(BigDecimal.class), null);
        assertNull(test.extract(null));
    }