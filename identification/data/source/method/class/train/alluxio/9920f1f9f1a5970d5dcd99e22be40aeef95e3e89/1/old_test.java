@Test
  public void RenameTest() throws Exception {
    for (int i = 0; i < 10; i ++) {
      mTfs.mkdir(new TachyonURI("/i" + i));
      for (int j = 0; j < 10; j ++) {
        mTfs.createFile(new TachyonURI("/i" + i + "/j" + j), (i + j + 1) * 64);
        mTfs.rename(new TachyonURI("/i" + i + "/j" + j), new TachyonURI("/i" + i + "/jj" + j));
      }
      mTfs.rename(new TachyonURI("/i" + i), new TachyonURI("/ii" + i));
    }
    mLocalTachyonCluster.stopTFS();
    RenameTestUtil();
    String editLogPath = mLocalTachyonCluster.getEditLogPath();
    UnderFileSystem.get(editLogPath).delete(editLogPath, true);
    RenameTestUtil();
  }