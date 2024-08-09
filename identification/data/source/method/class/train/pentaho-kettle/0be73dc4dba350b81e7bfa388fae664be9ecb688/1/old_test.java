  @Test
  public void folderExistsTest() {
    boolean actual = conn.folderExists( "a/b" );
    Assert.assertTrue( "Folder B exists", actual );
  }