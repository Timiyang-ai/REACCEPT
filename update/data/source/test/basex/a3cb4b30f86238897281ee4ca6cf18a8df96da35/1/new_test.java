@Test public void numberTest() throws QueryIOException {
    parse("0", false);
    parse("1", false);
    parse("-1", false);
    parse("10", false);
    parse("1234567890123456789012345678901234567890", false);
    parse("0.5", false);
    parse("0.01", false);
    parse("-0.01", false);
    parse("1234567890123456789012345678901234567890" +
        ".1234567890123456789012345678901234567890", false);
    parse("0E1", false);
    parse("0E-1", false);
    parse("0E+1", false);
    parse("-0E+1", false);
    parse("0E00", false);
    parse("1234567890123456789012345678901234567890" +
        "e1234567890123456789012345678901234567890", false);
    parse("123e-123", false);
    parse("123.4e-123", false);
    parse("123.456E0001", false);
    parse("-123.456E0001", false);
    parse("[ -123.456E0001, 0 ]", false);

    error("01", false);
    error("-", false);
    error("-\u00e4", false);
    error("0.", false);
    error("0.\u00e4", false);
    error("1e", false);
    error("1e+", false);
    error("1e+\u00e4", false);
    error("1e+0\u00e4", false);
  }