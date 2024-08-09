@Test
    public void test_parseToBuilder_StringParsePosition() throws Exception {
        DateTimeFormatter test = fmt.withLocale(Locale.ENGLISH).withSymbols(DateTimeFormatSymbols.STANDARD);
        ParsePosition pos = new ParsePosition(0);
        TemporalAccessor result = test.parseUnresolved("ONE30XXX", pos);
        assertEquals(pos.getIndex(), 5);
        assertEquals(pos.getErrorIndex(), -1);
        assertEquals(result.getLong(DAY_OF_MONTH), 30L);
    }