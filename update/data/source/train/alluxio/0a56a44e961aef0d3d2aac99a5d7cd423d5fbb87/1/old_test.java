@Test
  public void AddCheckpointTest() throws Exception {
    TachyonFSTestUtils.createByteFile(mTfs, "/xyz", WriteType.THROUGH, 10);
    FileInfo fInfo =
        mLocalTachyonCluster.getMasterInfo().getClientFileInfo(new TachyonURI("/xyz"));
    mTfs.createFile(new TachyonURI("/xyz_ck"), new TachyonURI(fInfo.getUfsPath()));
    FileInfo ckFileInfo =
        mLocalTachyonCluster.getMasterInfo().getClientFileInfo(new TachyonURI("/xyz_ck"));
    mLocalTachyonCluster.stopTFS();
    AddCheckpointTestUtil(fInfo, ckFileInfo);
    String editLogPath = mLocalTachyonCluster.getEditLogPath();
    UnderFileSystem.get(editLogPath, mMasterTachyonConf).delete(editLogPath, true);
    AddCheckpointTestUtil(fInfo, ckFileInfo);
  }