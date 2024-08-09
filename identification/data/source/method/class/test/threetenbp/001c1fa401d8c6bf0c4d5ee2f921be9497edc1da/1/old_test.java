@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        FlexiDateTime dt = new FlexiDateTime(null, null, null, null, null).withFieldValue(DayOfMonth.rule(), 3);
        NumberPrinterParser pp = new NumberPrinterParser(DayOfMonth.rule(), 1, 2, SignStyle.NEVER);
        pp.print((Appendable) null, dt, locale);
    }