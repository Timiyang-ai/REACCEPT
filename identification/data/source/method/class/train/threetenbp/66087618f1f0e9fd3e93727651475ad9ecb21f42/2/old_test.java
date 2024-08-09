@Test(expectedExceptions=UnsupportedRuleException.class)
    public void test_print_emptyCalendrical() throws Exception {
        NumberPrinterParser pp = new NumberPrinterParser(DAY_OF_MONTH, 1, 2, SignStyle.NEVER);
        pp.print(emptyCalendrical, buf, symbols);
    }