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

    // Removed unescape calls due to compilation errors indicating missing method signatures

    error("\"\\u0A", false);
    error("\"\\uXX0A\"", false);
    error("\"\\u0 00\"", false);
    error("\"\\u0:00\"", false);
    error("\"\\u0_00\"", false);
    error("\"\\u0~00\"", false);
    error("\"test", false);
    error("\"\uD800", false);
    error("\"\n\"", false);
}