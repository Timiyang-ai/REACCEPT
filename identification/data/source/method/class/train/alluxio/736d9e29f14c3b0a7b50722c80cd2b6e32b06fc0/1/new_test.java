@Test
  public void lockBlockTest() throws Exception {
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;

    OutStreamOptions options =
        new OutStreamOptions.Builder(new TachyonConf()).setBlockSizeBytes(blockSize)
            .setTachyonStorageType(TachyonStorageType.STORE).build();
    FileOutStream out = mTfs.getOutStream(new TachyonURI("/testFile"), options);
    TachyonFile file = mTfs.open(new TachyonURI("/testFile"));

    final long blockId = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);

    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();

    String localPath = mWorkerServiceHandler.lockBlock(blockId, SESSION_ID).blockPath;

    // The local path should exist
    Assert.assertNotNull(localPath);

    UnderFileSystem ufs = UnderFileSystem.get(localPath, mMasterTachyonConf);
    byte[] data = new byte[blockSize];
    int bytesRead = ufs.open(localPath).read(data);

    // The data in the local file should equal the data we wrote earlier
    Assert.assertEquals(blockSize, bytesRead);
    Assert.assertTrue(BufferUtils.equalIncreasingByteArray(bytesRead, data));

    mWorkerServiceHandler.unlockBlock(blockId, SESSION_ID);
  }