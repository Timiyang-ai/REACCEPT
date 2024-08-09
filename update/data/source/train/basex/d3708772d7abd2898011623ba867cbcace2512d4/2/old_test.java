@Test
  public void createDB() throws BaseXException {
    new CreateDB(DB).execute(CONTEXT);
    // check if database name equals argument of create command
    assertEquals(db(), DB);
  }