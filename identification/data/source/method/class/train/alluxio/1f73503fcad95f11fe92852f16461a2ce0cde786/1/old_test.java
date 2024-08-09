@Test
  public void getUnderFSType() throws Exception {
    Assert.assertEquals(UnderFSType.HDFS, mMockHdfsUnderFileSystem.getUnderFSType());
  }