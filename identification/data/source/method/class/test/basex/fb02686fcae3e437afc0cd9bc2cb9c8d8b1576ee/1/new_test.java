@Test
  public void query() {
    contains(_CLIENT_EXECUTE.args(conn(), new ShowUsers()), S_USERINFO[0]);
    // multiple sessions
    query("let $a := " + conn() + ", $b := " + conn() + " return " +
        _CLIENT_QUERY.args("$a", "1") + '+' + _CLIENT_QUERY.args("$b", "2"), "3");
    // arguments
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; $a*2\"",
        " map { 'a': 1 }"), "2");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; count($a)\"",
        " map { 'a': () }"), "0");
    query(_CLIENT_QUERY.args(conn(), "\"declare variable $a external; count($a)\"",
        " map { 'a': (1 to 5) }"), "5");
    query(_CLIENT_QUERY.args(conn(), "\"declare context item external; .\"",
        " map { '': (1,<a/>,'a') }"), "1\n<a/>\na");
    // binary data
    query(_CLIENT_QUERY.args(conn(), "\"xs:hexBinary('41')\""), "A");
    query(_CLIENT_QUERY.args(conn(), "\"xs:base64Binary('QQ==')\""), "A");
    // serialization parameters (should be ignored)
    query(_CLIENT_QUERY.args(conn(),
        "\"" + SerializerOptions.METHOD.arg("text") + "<xml/>\""), "<xml/>");
    query(_CLIENT_QUERY.args(conn(),
        "\"" + SerializerOptions.ENCODING.arg("US-ASCII") + "'\u00e4'\""), "\u00e4");
    query(_CLIENT_QUERY.args(conn(), "\"xs:base64Binary('QQ==')\""), "A");
    // query errors: returning function items
    error(_CLIENT_QUERY.args(conn(), "\"function(){}\""), BXCL_FITEM_X);
    error(_CLIENT_QUERY.args(conn(), "true#0"), BXCL_FITEM_X);
    error(_CLIENT_QUERY.args(conn(), "[]"), BXCL_FITEM_X);
    error(_CLIENT_QUERY.args(conn(), "map{}"), BXCL_FITEM_X);
    // query errors: server-side errors
    error(_CLIENT_QUERY.args(conn(), "x"), NOCTX_X);
  }