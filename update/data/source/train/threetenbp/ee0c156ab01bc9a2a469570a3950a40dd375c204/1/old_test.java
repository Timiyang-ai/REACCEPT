@Test
    public void test_parse_weekDate() {
        LocalDate expected = LocalDate.of(2004, 1, 28);
        assertEquals(DateTimeFormatters.isoWeekDate().parse("2004-W05-3", LocalDate.class), expected);
    }