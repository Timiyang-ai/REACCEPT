@Test
  public void pinTest() throws Exception {
    SetStateOptions setPinned =
        new SetStateOptions.Builder(new TachyonConf()).setPinned(true).build();
    SetStateOptions setUnpinned =
        new SetStateOptions.Builder(new TachyonConf()).setPinned(false).build();
    mTfs.mkdir(new TachyonURI("/myFolder"));
    TachyonFile directory = mTfs.open(new TachyonURI("/myFolder"));
    mTfs.setState(directory, setPinned);

    TachyonURI file0Path = new TachyonURI("/myFolder/file0");
    OutStreamOptions op =
        new OutStreamOptions.Builder(mMasterTachyonConf).setBlockSizeBytes(64).build();
    mTfs.getOutStream(file0Path, op).close();
    TachyonFile file0 = mTfs.open(file0Path);
    mTfs.setState(file0, setUnpinned);

    TachyonURI file1Path = new TachyonURI("/myFolder/file1");
    mTfs.getOutStream(file1Path, op).close();

    FileInfo directoryInfo = mTfs.getInfo(directory);
    FileInfo file0Info = mTfs.getInfo(file0);
    FileInfo file1Info = mTfs.getInfo(mTfs.open(file1Path));

    mLocalTachyonCluster.stopTFS();

    pinTestUtil(directoryInfo, file0Info, file1Info);
    deleteFsMasterJournalLogs();
    pinTestUtil(directoryInfo, file0Info, file1Info);
  }