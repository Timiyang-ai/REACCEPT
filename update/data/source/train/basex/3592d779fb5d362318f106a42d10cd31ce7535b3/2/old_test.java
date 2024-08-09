@Test
  public void createDB() throws BaseXException {
    new CreateDB(NAME).execute(context);
    // check if database name equals argument of create command
    assertEquals(db(), NAME);
  }