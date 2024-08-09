@Test
  public void AddCheckpointTest() throws Exception {
    TestUtils.createByteFile(mTfs, "/xyz", WriteType.THROUGH, 10);
    ClientFileInfo fInfo =
        mLocalTachyonCluster.getMasterInfo().getClientFileInfo(new TachyonURI("/xyz"));
    mTfs.createFile(new TachyonURI("/xyz_ck"), new TachyonURI(fInfo.getUfsPath()));
    ClientFileInfo ckFileInfo =
        mLocalTachyonCluster.getMasterInfo().getClientFileInfo(new TachyonURI("/xyz_ck"));
    mLocalTachyonCluster.stopTFS();
    AddCheckpointTestUtil(fInfo, ckFileInfo);
    String editLogPath = mLocalTachyonCluster.getEditLogPath();
    UnderFileSystem.get(editLogPath).delete(editLogPath, true);
    AddCheckpointTestUtil(fInfo, ckFileInfo);
  }