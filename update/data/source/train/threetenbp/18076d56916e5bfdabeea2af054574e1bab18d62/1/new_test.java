@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        Calendrical calendrical = new MockSimpleCalendrical(DayOfMonth.rule(), 3);
        NumberPrinterParser pp = new NumberPrinterParser(DayOfMonth.rule(), 1, 2, SignStyle.NEVER);
        pp.print(calendrical, (Appendable) null, symbols);
    }