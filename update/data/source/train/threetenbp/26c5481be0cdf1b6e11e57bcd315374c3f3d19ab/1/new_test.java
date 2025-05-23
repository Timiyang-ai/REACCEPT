@Test(groups={"tck"})
    public void test_parseToBuilder_StringParsePosition() throws Exception {
        DateTimeFormatter test = new DateTimeFormatter(Locale.ENGLISH, DateTimeFormatSymbols.STANDARD, compPP);
        ParsePosition pos = new ParsePosition(0);
        DateTimeBuilder result = test.parseToBuilder("ONE30XXX", pos);
        assertEquals(pos.getIndex(), 5);
        assertEquals(pos.getErrorIndex(), -1);
        assertEquals(result.getFieldValueMap().size(), 1);
        assertEquals(result.getFieldValueMap().get(DAY_OF_MONTH), Long.valueOf(30));
    }