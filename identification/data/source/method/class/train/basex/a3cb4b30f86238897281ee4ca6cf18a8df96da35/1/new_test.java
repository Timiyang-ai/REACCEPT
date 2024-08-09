@Test public void arrayTest() throws QueryIOException {
    parse("[ ]", false);
    parse("[]", "[ ]", false);
    parse("[[[[[[42], {}]]]]]", "[ [ [ [ [ [ 42 ], { } ] ] ] ] ]", false);
    parse("[ 1, 2, 3, 4, 5, 6, 7, 8 ]", false);
    parse("[1,2,3,]", "[ 1, 2, 3 ]", true);

    error("[1,2,3,]", false);
    error("[,42]", false);
    error("[1,", false);
  }