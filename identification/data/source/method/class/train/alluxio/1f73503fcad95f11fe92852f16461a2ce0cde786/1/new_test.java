@Test
  public void getUnderFSType() throws Exception {
    Assert.assertEquals("hdfs", mMockHdfsUnderFileSystem.getUnderFSType());
  }