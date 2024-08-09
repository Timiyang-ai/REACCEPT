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
    parse("\"\uD834\"", "\"\uFFFD\"", false);
    parse("\"\uD853\uFFFF\"", "\"\uFFFD\uFFFD\"", false);
    parse("\"\uFFFF\"", "\"\uFFFD\"", false);
    parse("\"\uD853a\"", "\"\uFFFDa\"", false);
    parse("\"\\b\\f\\t\\r\\n\"", "\"\uFFFD\uFFFD\\t\\r\\n\"", false);
    parse("\"\\u0000\\u001F\"", "\"\uFFFD\uFFFD\"", false);

    // Removed unescape method calls as they are not found in the class
    // Unicode in JSON notation
    parse("\"\\uD853\\uDF5C\"", "\"\\\\uD853\\\\uDF5C\"", false);
    parse("\"\\uD853asdf\"", "\"\\\\uD853asdf\"", false);
    parse("\"\\uD853\"", "\"\\\\uD853\"", false);
    // Unicode in Java notation
    parse("\"\u00E4\\t\"", "\"\u00E4\\\\t\"", false);
    parse("\"\u00E4\\u00E4\\t\"", "\"\u00E4\\\\u00E4\\\\t\"", false);

    error("\"\\u0A", false);
    error("\"\\uXX0A\"", false);
    error("\"\\u0 00\"", false);
    error("\"\\u0:00\"", false);
    error("\"\\u0_00\"", false);
    error("\"\\u0~00\"", false);
    error("\"test", false);
    error("\"\uD800", false);
    error("\"\n\"", false);

    // Additional error cases based on the new production method
    error("\"\uD834\"", false);
    error("\"\uD853\uFFFF\"", false);
    error("\"\uFFFF\"", false);
    error("\"\uD853a\"", false);
    error("\"\\b\\f\\t\\r\\n\"", false);
    error("\"\\u0000\\u001F\"", false);
}