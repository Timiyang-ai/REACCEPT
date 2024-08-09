@Test
  public void query() {
    contains(_CLIENT_EXECUTE.args(conn(), new ShowUsers()), Text.S_USERINFO[0]);
    query("let $a := " + conn() + ", $b := " + conn() + " return " +
        _CLIENT_QUERY.args("$a", "1") + '+' + _CLIENT_QUERY.args("$b", "2"), "3");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; $a*2\"",
        " map { 'a': 1 }"), "2");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; count($a)\"",
        " map { 'a': () }"), "0");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; count($a)\"",
        " map { 'a': (1 to 5) }"), "5");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; $a\"",
        " map { 'a': (1,<a/>,'a') }"), "1<a/>a");
    // query errors
    error(_CLIENT_QUERY.args(conn(), "x"), Err.NOCTX);
  }