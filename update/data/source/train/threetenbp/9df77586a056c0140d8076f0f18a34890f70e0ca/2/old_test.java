@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        Calendrical calendrical = Calendrical.calendrical(RULE_DOW, 3);
        TextPrinterParser pp = new TextPrinterParser(RULE_DOW, TextStyle.FULL);
        pp.print(calendrical, (Appendable) null, symbols);
    }