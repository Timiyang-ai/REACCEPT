@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        Calendrical calendrical = DateTimeField.of(ISOChronology.dayOfMonthRule(), 3);
        NumberPrinterParser pp = new NumberPrinterParser(ISOChronology.dayOfMonthRule(), 1, 2, SignStyle.NEVER);
        pp.print(calendrical, (Appendable) null, symbols);
    }