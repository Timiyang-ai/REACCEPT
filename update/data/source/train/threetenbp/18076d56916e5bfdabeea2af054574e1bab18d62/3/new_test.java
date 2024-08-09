@Test(dataProvider="parseText")
    public void test_parse_strict_caseSensitive_parseUpper(DateTimeFieldRule<?> rule, TextStyle style, int dow, String input) throws Exception {
        DateTimeParseContext context = new DateTimeParseContext(symbols);
        context.setCaseSensitive(true);
        TextPrinterParser pp = new TextPrinterParser(rule, style);
        int newPos = pp.parse(context, input.toUpperCase(), 0);
        assertEquals(newPos, ~0);
        assertEquals(context.toCalendricalMerger().getInputMap().containsKey(RULE_DOW), false);
    }