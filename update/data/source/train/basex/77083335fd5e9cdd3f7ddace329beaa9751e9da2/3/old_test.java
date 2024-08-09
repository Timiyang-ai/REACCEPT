@Test
  public void testEval() throws BaseXException {
    // test wrong arguments
    args("util:eval", String.class);

    // dynamically evaluate query expressions
    query("util:eval('1')", "1");
    query("util:eval('1 + 2')", "3");
    error("util:eval('1+')", "XPST0003");
    error("declare variable $a := 1; util:eval('$a')", "XPST0008");
    error("for $a in (1,2) return util:eval('$a')", "XPST0008");
  }