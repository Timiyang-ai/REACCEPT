@Test
  @PrepareForTest(KodoOutputStream.class)
  public void testClose() throws Exception {
    PowerMockito.whenNew(File.class).withArguments(Mockito.anyString()).thenReturn(mFile);
    FileOutputStream outputStream = PowerMockito.mock(FileOutputStream.class);
    PowerMockito.whenNew(FileOutputStream.class).withArguments(mFile).thenReturn(outputStream);
    FileInputStream inputStream = PowerMockito.mock(FileInputStream.class);
    PowerMockito.whenNew(FileInputStream.class).withArguments(mFile).thenReturn(inputStream);
    KodoOutputStream stream = new KodoOutputStream("testKey", mKodoClient);
    stream.close();
    Mockito.verify(mFile).delete();
  }