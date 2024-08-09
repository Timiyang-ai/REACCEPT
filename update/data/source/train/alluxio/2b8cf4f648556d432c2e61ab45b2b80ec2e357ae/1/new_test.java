@Test
  public void lockBlock() throws Exception {
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 2;

    CreateFileOptions options =
        CreateFileOptions.defaults().setBlockSizeBytes(blockSize)
            .setWriteType(WriteType.MUST_CACHE);
    FileOutStream out = mFileSystem.createFile(new AlluxioURI("/testFile"), options);
    URIStatus file = mFileSystem.getStatus(new AlluxioURI("/testFile"));

    final long blockId = BlockId.createBlockId(BlockId.getContainerId(file.getFileId()), 0);

    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();

    String localPath =
        mBlockWorkerServiceHandler.lockBlock(blockId, SESSION_ID, new LockBlockTOptions())
            .getBlockPath();

    // The local path should exist
    Assert.assertNotNull(localPath);

    UnderFileSystem ufs = UnderFileSystem.Factory.get(localPath);
    byte[] data = new byte[blockSize];
    InputStream in = ufs.open(localPath);
    int bytesRead = in.read(data);

    // The data in the local file should equal the data we wrote earlier
    Assert.assertEquals(blockSize, bytesRead);
    Assert.assertTrue(BufferUtils.equalIncreasingByteArray(bytesRead, data));

    mBlockWorkerServiceHandler.unlockBlock(blockId, SESSION_ID);
  }