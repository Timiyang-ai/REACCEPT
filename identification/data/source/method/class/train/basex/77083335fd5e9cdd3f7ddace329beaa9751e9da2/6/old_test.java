@Test
  public void testMB() throws BaseXException {
    // wrong arguments
    error("util:mb()", "XPST0017");
    error("util:mb('a','b')", "XPTY0004");
    error("util:mb('a','b','c')", "XPST0017");
    error("util:mb(1+)", "XPST0003");

    // measure memory (will always yield different results)
    query("util:mb(())");
    query("util:mb(1 to 10000, false())");
    query("util:mb(1 to 10000, true())");
  }