@Test
  public void downgradeTest() throws BaseXException {
    // hangs if QueryContext.downgrade call is activated..
    new CreateDB(NAME, "<x/>").execute(context);
    new XQuery("delete node /y").execute(context);
    new XQuery("let $d := '" + NAME + "' return doc($d)").execute(context);
  }