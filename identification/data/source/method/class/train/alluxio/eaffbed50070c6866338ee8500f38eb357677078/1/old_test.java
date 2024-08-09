@Test
  public void getMountTable() throws Exception {
    Map<String, MountInfo> mountTable = new HashMap<>(2);
    mountTable.put("/mnt/foo",
        new MountInfo(new AlluxioURI("/mnt/foo"), new AlluxioURI("hdfs://localhost:5678/foo"), 1L,
            MountOptions.defaults()));
    mountTable.put("/mnt/bar",
        new MountInfo(new AlluxioURI("/mnt/bar"), new AlluxioURI("hdfs://localhost:5678/bar"), 2L,
            MountOptions.defaults()));

    AlluxioURI masterAddr = new AlluxioURI("alluxio://localhost:1234");
    for (Map.Entry<String, MountInfo> mountPoint : mountTable.entrySet()) {
      MountInfo mountInfo = mountPoint.getValue();
      mMountTable.add(masterAddr.join(mountPoint.getKey()),
          mountInfo.getUfsUri(), mountInfo.getMountId(), mountInfo.getOptions());
    }
    Assert.assertEquals(mountTable, mMountTable.getMountTable());
  }