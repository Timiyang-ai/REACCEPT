@Test
  public void idTest() {
    check("function($x) { $x }(42)",
        "42",
        "empty(//" + Util.className(InlineFunc.class) + ')'
    );
  }