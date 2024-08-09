@Test public void lookup() {
    query("[1](1)", "1");
    query("[1, 2, 3](2)", "2");
    query("[1 to 2](1)", "1 2");
    query("array { 1 to 2 }(2)", "2");
    array("[[1]](1)", "[1]");
    query("[[[1]]](1)(1)(1)", "1");

    error("[](0)", Err.ARRAYPOS);
    error("[](1)", Err.ARRAYPOS);
    error("[1](-5000000000)", Err.ARRAYPOS);
    error("[1](-1)", Err.ARRAYPOS);
    error("[1](0)", Err.ARRAYPOS);
    error("[1](2)", Err.ARRAYPOS);
    error("[1](5000000000)", Err.ARRAYPOS);
  }