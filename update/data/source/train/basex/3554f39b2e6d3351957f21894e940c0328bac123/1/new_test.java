@Test
  public void testTextEntry() throws BaseXException {
    final String fun = check(FunDef.TEXTENTRY,
        String.class, String.class, String.class);
    query(fun + "('" + ZIP + "', '" + ENTRY1 + "')");
    query(fun + "('" + ZIP + "', '" + ENTRY1 + "', 'US-ASCII')");
    error(fun + "('" + ZIP + "', '" + ENTRY1 + "', 'xyz')",
        Err.ZIPFAIL);
    // newlines are removed from the result..
    contains(fun + "('" + ZIP + "', '" + ENTRY1 + "')", "aaboutab");
  }