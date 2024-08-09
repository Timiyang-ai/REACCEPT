@Test public void objectTest() throws QueryIOException {
    parse("{ }", RFC4627);
    parse("{ \"\": 42 }", RFC4627);
    parse("{ a : 42, b: 23 }", "{ \"a\": 42, \"b\": 23 }", LIBERAL);
    parse("{ \"a\": 1, \"b\": 2, }", "{ \"a\": 1, \"b\": 2 }", LIBERAL);

    error("{ a : 42 }", RFC4627);
    error("{ \"a\": 42, b: 23 }", RFC4627);
  }