@Test
  public void docTest() throws Exception {
    createDoc();
    final String doc = DOC.args(NAME);
    check(doc + "//*[text() = '1']");
    check(doc + "//*[text() contains text '2']");
    check(doc + "//a[. = '1']");
    check(doc + "//xml[a = '1']");
  }