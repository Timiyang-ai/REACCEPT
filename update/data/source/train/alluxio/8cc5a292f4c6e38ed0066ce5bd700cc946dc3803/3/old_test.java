@Test
  public void close() throws Exception {
    mTestStream.write(BufferUtils.getIncreasingByteArray((int) (BLOCK_LENGTH * 1.5)));
    mTestStream.close();
    for (long streamIndex = 0; streamIndex < 2; streamIndex++) {
      Assert.assertFalse(mAlluxioOutStreamMap.get(streamIndex).isCanceled());
      Assert.assertTrue(mAlluxioOutStreamMap.get(streamIndex).isClosed());
    }
    verify(mFileSystemMasterClient).completeFile(eq(FILE_NAME), any(CompleteFileOptions.class));
  }