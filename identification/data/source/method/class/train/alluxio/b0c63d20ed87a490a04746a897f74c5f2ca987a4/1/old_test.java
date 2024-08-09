@Test
  public void PinTest() throws Exception {
    mTfs.mkdir(new TachyonURI("/myFolder"));
    int folderId = mTfs.getFileId(new TachyonURI("/myFolder"));
    mTfs.setPinned(folderId, true);
    int file0Id = mTfs.createFile(new TachyonURI("/myFolder/file0"), 64);
    mTfs.setPinned(file0Id, false);
    int file1Id = mTfs.createFile(new TachyonURI("/myFolder/file1"), 64);
    ClientFileInfo folderInfo = mLocalTachyonCluster.getMasterInfo().getClientFileInfo(folderId);
    ClientFileInfo file0Info = mLocalTachyonCluster.getMasterInfo().getClientFileInfo(file0Id);
    ClientFileInfo file1Info = mLocalTachyonCluster.getMasterInfo().getClientFileInfo(file1Id);
    mLocalTachyonCluster.stopTFS();
    PinTestUtil(folderInfo, file0Info, file1Info);
    String editLogPath = mLocalTachyonCluster.getEditLogPath();
    UnderFileSystem.get(editLogPath, mMasterTachyonConf).delete(editLogPath, true);
    PinTestUtil(folderInfo, file0Info, file1Info);
  }