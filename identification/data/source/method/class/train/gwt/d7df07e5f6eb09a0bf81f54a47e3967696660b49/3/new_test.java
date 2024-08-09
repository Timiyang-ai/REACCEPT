  public void test_putAttribute_default() throws SAXParseException {
    List<String> statements = call_putAttribute(stub);
    // validate
    assertEquals(0, statements.size());
  }