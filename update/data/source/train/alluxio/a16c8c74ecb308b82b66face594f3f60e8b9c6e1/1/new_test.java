@Test
  public void readEOFReturnsNegativeTest() {
    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        (String) null);
    String underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertFalse(underFSClass != null
        && underFSClass.equals("alluxio.underfs.hdfs.LocalMiniDFSCluster"));

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "XXXX");
    underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertFalse(underFSClass != null
        && underFSClass.equals("alluxio.underfs.hdfs.LocalMiniDFSCluster"));

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "alluxio.underfs.hdfs.LocalMiniDFSCluster");
    underFSClass = UnderFileSystemCluster.getUnderFSClass();
    Assert.assertTrue(underFSClass != null
        && underFSClass.equals("alluxio.underfs.hdfs.LocalMiniDFSCluster"));
  }