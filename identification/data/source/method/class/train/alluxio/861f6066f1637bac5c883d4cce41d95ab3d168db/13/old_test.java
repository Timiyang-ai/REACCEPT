@Test
  public void reportLostFileTest() throws Exception {
    TachyonURI path = new TachyonURI("test");
    mTachyonLineageFileSystem.reportLostFile(path);
    Mockito.verify(mLineageMasterClient).reportLostFile("test");
    // verify client is released
    Mockito.verify(mLineageContext).releaseMasterClient(mLineageMasterClient);
  }