@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        ReducedPrinterParser pp = new ReducedPrinterParser(YEAR, 2, 2010);
        pp.print(calendrical2012, (Appendable) null, symbols);
    }