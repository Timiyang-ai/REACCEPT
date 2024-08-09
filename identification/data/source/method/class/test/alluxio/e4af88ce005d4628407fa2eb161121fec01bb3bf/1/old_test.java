@Test
  public void addCheckpointTest() throws Exception {
    FileOutStream os = mTfs.getOutStream(new TachyonURI("/testFile"));
    TachyonFile file = mTfs.open(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;

    FileInfo fileInfo = mLocalTachyonCluster.getClient().getInfo(file);
    String ufsPath = fileInfo.getUfsPath();
    UnderFileSystem ufs = UnderFileSystem.get(ufsPath, mMasterTachyonConf);
    OutputStream out = ufs.create(ufsPath);

    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();
    mWorkerServiceHandler.addCheckpoint(file.getFileId(), os.getNonce());

    // No space should be used in Tachyon, but the file should be complete
    Assert.assertEquals(0, mBlockMasterClient.getUsedBytes());
    Assert.assertTrue(mTfs.getInfo(file).isCompleted);
  }