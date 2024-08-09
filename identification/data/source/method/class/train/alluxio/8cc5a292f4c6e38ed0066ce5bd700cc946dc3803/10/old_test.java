@Test
  public void rename() throws Exception {
    for (int i = 0; i < 10; i++) {
      mFileSystem.createDirectory(new AlluxioURI("/i" + i));
      for (int j = 0; j < 10; j++) {
        CreateFileOptions option = CreateFileOptions.defaults().setBlockSizeBytes((i + j + 1) * 64);
        AlluxioURI path = new AlluxioURI("/i" + i + "/j" + j);
        mFileSystem.createFile(path, option).close();
        mFileSystem.rename(path, new AlluxioURI("/i" + i + "/jj" + j));
      }
      mFileSystem.rename(new AlluxioURI("/i" + i), new AlluxioURI("/ii" + i));
    }
    mLocalAlluxioCluster.stopFS();
    renameTestUtil();
    deleteFsMasterJournalLogs();
    renameTestUtil();
  }