@Test
  public void addCheckpointTest() throws Exception {
    ClientOptions options = new ClientOptions.Builder(new TachyonConf()).build();
    mTfs.getOutStream(new TachyonURI("/testFile"), options);
    TachyonFile file = mTfs.open(new TachyonURI("/testFile"));
    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;

    String tmpFolder = mWorkerServiceHandler.getUserUfsTempFolder(USER_ID);
    UnderFileSystem ufs = UnderFileSystem.get(tmpFolder, mMasterTachyonConf);
    ufs.mkdirs(tmpFolder, true);
    String filename = PathUtils.concatPath(tmpFolder, file.getFileId());
    OutputStream out = ufs.create(filename);
    out.write(BufferUtils.getIncreasingByteArray(blockSize));
    out.close();
    mWorkerServiceHandler.addCheckpoint(USER_ID, (int) file.getFileId());

    // No space should be used in Tachyon, but the file should be complete
    Assert.assertEquals(0, mBlockMasterClient.getUsedBytes());
    Assert.assertTrue(mTfs.getInfo(file).isComplete);
  }