@Test
public void numberTest() throws QueryIOException {
    parse("0", true);
    parse("1", true);
    parse("-1", true);
    parse("10", true);
    parse("1234567890123456789012345678901234567890", true);
    parse("0.5", true);
    parse("0.01", true);
    parse("-0.01", true);
    parse("1234567890123456789012345678901234567890" +
        ".1234567890123456789012345678901234567890", true);
    parse("0E1", true);
    parse("0E-1", true);
    parse("0E+1", true);
    parse("-0E+1", true);
    parse("0E00", true);
    parse("1234567890123456789012345678901234567890" +
        "e1234567890123456789012345678901234567890", true);
    parse("123e-123", true);
    parse("123.4e-123", true);
    parse("123.456E0001", true);
    parse("-123.456E0001", true);
    parse("[ -123.456E0001, 0 ]", true);

    error("01", true);
    error("-", true);
    error("-\u00e4", true);
    error("0.", true);
    error("0.\u00e4", true);
    error("1e", true);
    error("1e+", true);
    error("1e+\u00e4", true);
    error("1e+0\u00e4", true);
}