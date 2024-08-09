@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        Calendrical calendrical = DateTimeFields.fields(NANO_RULE, 3);
        FractionPrinterParser pp = new FractionPrinterParser(NANO_RULE, 0, 9);
        pp.print(calendrical, (Appendable) null, symbols);
    }