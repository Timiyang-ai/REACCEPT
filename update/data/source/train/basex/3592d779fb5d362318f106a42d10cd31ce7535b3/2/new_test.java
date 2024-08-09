@Test
  public void createDB() {
    execute(new CreateDB(NAME));
    // check if database name equals argument of create command
    assertEquals(db(), NAME);
  }