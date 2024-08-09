@Test public void arrayTest() throws QueryException {
    parse("[ ]", Spec.RFC4627);
    parse("[]", "[ ]", Spec.RFC4627);
    parse("[[[[[[42], {}]]]]]", "[ [ [ [ [ [ 42 ], { } ] ] ] ] ]", Spec.RFC4627);
    parse("[ 1, 2, 3, 4, 5, 6, 7, 8 ]", Spec.RFC4627);
    parse("[1,2,3,]", "[ 1, 2, 3 ]", Spec.LIBERAL);

    error("[1,2,3,]", Spec.RFC4627);
    error("[,42]", Spec.RFC4627);
    error("[1, ", Spec.RFC4627);
  }