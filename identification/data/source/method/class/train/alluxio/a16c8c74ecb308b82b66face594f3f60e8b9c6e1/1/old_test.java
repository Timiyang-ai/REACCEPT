@Test
  public void readEOFReturnsNegativeTest() {
    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
            (String) null);
    Assert.assertFalse(UnderFileSystemCluster.readEOFReturnsNegative());

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "XXXX");
    Assert.assertFalse(UnderFileSystemCluster.readEOFReturnsNegative());

    Whitebox.setInternalState(UnderFileSystemCluster.class, "sUnderFSClass",
        "alluxio.underfs.hdfs.LocalMiniDFSCluster");
    Assert.assertTrue(UnderFileSystemCluster.readEOFReturnsNegative());
  }