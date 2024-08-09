@Test
  public void reinitializeFile() throws Exception {
    FileInfo fileInfo = new FileInfo();
    fileInfo.setCompleted(false);
    Mockito.when(mFileSystemMaster.getFileInfo(Mockito.any(Long.class))).thenReturn(fileInfo);
    mLineageMaster.start(true);
    mLineageMaster.createLineage(new ArrayList<AlluxioURI>(),
        Lists.newArrayList(new AlluxioURI("/test1")), mJob);
    mLineageMaster.reinitializeFile("/test1", 500L, 10L, TtlExpiryAction.DELETE);
    Mockito.verify(mFileSystemMaster).reinitializeFile(new AlluxioURI("/test1"), 500L, 10L,
        TtlExpiryAction.DELETE);
  }