@Test
  public void parse() {
    query(_XQUERY_PARSE.args("1") + "/name()", "MainModule");
    query(_XQUERY_PARSE.args("1") + "/@updating/string()", "false");
    query(_XQUERY_PARSE.args("1") + "/QueryPlan/@compiled/string()", "false");

    query(_XQUERY_PARSE.args("1", " map{'compile':true()}") + "/QueryPlan/@compiled/string()",
        "true");
    query(_XQUERY_PARSE.args("1", " map{'plan':false()}") + "/QueryPlan", "");

    query(_XQUERY_PARSE.args("module namespace x='x'; "
        + "declare function x:x() { 1 };") + "/name()", "LibraryModule");

    query(_XQUERY_PARSE.args("delete node <a/>") + "/name()", "MainModule");
    query(_XQUERY_PARSE.args("delete node <a/>") + "/@updating/string()", "true");

    error(_XQUERY_PARSE.args("1+"), CALCEXPR);
    query("\n\ntry {" + _XQUERY_PARSE.args("1 +",
        " map{'pass':true()}") + "} catch * { $err:line-number }", "1");

    query("contains(try {" + _XQUERY_PARSE.args("1 +",
        " map { 'base-uri': 'XXXX', 'pass': 'true' }") + "} catch * { $err:module }, 'XXXX')",
        "true");
  }