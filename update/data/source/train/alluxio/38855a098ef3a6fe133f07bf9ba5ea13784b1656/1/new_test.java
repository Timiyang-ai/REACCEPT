@Test
  public void addCheckpointTest() throws Exception {
    // TODO(jiri): Fix this.
//    ClientOptions options = new ClientOptions.Builder(new TachyonConf()).build();
//    mTfs.getOutStream(new TachyonURI(PathUtils.concatPath(mMountPoint, "testFile")), options);
//    TachyonFile file = mTfs.open(new TachyonURI(PathUtils.concatPath(mMountPoint, "testFile")));
//    final int blockSize = (int) WORKER_CAPACITY_BYTES / 10;
//
//    String tmpFolder = mWorkerServiceHandler.getSessionUfsTempFolder(SESSION_ID);
//    UnderFileSystem ufs = UnderFileSystem.get(tmpFolder, mMasterTachyonConf);
//    ufs.mkdirs(tmpFolder, true);
//    String filename = PathUtils.concatPath(tmpFolder, file.getFileId());
//    OutputStream out = ufs.create(filename);
//    out.write(BufferUtils.getIncreasingByteArray(blockSize));
//    out.close();
//    mWorkerServiceHandler.addCheckpoint(SESSION_ID, (int) file.getFileId());
//
//    // No space should be used in Tachyon, but the file should be complete
//    Assert.assertEquals(0, mBlockMasterClient.getUsedBytes());
//    Assert.assertTrue(mTfs.getInfo(file).isCompleted);
  }