@Test
  public void DeleteTest() throws Exception {
    for (int i = 0; i < 10; i ++) {
      String dirPath = "/i" + i;
      mTfs.mkdirs(new TachyonURI(dirPath));
      for (int j = 0; j < 10; j ++) {
        ClientOptions option =
            new ClientOptions.Builder(mMasterTachyonConf).setBlockSize((i + j + 1) * 64).build();
        String filePath = dirPath + "/j" + j;
        mTfs.getOutStream(new TachyonURI(filePath), option).close();
        if (j >= 5) {
          mTfs.delete(mTfs.open(new TachyonURI(filePath)));
        }
      }
      if (i >= 5) {
        mTfs.delete(mTfs.open(new TachyonURI(dirPath)));
      }
    }
    mLocalTachyonCluster.stopTFS();
    DeleteTestUtil();
    deleteFsMasterJournalLogs();
    DeleteTestUtil();
  }