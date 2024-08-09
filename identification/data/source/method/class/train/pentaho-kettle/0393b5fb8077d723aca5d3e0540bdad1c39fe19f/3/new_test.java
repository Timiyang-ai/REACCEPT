  @Test
  public void openFolderTest() throws KettleException, MessagingException {
    conn.openFolder( "a/b", false, false );
    Folder folder = conn.getFolder();
    Assert.assertEquals( "Folder B is opened", "B", folder.getFullName() );
  }