@Test(groups={"tck"})
    public void test_parse_isoOrdinalDate() {
        DateTimeBuilder expected = new DateTimeBuilder(YEAR, 2008).addFieldValue(DAY_OF_YEAR, 123);
        assertParseMatch(DateTimeFormatters.isoOrdinalDate().parseToContext("2008-123", new ParsePosition(0)), expected);
    }