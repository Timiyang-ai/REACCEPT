@Test
  public void createTest() throws BaseXException {
    // drops database for clean test
    sess.execute(new DropDB(NAME));
    // create database for clean test
    sess.execute(new CreateDB(NAME, FILE));
  }