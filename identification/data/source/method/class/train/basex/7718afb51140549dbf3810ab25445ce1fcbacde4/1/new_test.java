@Test
  public void functions() {
    query("declare function local:x() { 1 }; " +
        COUNT.args(_INSPECT_FUNCTIONS.args()), "1");
    query("declare function local:x() { 2 }; " +
        _INSPECT_FUNCTIONS.args() + "()", "2");
    query("import module namespace hello='world' at 'src/test/resources/hello.xqm';" +
        "inspect:functions()[last()] instance of function(*)", "true");

    query("for $f in " + _INSPECT_FUNCTIONS.args("src/test/resources/hello.xqm")
        + "where local-name-from-QName(function-name($f)) = 'world' "
        + "return $f()", "hello world");
  }