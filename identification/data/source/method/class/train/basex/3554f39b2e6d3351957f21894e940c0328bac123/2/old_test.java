@Test
  public void testXMLEntry() throws BaseXException {
    final String fun = check(FunDef.XMLENTRY, Uri.class, String.class);
    query(fun + "(xs:anyURI('" + ZIP + "'), '" + ENTRY2 + "')");
    query(fun + "(xs:anyURI('" + ZIP + "'), '" +
        ENTRY2 + "')//title/text()", "XML");
  }