@Test
  @PrepareForTest(KodoOutputStream.class)
  public void testWrite2() throws Exception {
    PowerMockito.whenNew(BufferedOutputStream.class)
        .withArguments(Mockito.any(DigestOutputStream.class)).thenReturn(mLocalOutputStream);
    PowerMockito.whenNew(BufferedOutputStream.class)
        .withArguments(Mockito.any(FileOutputStream.class)).thenReturn(mLocalOutputStream);
    KodoOutputStream stream = new KodoOutputStream("testKey", mKodoClient, sTmpDirs);
    byte[] b = new byte[1];
    stream.write(b, 0, 1);
    stream.close();
    Mockito.verify(mLocalOutputStream).write(b, 0, 1);
  }