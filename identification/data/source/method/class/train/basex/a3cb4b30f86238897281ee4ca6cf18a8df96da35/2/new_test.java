@Test public void objectTest() throws QueryIOException {
    parse("{ }", false);
    parse("{ \"\": 42 }", false);
    parse("{ a : 42, b: 23 }", "{ \"a\": 42, \"b\": 23 }", true);
    parse("{ \"a\": 1, \"b\": 2, }", "{ \"a\": 1, \"b\": 2 }", true);

    error("{ a : 42 }", false);
    error("{ \"a\": 42, b: 23 }", false);
  }