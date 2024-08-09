  @Test
  public void setDestinationFolderTest() throws KettleException, MessagingException {
    conn.setDestinationFolder( "a/b/c", true );
    Assert.assertTrue( "Folder C created", conn.cCreated );
    Assert.assertEquals( "Folder created with holds messages mode", Folder.HOLDS_MESSAGES, conn.mode.intValue() );
  }