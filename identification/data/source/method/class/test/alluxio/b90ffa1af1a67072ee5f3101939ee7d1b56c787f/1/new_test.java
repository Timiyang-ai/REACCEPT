@Test
  public void deleteTest() throws Exception {
    CreateDirectoryOptions recMkdir = CreateDirectoryOptions.defaults().setRecursive(true);
    DeleteOptions recDelete = DeleteOptions.defaults().setRecursive(true);
    for (int i = 0; i < 10; i ++) {
      String dirPath = "/i" + i;
      mTfs.createDirectory(new TachyonURI(dirPath), recMkdir);
      for (int j = 0; j < 10; j ++) {
        CreateFileOptions option = CreateFileOptions.defaults().setBlockSizeBytes((i + j + 1) * 64);
        String filePath = dirPath + "/j" + j;
        mTfs.createFile(new TachyonURI(filePath), option).close();
        if (j >= 5) {
          mTfs.delete(new TachyonURI(filePath), recDelete);
        }
      }
      if (i >= 5) {
        mTfs.delete(new TachyonURI(dirPath), recDelete);
      }
    }
    mLocalTachyonCluster.stopTFS();
    deleteTestUtil();
    deleteFsMasterJournalLogs();
    deleteTestUtil();
  }