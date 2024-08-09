@Test
  public void createDB() throws BaseXException {
    new CreateDB(DBNAME).execute(CONTEXT);
    // check if database name equals argument of create command
    assertEquals(dbName(), DBNAME);
  }