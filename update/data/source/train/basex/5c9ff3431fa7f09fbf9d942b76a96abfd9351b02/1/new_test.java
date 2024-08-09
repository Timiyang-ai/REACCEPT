@Test public void numberTest() throws QueryIOException {
    parse("0", JsonSpec.ECMA_262);
    parse("1", JsonSpec.ECMA_262);
    parse("-1", JsonSpec.ECMA_262);
    parse("10", JsonSpec.ECMA_262);
    parse("1234567890123456789012345678901234567890", JsonSpec.ECMA_262);
    parse("0.5", JsonSpec.ECMA_262);
    parse("0.01", JsonSpec.ECMA_262);
    parse("-0.01", JsonSpec.ECMA_262);
    parse("1234567890123456789012345678901234567890" +
        ".1234567890123456789012345678901234567890", JsonSpec.ECMA_262);
    parse("0E1", JsonSpec.ECMA_262);
    parse("0E-1", JsonSpec.ECMA_262);
    parse("0E+1", JsonSpec.ECMA_262);
    parse("-0E+1", JsonSpec.ECMA_262);
    parse("0E00", JsonSpec.ECMA_262);
    parse("1234567890123456789012345678901234567890" +
        "e1234567890123456789012345678901234567890", JsonSpec.ECMA_262);
    parse("123e-123", JsonSpec.ECMA_262);
    parse("123.4e-123", JsonSpec.ECMA_262);
    parse("123.456E0001", JsonSpec.ECMA_262);
    parse("-123.456E0001", JsonSpec.ECMA_262);
    parse("[ -123.456E0001, 0 ]", JsonSpec.ECMA_262);

    error("01", JsonSpec.ECMA_262);
    error("-", JsonSpec.ECMA_262);
    error("-\u00e4", JsonSpec.ECMA_262);
    error("0.", JsonSpec.ECMA_262);
    error("0.\u00e4", JsonSpec.ECMA_262);
    error("1e", JsonSpec.ECMA_262);
    error("1e+", JsonSpec.ECMA_262);
    error("1e+\u00e4", JsonSpec.ECMA_262);
    error("1e+0\u00e4", JsonSpec.ECMA_262);
  }