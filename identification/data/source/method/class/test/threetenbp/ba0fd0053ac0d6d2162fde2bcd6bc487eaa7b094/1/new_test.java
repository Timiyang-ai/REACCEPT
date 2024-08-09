@Test(expectedExceptions=CalendricalPrintException.class)
    public void test_print_emptyCalendrical() throws Exception {
        ZoneOffsetPrinterParser pp = new ZoneOffsetPrinterParser("Z", true, true);
        pp.print(emptyCalendrical, buf, symbols);
    }