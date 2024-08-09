@Test public void arrayTest() throws QueryIOException {
    parse("[ ]", RFC4627);
    parse("[]", "[ ]", RFC4627);
    parse("[[[[[[42], {}]]]]]", "[ [ [ [ [ [ 42 ], { } ] ] ] ] ]", RFC4627);
    parse("[ 1, 2, 3, 4, 5, 6, 7, 8 ]", RFC4627);
    parse("[1,2,3,]", "[ 1, 2, 3 ]", LIBERAL);

    error("[1,2,3,]", RFC4627);
    error("[,42]", RFC4627);
    error("[1,", RFC4627);
  }