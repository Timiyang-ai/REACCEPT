@Test
  public void AddCheckpointTest() throws Exception {
    ClientOptions options = new ClientOptions.Builder(mMasterTachyonConf)
        .setCacheType(CacheType.NO_CACHE).setUnderStorageType(UnderStorageType.PERSIST).build();
    TachyonFSTestUtils.createByteFile(mTfs, "/xyz", options, 10);
    FileInfo fInfo = mTfs.getInfo(mTfs.open(new TachyonURI("/xyz")));
    TachyonURI ckPath = new TachyonURI("/xyz_ck");
    // TODO(cc): what's the counterpart in the new client API for this?
    //mTfs.createFile(new TachyonURI("/xyz_ck"), new TachyonURI(fInfo.getUfsPath()));
    FileInfo ckFileInfo = mTfs.getInfo(mTfs.open(ckPath));
    mLocalTachyonCluster.stopTFS();
    AddCheckpointTestUtil(fInfo, ckFileInfo);
    deleteFsMasterJournalLogs();
    AddCheckpointTestUtil(fInfo, ckFileInfo);
  }