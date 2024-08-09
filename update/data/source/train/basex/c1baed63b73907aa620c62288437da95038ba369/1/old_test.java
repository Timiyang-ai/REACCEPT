@Test
  public void eval() {
    query(_XQUERY_EVAL.args("1"), 1);
    query(_XQUERY_EVAL.args("1 + 2"), 3);
    query(_XQUERY_EVAL.args("\"$a\"", " { '$a' : 'b' }"), "b");
    query(_XQUERY_EVAL.args("\"$a\"", " { 'a' : 'b' }"), "b");
    query(_XQUERY_EVAL.args("\"$a\"", " { 'a' : (1,2) }"), "1 2");
    query(_XQUERY_EVAL.args("\"declare variable $local:a external;$local:a\"",
        " { xs:QName('local:a') : 1 }"), "1");
    query(_XQUERY_EVAL.args(".", " { '' : 1 }"), "1");
    error(_XQUERY_EVAL.args("1+"), Err.CALCEXPR);
    error("declare variable $a:=1;" + _XQUERY_EVAL.args("\"$a\""), Err.VARUNDEF);
    error("for $a in (1,2) return " + _XQUERY_EVAL.args("\"$a\""), Err.VARUNDEF);
    // check updating expressions
    error(_XQUERY_EVAL.args("delete node ()"), Err.BXXQ_UPDATING);
    error(_XQUERY_EVAL.args("declare %updating function local:x() {()}; local:x()"),
        Err.BXXQ_UPDATING);
    query(_XQUERY_EVAL.args("declare %updating function local:x() {()}; 1"));
  }