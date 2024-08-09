@Test(expectedExceptions=UnsupportedRuleException.class)
    public void test_print_emptyCalendrical() throws Exception {
        ZoneOffsetPrinterParser pp = new ZoneOffsetPrinterParser("Z", "+HH:MM:ss");
        pp.print(emptyCalendrical, buf, symbols);
    }