@Test
  public void testAdd() throws Exception {
    new Add("", DIR).execute(context);
    assertAllFilesExist();
  }