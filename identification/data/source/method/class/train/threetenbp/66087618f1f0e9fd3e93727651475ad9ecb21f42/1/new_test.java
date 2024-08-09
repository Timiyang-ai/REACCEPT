@Test(expectedExceptions=IndexOutOfBoundsException.class)
    public void test_parse_negativePosition() throws Exception {
        StringLiteralPrinterParser pp = new StringLiteralPrinterParser("hello");
        pp.parse(parseContext, "hello", -1);
    }