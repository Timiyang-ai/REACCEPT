@Test(groups={"tck"})
    public void test_parse_basicIsoDate() {
        LocalDate expected = LocalDate.of(2008, 6, 3);
        assertEquals(DateTimeFormatters.basicIsoDate().parse("20080603", LocalDate::from), expected);
    }