@Test
  public void getUnderFSClassTest() {
    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        (String) null);
    String underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertNull(underFSClass);

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "alluxio.underfs.hdfs.LocalMiniDFSCluster");
    underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertEquals("alluxio.underfs.hdfs.LocalMiniDFSCluster", underFSClass);
  }