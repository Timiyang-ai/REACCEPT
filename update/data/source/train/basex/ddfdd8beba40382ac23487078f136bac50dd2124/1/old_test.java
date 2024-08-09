@Test public void objectTest() throws QueryException {
    parse("{ }", Spec.RFC4627);
    parse("{ \"\": 42 }", Spec.RFC4627);
    parse("{ a : 42, b: 23 }", "{ \"a\": 42, \"b\": 23 }", Spec.LIBERAL);
    parse("{ \"a\": 1, \"b\": 2, }", "{ \"a\": 1, \"b\": 2 }", Spec.LIBERAL);

    error("{ a : 42 }", Spec.RFC4627);
    error("{ \"a\": 42, b: 23 }", Spec.RFC4627);
  }