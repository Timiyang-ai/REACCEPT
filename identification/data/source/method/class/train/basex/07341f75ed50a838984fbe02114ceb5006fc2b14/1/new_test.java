@Test public void stringTest() throws QueryException {
    parse("\"\"", Spec.ECMA_262);
    parse("\"test\"", Spec.ECMA_262);
    parse("\"ä\"", Spec.ECMA_262);
    parse("\"\uD834\uDD1E\"", Spec.ECMA_262);
    parse("\"\uD834\"", Spec.ECMA_262);
    parse("\"\uD853\uDF5C\"", Spec.ECMA_262);
    parse("\"\uD853\uFFFF\"", Spec.ECMA_262);
    parse("\"\uFFFF\"", Spec.ECMA_262);
    parse("\"\uD853a\"", Spec.ECMA_262);
    parse("\"\\n\"", Spec.ECMA_262);
    parse("\"\\b\\f\\t\\r\\n\"", Spec.ECMA_262);
    unescape("\"\\b\\f\\t\\r\\n\"", "\"\\\\b\\\\f\\\\t\\\\r\\\\n\"");
    parse("\"\\u0000\\u001F\"", Spec.ECMA_262);
    parse("\"\\\"\\\\\"", Spec.ECMA_262);
    parse("\"\\u000a\"", "\"\\n\"", Spec.ECMA_262);
    parse("\"\\u000A\"", "\"\\n\"", Spec.ECMA_262);
    parse("\"\n\"", "\"\\n\"", Spec.LIBERAL);
    unescape("\"\\uD853\\uDF5C\"", "\"\\\\uD853\\\\uDF5C\"");
    unescape("\"\\uD853asdf\"", "\"\\\\uD853asdf\"");
    unescape("\"\\uD853\"", "\"\\\\uD853\"");
    unescape("\"\uD853\\t\"", "\"\uD853\\\\t\"");
    unescape("\"\uD853\\uD853\\t\"", "\"\uD853\\\\uD853\\\\t\"");

    error("\"\\u0A", Spec.ECMA_262);
    error("\"\\uXX0A\"", Spec.ECMA_262);
    error("\"\\u0 00\"", Spec.ECMA_262);
    error("\"\\u0:00\"", Spec.ECMA_262);
    error("\"\\u0_00\"", Spec.ECMA_262);
    error("\"\\u0~00\"", Spec.ECMA_262);
    error("\"test", Spec.ECMA_262);
    error("\"\uD800", Spec.ECMA_262);
    error("\"\n\"", Spec.ECMA_262);
  }