@Test
  public void testAdd() {
    execute(new Add("", DIR));
    assertAllFilesExist();
  }