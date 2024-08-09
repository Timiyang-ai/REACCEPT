@Test
  public void RenameTest() throws Exception {
    for (int i = 0; i < 10; i ++) {
      mTfs.mkdirs(new TachyonURI(PathUtils.concatPath(mMountPoint, "i" + i)));
      for (int j = 0; j < 10; j ++) {
        ClientOptions option =
            new ClientOptions.Builder(mMasterTachyonConf).setBlockSize((i + j + 1) * 64).build();
        TachyonURI path = new TachyonURI(PathUtils.concatPath(mMountPoint, "i" + i, "j" + j));
        mTfs.getOutStream(path, option).close();
        mTfs.rename(mTfs.open(path),
            new TachyonURI(PathUtils.concatPath(mMountPoint, "i" + i, "jj" + j)));
      }
      mTfs.rename(mTfs.open(new TachyonURI(PathUtils.concatPath(mMountPoint, "i" + i))),
          new TachyonURI(PathUtils.concatPath(mMountPoint, "ii" + i)));
    }
    mLocalTachyonCluster.stopTFS();
    RenameTestUtil();
    deleteFsMasterJournalLogs();
    RenameTestUtil();
  }