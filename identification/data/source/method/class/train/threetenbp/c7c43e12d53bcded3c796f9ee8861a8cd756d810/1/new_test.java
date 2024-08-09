@Test
    public void test_parse_basicIsoDate() {
        LocalDate expected = LocalDate.of(2008, 6, 3);
        assertEquals(DateTimeFormatter.BASIC_ISO_DATE.parse("20080603", LocalDate.FROM), expected);
    }