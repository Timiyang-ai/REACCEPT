@Test
public void eval() {
  query(_XQUERY_EVAL.args("1"), "1");
  query(_XQUERY_EVAL.args("1 + 2"), "3");
  query(_XQUERY_EVAL.args("declare variable $a external; $a", " map { '$a': 'b' }"), "b");
  query(_XQUERY_EVAL.args("declare variable $a external; $a", " map { 'a': 'b' }"), "b");
  query(_XQUERY_EVAL.args("declare variable $a external; $a", " map { 'a': (1,2) }"), "1\n2");
  query(_XQUERY_EVAL.args("declare variable $local:a external; $local:a", " map { xs:QName('local:a'): 1 }"), "1");
  query(_XQUERY_EVAL.args(".", " map { '': 1 }"), "1");
  error(_XQUERY_EVAL.args("1+"), CALCEXPR);
  error("declare variable $a:=1;" + _XQUERY_EVAL.args("$a"), VARUNDEF_X);
  error("for $a in (1,2) return " + _XQUERY_EVAL.args("$a"), VARUNDEF_X);
  // Adjusted for updated error codes
  error(_XQUERY_EVAL.args("delete node ()"), XQUERY_UPDATE1);
  error(_XQUERY_EVAL.args("declare %updating function local:x() {()}; local:x()"), XQUERY_UPDATE1);
  query(_XQUERY_EVAL.args("declare %updating function local:x() {()}; 1"));

  // check additional options
  query(_DB_CREATE.args(NAME));
  query("try { " + _XQUERY_EVAL.args("(1 to 10000000000000)[. = 0]", " map { }", " map { 'timeout': 1 }") + " } catch * { () }", "");
  query(_XQUERY_EVAL.args("static-base-uri()", " map { }", " map { 'base-uri': 'http://x.x/' }"), "http://x.x/");

  // Corrected test case to match the actual error expectation
  error(_XQUERY_EVAL.args("fn:doc(\"Sandbox\")", " map { }", " map { 'permission': 'none' }"), XQUERY_PERMISSION1_X);
  // Other error checks
  error(_XQUERY_EVAL.args(_FILE_EXISTS.args("x").trim(), " map { }", " map { 'permission': 'none' }"), XQUERY_PERMISSION1_X);
  error(_XQUERY_EVAL.args("(1 to 10000000000000)[. = 0]", " map { }", " map { 'timeout': 1 }"), XQUERY_TIMEOUT);
  error(_XQUERY_EVAL.args("(1 to 10000000000000) ! <a/>", " map { }", " map { 'memory': 10 }"), XQUERY_MEMORY);
}