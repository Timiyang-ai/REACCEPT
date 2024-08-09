@Test
  public void pinTest() throws Exception {
    mTfs.mkdirs(new TachyonURI("/myFolder"));
    TachyonFile folder = mTfs.open(new TachyonURI("/myFolder"));
    mTfs.setPin(folder, true);

    TachyonURI file0Path = new TachyonURI("/myFolder/file0");
    ClientOptions op = new ClientOptions.Builder(mMasterTachyonConf).setBlockSize(64).build();
    mTfs.getOutStream(file0Path, op).close();
    TachyonFile file0 = mTfs.open(file0Path);
    mTfs.setPin(file0, false);

    TachyonURI file1Path = new TachyonURI("/myFolder/file1");
    mTfs.getOutStream(file1Path, op).close();

    FileInfo folderInfo = mTfs.getInfo(folder);
    FileInfo file0Info = mTfs.getInfo(file0);
    FileInfo file1Info = mTfs.getInfo(mTfs.open(file1Path));

    mLocalTachyonCluster.stopTFS();

    pinTestUtil(folderInfo, file0Info, file1Info);
    deleteFsMasterJournalLogs();
    pinTestUtil(folderInfo, file0Info, file1Info);
  }