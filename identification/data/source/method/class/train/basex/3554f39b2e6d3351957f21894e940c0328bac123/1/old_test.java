@Test
  public void testTextEntry() throws BaseXException {
    final String fun = check(FunDef.TEXTENTRY, Uri.class, String.class);
    query(fun + "(xs:anyURI('" + ZIP + "'), '" + ENTRY1 + "')");
    // newlines are removed from the result..
    contains(fun + "(xs:anyURI('" + ZIP + "'), '" + ENTRY1 + "')", "aaboutab");
  }