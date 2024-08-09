@Test
  public void execute() {
    contains(_CLIENT_EXECUTE.args(conn(), new ShowUsers()), Text.USERHEAD[0]);
    query("let $a := " + conn() + ", $b := " + conn() + " return (" +
        _CLIENT_EXECUTE.args("$a", new XQuery("1")) + "," +
        _CLIENT_EXECUTE.args("$b", new XQuery("2")) + ")", "1 2");
    // BXCL0004: connection errors
    error(_CLIENT_EXECUTE.args(conn(), "x"), Err.BXCL_COMMAND);
  }