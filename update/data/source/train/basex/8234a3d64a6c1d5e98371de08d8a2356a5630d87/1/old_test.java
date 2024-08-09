@Test
  public void contextFunctions() {
    query("declare function local:x() { 1 }; " +
        COUNT.args(_INSPECT_FUNCTIONS.args()), "1");
    query("declare function local:x() { 2 }; " +
        _INSPECT_FUNCTIONS.args() + "()", "2");
  }