@Test
  public void eval() {
    query(_XQUERY_EVAL.args("1"), 1);
    query(_XQUERY_EVAL.args("1 + 2"), 3);
    query(_XQUERY_EVAL.args("\"declare variable $a external; $a\"", " map { '$a': 'b' }"), "b");
    query(_XQUERY_EVAL.args("\"declare variable $a external; $a\"", " map { 'a': 'b' }"), "b");
    query(_XQUERY_EVAL.args("\"declare variable $a external; $a\"", " map { 'a': (1,2) }"), "1 2");
    query(_XQUERY_EVAL.args("\"declare variable $local:a external; $local:a\"",
        " map { xs:QName('local:a'): 1 }"), "1");
    query(_XQUERY_EVAL.args(".", " map { '': 1 }"), "1");
    error(_XQUERY_EVAL.args("1+"), Err.CALCEXPR);
    error("declare variable $a:=1;" + _XQUERY_EVAL.args("\"$a\""), Err.VARUNDEF_X);
    error("for $a in (1,2) return " + _XQUERY_EVAL.args("\"$a\""), Err.VARUNDEF_X);
    // check updating expressions
    error(_XQUERY_EVAL.args("delete node ()"), Err.BXXQ_UPDATING);
    error(_XQUERY_EVAL.args("declare %updating function local:x() {()}; local:x()"),
        Err.BXXQ_UPDATING);
    query(_XQUERY_EVAL.args("declare %updating function local:x() {()}; 1"));
    query(_XQUERY_EVAL.args('"' + DOC.args(PATH).replace('"', '\'') + '"'));

    // check additional options
    query(_DB_CREATE.args('"' + NAME + '"'));
    query("try{ " + _XQUERY_EVAL.args("\"(1 to 10000000000000)[.=0]\"", " map{}",
        " map{ 'timeout':'1'}") + " } catch * { () }", "");
    error(_XQUERY_EVAL.args("\"doc('" + NAME + "')\"", " map{}", " map{ 'permission':'none'}"),
        Err.BXXQ_PERM_X);
    error(_XQUERY_EVAL.args("\"db:open('" + NAME + "')\"", " map{}", " map{ 'permission':'none'}"),
        Err.BXDB_OPEN_X);
    error(_XQUERY_EVAL.args("\"file:exists('x')\"", " map{}", " map{ 'permission':'none'}"),
        Err.BXXQ_PERM_X);
    error(_XQUERY_EVAL.args("\"(1 to 10000000000000)[.=0]\"", " map{}", " map{ 'timeout':'1'}"),
        Err.BXXQ_STOPPED);
    error(_XQUERY_EVAL.args("\"(1 to 10000000000000) ! <a/>\"", " map{}", " map{ 'memory':'10'}"),
        Err.BXXQ_STOPPED);
  }