@Test public void stringTest() throws QueryIOException {
    parse("\"\"", ECMA_262);
    parse("\"test\"", ECMA_262);
    parse("\"\u00e4\"", ECMA_262);
    parse("\"\uD834\uDD1E\"", ECMA_262);
    parse("\"\uD834\"", ECMA_262);
    parse("\"\uD853\uDF5C\"", ECMA_262);
    parse("\"\uD853\uFFFF\"", ECMA_262);
    parse("\"\uFFFF\"", ECMA_262);
    parse("\"\uD853a\"", ECMA_262);
    parse("\"\\n\"", ECMA_262);
    parse("\"\\b\\f\\t\\r\\n\"", ECMA_262);
    parse("\"\\u0000\\u001F\"", ECMA_262);
    parse("\"\\\"\\\\\"", ECMA_262);
    parse("\"\\u000a\"", "\"\\n\"", ECMA_262);
    parse("\"\\u000A\"", "\"\\n\"", ECMA_262);
    parse("\"\n\"", "\"\\n\"", LIBERAL);

    unescape("\"\\b\\f\\t\\r\\n\"", "\"\\\\b\\\\f\\\\t\\\\r\\\\n\"");
    unescape("\"\\uD853\\uDF5C\"", "\"\\\\uD853\\\\uDF5C\"");
    unescape("\"\\uD853asdf\"", "\"\\\\uD853asdf\"");
    unescape("\"\\uD853\"", "\"\\\\uD853\"");
    unescape("\"\uD853\\t\"", "\"\uD853\\\\t\"");
    unescape("\"\uD853\\uD853\\t\"", "\"\uD853\\\\uD853\\\\t\"");

    error("\"\\u0A", ECMA_262);
    error("\"\\uXX0A\"", ECMA_262);
    error("\"\\u0 00\"", ECMA_262);
    error("\"\\u0:00\"", ECMA_262);
    error("\"\\u0_00\"", ECMA_262);
    error("\"\\u0~00\"", ECMA_262);
    error("\"test", ECMA_262);
    error("\"\uD800", ECMA_262);
    error("\"\n\"", ECMA_262);
  }