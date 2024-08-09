@Test public void cast() {
    query("[] cast as xs:integer?", "");
    query("xs:integer([1])", "1");

    error("[] cast as xs:integer", Err.INVCAST_X_X_X);
    error("[1,2] cast as xs:integer", Err.INVCAST_X_X_X);
  }