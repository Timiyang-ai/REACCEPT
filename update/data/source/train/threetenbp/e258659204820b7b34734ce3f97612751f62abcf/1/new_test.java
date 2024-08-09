@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        CharLiteralPrinterParser pp = new CharLiteralPrinterParser('a');
        pp.print(emptyCalendrical, (Appendable) null, symbols);
    }