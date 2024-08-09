@Test
  public void info() {
    // check if the info string is not empty
    query("let $a := " + conn() + " return (" +
        _CLIENT_EXECUTE.args("$a", "xquery 123") + " and " +
        _CLIENT_INFO.args("$a") + ')', "true");
  }