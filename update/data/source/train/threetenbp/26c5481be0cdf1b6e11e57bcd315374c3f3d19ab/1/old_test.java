@Test(groups={"tck"})
    public void test_parseToContext_StringParsePosition() throws Exception {
        DateTimeFormatter test = new DateTimeFormatter(Locale.ENGLISH, DateTimeFormatSymbols.STANDARD, compPP);
        ParsePosition pos = new ParsePosition(0);
        DateTimeParseContext result = test.parseToContext("ONE30XXX", pos);
        assertEquals(pos.getIndex(), 5);
        assertEquals(pos.getErrorIndex(), -1);
        assertEquals(result.getParsed().size(), 1);
        assertEquals(result.getParsed(DAY_OF_MONTH), Long.valueOf(30));
    }