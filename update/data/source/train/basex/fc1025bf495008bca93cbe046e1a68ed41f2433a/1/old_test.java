@Test
  public void query() {
    contains(_CLIENT_EXECUTE.args(conn(), new ShowUsers()), Text.USERHEAD[0]);
    query("let $a := " + conn() + ", $b := " + conn() + " return " +
        _CLIENT_QUERY.args("$a", "1") + "+" + _CLIENT_QUERY.args("$b", "2"), "3");
    query(_CLIENT_QUERY.args(conn(), "\"$a*2\"", " { 'a':1 }"), "2");
    // query errors
    error(_CLIENT_QUERY.args(conn(), "x"), Err.NOCTX);
    error(_CLIENT_QUERY.args(conn(), "\"$a\"", " { 'a':(1,2) }"), Err.BXCL_ITEM);
  }