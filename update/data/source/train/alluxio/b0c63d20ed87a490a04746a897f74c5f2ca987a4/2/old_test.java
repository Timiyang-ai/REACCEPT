@Test
  public void lockBlockTest() throws Exception {
    final int fileId = mTfs.createFile(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;
    final long blockId = mTfs.getBlockId(fileId, 0);

    OutStream out = mTfs.getFile(fileId).getOutStream(WriteType.MUST_CACHE);
    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();

    String localPath = mWorkerServiceHandler.lockBlock(blockId, USER_ID);

    // The local path should exist
    Assert.assertNotNull(localPath);

    UnderFileSystem ufs = UnderFileSystem.get(localPath, mMasterTachyonConf);
    byte[] data = new byte[blockSize];
    int bytesRead = ufs.open(localPath).read(data);

    // The data in the local file should equal the data we wrote earlier
    Assert.assertEquals(blockSize, bytesRead);
    Assert.assertTrue(BufferUtils.equalIncreasingByteArray(bytesRead, data));

    mWorkerServiceHandler.unlockBlock(blockId, USER_ID);
  }