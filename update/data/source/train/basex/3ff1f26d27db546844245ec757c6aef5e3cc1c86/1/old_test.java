@Test
  public void functionTest() throws Exception {
    createColl();
    final String doc = _DB_OPEN.args(NAME);
    // text: search term must be string
    check("declare function local:x() {" + doc +
        "//text()[. = '1'] }; local:x()", "1");
    check("declare function local:x($x as xs:string) {" + doc +
        "//text()[. = $x] }; local:x('1')", "1");
    // full-text: search term may can have any type
    check("declare function local:x() {" + doc +
        "//text()[. contains text '1'] }; local:x()", "1");
    check("declare function local:x($x) {" + doc +
        "//text()[. contains text { $x }] }; local:x('1')", "1");
  }