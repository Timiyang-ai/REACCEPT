@Test public void invoke() {
    final Function func = _XQUERY_INVOKE;
    // queries
    query(func.args("src/test/resources/input.xq"), "XML");
    error(func.args("src/test/resources/xxx.xq"), WHICHRES_X);
  }