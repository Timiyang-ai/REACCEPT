@Test public void stringTest() throws QueryIOException {
    parse("\"\"", false);
    parse("\"test\"", false);
    parse("\"\u00e4\"", false);
    parse("\"\uD834\uDD1E\"", false);
    parse("\"\uD853\uDF5C\"", false);
    parse("\"\\n\"", false);
    parse("\"\\\"\\\\\"", false);
    parse("\"\\u000a\"", "\"\\n\"", false);
    parse("\"\\u000A\"", "\"\\n\"", false);
    parse("\"\n\"", "\"\\n\"", true);

    unescape("\"\\b\\f\\t\\r\\n\"", "\"\\\\b\\\\f\\\\t\\\\r\\\\n\"");
    // Unicode in JSON notation
    unescape("\"\\uD853\\uDF5C\"", "\"\\\\uD853\\\\uDF5C\"");
    unescape("\"\\uD853asdf\"", "\"\\\\uD853asdf\"");
    unescape("\"\\uD853\"", "\"\\\\uD853\"");
    // Unicode in Java notation
    unescape("\"\u00E4\\t\"", "\"\u00E4\\\\t\"");
    unescape("\"\u00E4\\u00E4\\t\"", "\"\u00E4\\\\u00E4\\\\t\"");

    error("\"\\u0A", false);
    error("\"\\uXX0A\"", false);
    error("\"\\u0 00\"", false);
    error("\"\\u0:00\"", false);
    error("\"\\u0_00\"", false);
    error("\"\\u0~00\"", false);
    error("\"test", false);
    error("\"\uD800", false);
    error("\"\n\"", false);

    error("\"\uD834\"", false);
    error("\"\uD853\uFFFF\"", false);
    error("\"\uFFFF\"", false);
    error("\"\uD853a\"", false);
    error("\"\\b\\f\\t\\r\\n\"", false);
    error("\"\\u0000\\u001F\"", false);
  }