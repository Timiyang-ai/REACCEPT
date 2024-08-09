@Test
  public void getUnderFSClassTest() {
    String underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertEquals("alluxio.underfs.LocalFileSystemCluster", underFSClass);

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "alluxio.underfs.hdfs.LocalMiniDFSCluster");
    underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertEquals("alluxio.underfs.hdfs.LocalMiniDFSCluster", underFSClass);
  }