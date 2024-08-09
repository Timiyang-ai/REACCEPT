@Test
  public void DeleteTest() throws Exception {
    for (int i = 0; i < 10; i ++) {
      mTfs.mkdir(new TachyonURI("/i" + i));
      for (int j = 0; j < 10; j ++) {
        mTfs.createFile(new TachyonURI("/i" + i + "/j" + j), (i + j + 1) * 64);
        if (j >= 5) {
          mTfs.delete(new TachyonURI("/i" + i + "/j" + j), false);
        }
      }
      if (i >= 5) {
        mTfs.delete(new TachyonURI("/i" + i), true);
      }
    }
    mLocalTachyonCluster.stopTFS();
    DeleteTestUtil();
    String editLogPath = mLocalTachyonCluster.getEditLogPath();
    UnderFileSystem.get(editLogPath, mMasterTachyonConf).delete(editLogPath, true);
    DeleteTestUtil();
  }