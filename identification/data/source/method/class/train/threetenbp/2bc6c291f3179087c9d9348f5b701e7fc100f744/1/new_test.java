@Test(expectedExceptions=NullPointerException.class)
    public void test_print_nullAppendable() throws Exception {
        ZoneOffsetPrinterParser pp = new ZoneOffsetPrinterParser("Z", "+HH:MM:ss");
        Calendrical cal = OFFSET_0130;
        pp.print(cal, (Appendable) null, symbols);
    }