@Test public void numberTest() throws QueryIOException {
    parse("0", ECMA_262);
    parse("1", ECMA_262);
    parse("-1", ECMA_262);
    parse("10", ECMA_262);
    parse("1234567890123456789012345678901234567890", ECMA_262);
    parse("0.5", ECMA_262);
    parse("0.01", ECMA_262);
    parse("-0.01", ECMA_262);
    parse("1234567890123456789012345678901234567890" +
        ".1234567890123456789012345678901234567890", ECMA_262);
    parse("0E1", ECMA_262);
    parse("0E-1", ECMA_262);
    parse("0E+1", ECMA_262);
    parse("-0E+1", ECMA_262);
    parse("0E00", ECMA_262);
    parse("1234567890123456789012345678901234567890" +
        "e1234567890123456789012345678901234567890", ECMA_262);
    parse("123e-123", ECMA_262);
    parse("123.4e-123", ECMA_262);
    parse("123.456E0001", ECMA_262);
    parse("-123.456E0001", ECMA_262);
    parse("[ -123.456E0001, 0 ]", ECMA_262);

    error("01", ECMA_262);
    error("-", ECMA_262);
    error("-\u00e4", ECMA_262);
    error("0.", ECMA_262);
    error("0.\u00e4", ECMA_262);
    error("1e", ECMA_262);
    error("1e+", ECMA_262);
    error("1e+\u00e4", ECMA_262);
    error("1e+0\u00e4", ECMA_262);
}