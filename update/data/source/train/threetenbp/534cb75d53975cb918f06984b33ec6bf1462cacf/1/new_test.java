@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullStringBuilder() throws Exception {
        ReducedPrinterParser pp = new ReducedPrinterParser(YEAR, 2, 2010);
        pp.print(calendrical2012, (StringBuilder) null, symbols);
    }