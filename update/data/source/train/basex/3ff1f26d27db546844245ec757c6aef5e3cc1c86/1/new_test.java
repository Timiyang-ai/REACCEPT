@Test
  public void functionTest() throws Exception {
    createColl();
    // document access after inlining
    check("declare function local:x($d) { collection($d)//text()[. = '1'] };"
        + "local:x('" + NAME + "')", "1");
    check("declare function local:x($d, $s) { collection($d)//text()[. = $s] };"
        + "local:x('" + NAME + "', '1')", "1");

    // text: search term must be string
    final String doc = _DB_OPEN.args(NAME);
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