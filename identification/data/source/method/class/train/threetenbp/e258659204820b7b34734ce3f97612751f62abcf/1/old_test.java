@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        CharLiteralPrinterParser pp = new CharLiteralPrinterParser('a');
        pp.print(emptyDateTime, (Appendable) null, symbols);
    }