@Test
  public void DeleteTest() throws Exception {
    for (int i = 0; i < 10; i ++) {
      String dirPath = PathUtils.concatPath(mMountPoint, "i" + i);
      mTfs.mkdirs(new TachyonURI(dirPath), TachyonFileSystem.RECURSIVE);
      for (int j = 0; j < 10; j ++) {
        ClientOptions option =
            new ClientOptions.Builder(mMasterTachyonConf).setBlockSize((i + j + 1) * 64).build();
        String filePath = PathUtils.concatPath(dirPath, "j" + j);
        mTfs.getOutStream(new TachyonURI(filePath), option).close();
        if (j >= 5) {
          mTfs.delete(mTfs.open(new TachyonURI(filePath)), TachyonFileSystem.RECURSIVE);
        }
      }
      if (i >= 5) {
        mTfs.delete(mTfs.open(new TachyonURI(dirPath)), TachyonFileSystem.RECURSIVE);
      }
    }
    mLocalTachyonCluster.stopTFS();
    DeleteTestUtil();
    deleteFsMasterJournalLogs();
    DeleteTestUtil();
  }