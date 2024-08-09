@Test(dataProvider="parseData")
    public void test_parse_fresh(int minWidth, int maxWidth, SignStyle signStyle, int subsequentWidth, String text, int pos, int expectedPos, long expectedValue) {
        NumberPrinterParser pp = new NumberPrinterParser(DAY_OF_MONTH, minWidth, maxWidth, signStyle);
        if (subsequentWidth > 0) {
            pp = pp.withSubsequentWidth(subsequentWidth);
        }
        int newPos = pp.parse(parseContext, text, pos);
        assertEquals(newPos, expectedPos);
        if (expectedPos > 0) {
            assertParsed(parseContext, DAY_OF_MONTH, expectedValue);
        } else {
            assertEquals(parseContext.toCalendricalMerger().getInputMap().containsKey(DAY_OF_MONTH), false);
        }
    }