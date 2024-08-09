@Test
  public void literalTest() {
    check("lower-case#1('FooBar')",
        "foobar",
        "empty(//" + Util.className(FuncLit.class) + ')'
    );
  }