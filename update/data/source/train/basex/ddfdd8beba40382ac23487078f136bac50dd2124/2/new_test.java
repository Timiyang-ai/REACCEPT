@Test public void stringTest() throws QueryException {
    parse("\"\"", JsonSpec.ECMA_262);
    parse("\"test\"", JsonSpec.ECMA_262);
    parse("\"\u00e4\"", JsonSpec.ECMA_262);
    parse("\"\uD834\uDD1E\"", JsonSpec.ECMA_262);
    parse("\"\uD834\"", JsonSpec.ECMA_262);
    parse("\"\uD853\uDF5C\"", JsonSpec.ECMA_262);
    parse("\"\uD853\uFFFF\"", JsonSpec.ECMA_262);
    parse("\"\uFFFF\"", JsonSpec.ECMA_262);
    parse("\"\uD853a\"", JsonSpec.ECMA_262);
    parse("\"\\n\"", JsonSpec.ECMA_262);
    parse("\"\\b\\f\\t\\r\\n\"", JsonSpec.ECMA_262);
    unescape("\"\\b\\f\\t\\r\\n\"", "\"\\\\b\\\\f\\\\t\\\\r\\\\n\"");
    parse("\"\\u0000\\u001F\"", JsonSpec.ECMA_262);
    parse("\"\\\"\\\\\"", JsonSpec.ECMA_262);
    parse("\"\\u000a\"", "\"\\n\"", JsonSpec.ECMA_262);
    parse("\"\\u000A\"", "\"\\n\"", JsonSpec.ECMA_262);
    parse("\"\n\"", "\"\\n\"", JsonSpec.LIBERAL);
    unescape("\"\\uD853\\uDF5C\"", "\"\\\\uD853\\\\uDF5C\"");
    unescape("\"\\uD853asdf\"", "\"\\\\uD853asdf\"");
    unescape("\"\\uD853\"", "\"\\\\uD853\"");
    unescape("\"\uD853\\t\"", "\"\uD853\\\\t\"");
    unescape("\"\uD853\\uD853\\t\"", "\"\uD853\\\\uD853\\\\t\"");

    error("\"\\u0A", JsonSpec.ECMA_262);
    error("\"\\uXX0A\"", JsonSpec.ECMA_262);
    error("\"\\u0 00\"", JsonSpec.ECMA_262);
    error("\"\\u0:00\"", JsonSpec.ECMA_262);
    error("\"\\u0_00\"", JsonSpec.ECMA_262);
    error("\"\\u0~00\"", JsonSpec.ECMA_262);
    error("\"test", JsonSpec.ECMA_262);
    error("\"\uD800", JsonSpec.ECMA_262);
    error("\"\n\"", JsonSpec.ECMA_262);
  }