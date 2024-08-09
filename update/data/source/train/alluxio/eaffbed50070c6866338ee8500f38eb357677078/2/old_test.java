@Test
  public void getMountInfo() throws Exception {
    MountInfo info1 =
        new MountInfo(new AlluxioURI("/mnt/foo"), new AlluxioURI("hdfs://localhost:5678/foo"), 1L,
            MountOptions.defaults());
    MountInfo info2 =
        new MountInfo(new AlluxioURI("/mnt/bar"), new AlluxioURI("hdfs://localhost:5678/bar"), 2L,
            MountOptions.defaults());
    mMountTable
        .add(new AlluxioURI("/mnt/foo"), info1.getUfsUri(), info1.getMountId(), info1.getOptions());
    mMountTable
        .add(new AlluxioURI("/mnt/bar"), info2.getUfsUri(), info2.getMountId(), info2.getOptions());
    Assert.assertEquals(info1, mMountTable.getMountInfo(info1.getMountId()));
    Assert.assertEquals(info2, mMountTable.getMountInfo(info2.getMountId()));
    Assert.assertEquals(null, mMountTable.getMountInfo(3L));
  }