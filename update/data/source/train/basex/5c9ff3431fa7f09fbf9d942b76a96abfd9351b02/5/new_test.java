@Test public void objectTest() throws QueryIOException {
    parse("{ }", JsonSpec.RFC4627);
    parse("{ \"\": 42 }", JsonSpec.RFC4627);
    parse("{ a : 42, b: 23 }", "{ \"a\": 42, \"b\": 23 }", JsonSpec.LIBERAL);
    parse("{ \"a\": 1, \"b\": 2, }", "{ \"a\": 1, \"b\": 2 }", JsonSpec.LIBERAL);

    error("{ a : 42 }", JsonSpec.RFC4627);
    error("{ \"a\": 42, b: 23 }", JsonSpec.RFC4627);
  }