@Test
  public void reportLostFileTest() throws Exception {
    AlluxioURI path = new AlluxioURI("test");
    mTachyonLineageFileSystem.reportLostFile(path);
    Mockito.verify(mLineageMasterClient).reportLostFile("test");
    // verify client is released
    Mockito.verify(mLineageContext).releaseMasterClient(mLineageMasterClient);
  }