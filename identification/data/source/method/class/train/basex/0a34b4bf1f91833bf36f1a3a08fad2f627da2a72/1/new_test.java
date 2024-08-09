@Test
  public void createTest() throws IOException {
    // drops database for clean test
    sess.execute(new DropDB(NAME));
    // create database for clean test
    sess.execute(new CreateDB(NAME, FILE));
  }