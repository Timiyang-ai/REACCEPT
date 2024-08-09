@Test
  public void function() {
    String func = query(_INSPECT_FUNCTION.args(" true#0"));
    query(func + "/@name/data()", "true");
    query(func + "/@uri/data()", "http://www.w3.org/2005/xpath-functions");
    query(func + "/return/@type/data()", "xs:boolean");
    query(func + "/return/@occurrence/data()", "");

    func = query(_INSPECT_FUNCTION.args(" map { }"));
    query(func + "/@name/data()", "");
    query(func + "/@uri/data()", "");
    query(func + "/argument/@type/data()", "xs:anyAtomicType");
    query(func + "/return/@type/data()", "item()");
    query(func + "/return/@occurrence/data()", "*");

    func = query(_INSPECT_FUNCTION.args(" function($a as xs:int) as xs:integer { $a + 1 }"));
    query(func + "/@name/data()", "");
    query(func + "/@uri/data()", "");
    query(func + "/argument/@name/data()", "");
    query(func + "/argument/@type/data()", "xs:int");
    query(func + "/return/@type/data()", "xs:integer");
    query(func + "/return/@occurrence/data()", "");

    func = query("declare %private function Q{U}f($v as xs:int) as xs:integer {$v};" +
        _INSPECT_FUNCTION.args(" Q{U}f#1"));
    query(func + "/@name/data()", "f");
    query(func + "/@uri/data()", "U");
    query(func + "/argument/@name/data()", "v");
    query(func + "/argument/@type/data()", "xs:int");
    query(func + "/annotation/@name/data()", "private");
    query(func + "/annotation/@uri/data()", "http://www.w3.org/2012/xquery");
    query(func + "/return/@type/data()", "xs:integer");
    query(func + "/return/@occurrence/data()", "");

    // unknown annotations disappear
    query("declare namespace x='x';" +
      _INSPECT_FUNCTION.args(" %x:x function() {()}") + "/annotation", "");
  }