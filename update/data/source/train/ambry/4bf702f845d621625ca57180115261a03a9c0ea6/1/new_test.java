@Test
  public void addBlobStoreTest() throws Exception {
    generateConfigs(true);
    MockDataNodeId localNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> localReplicas = clusterMap.getReplicaIds(localNode);
    int newMountPathIndex = 3;
    // add new MountPath to local node
    File f = File.createTempFile("ambry", ".tmp");
    File mountFile =
        new File(f.getParent(), "mountpathfile" + MockClusterMap.PLAIN_TEXT_PORT_START_NUMBER + newMountPathIndex);
    MockClusterMap.deleteFileOrDirectory(mountFile);
    assertTrue("Couldn't create mount path directory", mountFile.mkdir());
    localNode.addMountPaths(Collections.singletonList(mountFile.getAbsolutePath()));
    PartitionId newPartition1 =
        new MockPartitionId(10L, MockClusterMap.DEFAULT_PARTITION_CLASS, clusterMap.getDataNodes(), newMountPathIndex);
    StorageManager storageManager = createStorageManager(localNode, metricRegistry, null);
    storageManager.start();
    // test add store that already exists, which should fail
    assertFalse("Add store which is already existing should fail", storageManager.addBlobStore(localReplicas.get(0)));
    // test add store onto a new disk, which should succeed
    assertTrue("Add new store should succeed", storageManager.addBlobStore(newPartition1.getReplicaIds().get(0)));
    assertNotNull("The store shouldn't be null because new store is successfully added",
        storageManager.getStore(newPartition1, false));
    // test add store whose diskManager is not running, which should fail
    PartitionId newPartition2 =
        new MockPartitionId(11L, MockClusterMap.DEFAULT_PARTITION_CLASS, clusterMap.getDataNodes(), 0);
    storageManager.getDiskManager(localReplicas.get(0).getPartitionId()).shutdown();
    assertFalse("Add store onto the DiskManager which is not running should fail",
        storageManager.addBlobStore(newPartition2.getReplicaIds().get(0)));
    storageManager.getDiskManager(localReplicas.get(0).getPartitionId()).start();
    shutdownAndAssertStoresInaccessible(storageManager, localReplicas);
    // test add store but fail to add segment requirements to DiskSpaceAllocator. (This is simulated by inducing
    // addRequiredSegments failure to make store inaccessible)
    List<String> mountPaths = localNode.getMountPaths();
    String diskToFail = mountPaths.get(0);
    File reservePoolDir = new File(diskToFail, diskManagerConfig.diskManagerReserveFileDirName);
    File storeReserveDir = new File(reservePoolDir, DiskSpaceAllocator.STORE_DIR_PREFIX + newPartition2.toString());
    StorageManager storageManager2 = createStorageManager(localNode, new MetricRegistry(), null);
    storageManager2.start();
    Utils.deleteFileOrDirectory(storeReserveDir);
    assertTrue("File creation should succeed", storeReserveDir.createNewFile());

    assertFalse("Add store should fail if store couldn't start due to initializePool failure",
        storageManager2.addBlobStore(newPartition2.getReplicaIds().get(0)));
    assertNull("New store shouldn't be in in-memory data structure", storageManager2.getStore(newPartition2, false));
    shutdownAndAssertStoresInaccessible(storageManager2, localReplicas);
  }