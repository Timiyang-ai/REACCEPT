@Test public void arrayTest() throws QueryIOException {
    parse("[ ]", JsonSpec.RFC4627);
    parse("[]", "[ ]", JsonSpec.RFC4627);
    parse("[[[[[[42], {}]]]]]", "[ [ [ [ [ [ 42 ], { } ] ] ] ] ]", JsonSpec.RFC4627);
    parse("[ 1, 2, 3, 4, 5, 6, 7, 8 ]", JsonSpec.RFC4627);
    parse("[1,2,3,]", "[ 1, 2, 3 ]", JsonSpec.LIBERAL);

    error("[1,2,3,]", JsonSpec.RFC4627);
    error("[,42]", JsonSpec.RFC4627);
    error("[1, ", JsonSpec.RFC4627);
  }