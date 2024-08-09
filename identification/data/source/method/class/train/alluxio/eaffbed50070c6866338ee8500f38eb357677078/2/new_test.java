@Test
  public void getMountInfo() throws Exception {
    MountInfo info1 =
        new MountInfo(new AlluxioURI("/mnt/foo"), new AlluxioURI("hdfs://localhost:5678/foo"), 2L,
            MountOptions.defaults());
    MountInfo info2 =
        new MountInfo(new AlluxioURI("/mnt/bar"), new AlluxioURI("hdfs://localhost:5678/bar"), 3L,
            MountOptions.defaults());
    addMount("/mnt/foo", "hdfs://localhost:5678/foo", 2);
    addMount("/mnt/bar", "hdfs://localhost:5678/bar", 3);
    Assert.assertEquals(info1, mMountTable.getMountInfo(info1.getMountId()));
    Assert.assertEquals(info2, mMountTable.getMountInfo(info2.getMountId()));
    Assert.assertEquals(null, mMountTable.getMountInfo(4L));
  }