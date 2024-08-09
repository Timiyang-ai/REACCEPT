@Test
  public void reinitializeFileTest() throws Exception {
    mLineageMaster.createLineage(Lists.<TachyonURI>newArrayList(),
        Lists.newArrayList(new TachyonURI("/test1")), mJob);
    FileInfo fileInfo = new FileInfo();
    fileInfo.setCompleted(false);
    Mockito.when(mFileSystemMaster.getFileInfo(Mockito.any(Long.class))).thenReturn(fileInfo);
    mLineageMaster.reinitializeFile("/test1", 500L, 10L);
    Mockito.verify(mFileSystemMaster).reinitializeFile(new TachyonURI("/test1"), 500L, 10L);
  }