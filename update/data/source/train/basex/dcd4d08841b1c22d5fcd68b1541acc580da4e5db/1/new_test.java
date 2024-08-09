@Test public void evalURI() {
    final Function func = _XQUERY_EVAL;
    // queries
    query(func.args(" xs:anyURI('src/test/resources/input.xq')"), "XML");
    error(func.args(" xs:anyURI('src/test/resources/xxx.xq')"), WHICHRES_X);
  }