@Test
  public void testXMLEntry() throws BaseXException {
    final String fun = check(FunDef.XMLENTRY, String.class, String.class);
    query(fun + "('" + ZIP + "', '" + ENTRY2 + "')");
    query(fun + "('" + ZIP + "', '" + ENTRY2 + "')//title/text()", "XML");
  }