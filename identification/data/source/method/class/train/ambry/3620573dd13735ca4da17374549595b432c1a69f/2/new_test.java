  @Test
  public void shutdownBlobStoreTest() throws Exception {
    MockDataNodeId dataNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> replicas = clusterMap.getReplicaIds(dataNode);
    List<MockDataNodeId> dataNodes = new ArrayList<>();
    dataNodes.add(dataNode);
    MockPartitionId invalidPartition =
        new MockPartitionId(Long.MAX_VALUE, MockClusterMap.DEFAULT_PARTITION_CLASS, dataNodes, 0);
    List<? extends ReplicaId> invalidPartitionReplicas = invalidPartition.getReplicaIds();
    StorageManager storageManager = createStorageManager(dataNode, metricRegistry, null);
    storageManager.start();
    assertEquals("There should be 1 unexpected partition reported", 1, getNumUnrecognizedPartitionsReported());
    for (int i = 1; i < replicas.size() - 1; i++) {
      ReplicaId replica = replicas.get(i);
      PartitionId id = replica.getPartitionId();
      assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
    }
    // test shutdown the store which is not started
    ReplicaId replica = replicas.get(replicas.size() - 1);
    PartitionId id = replica.getPartitionId();
    Store store = storageManager.getStore(id, false);
    store.shutdown();
    assertTrue("Shutdown should succeed on the store which is not started", storageManager.shutdownBlobStore(id));
    // test shutdown the store whose DiskManager is not running
    replica = replicas.get(0);
    id = replica.getPartitionId();
    storageManager.getDiskManager(id).shutdown();
    assertFalse("Shutdown should fail on given store whose DiskManager is not running",
        storageManager.shutdownBlobStore(id));
    // test invalid partition
    replica = invalidPartitionReplicas.get(0);
    id = replica.getPartitionId();
    assertFalse("Shutdown should fail on given invalid replica", storageManager.shutdownBlobStore(id));
    shutdownAndAssertStoresInaccessible(storageManager, replicas);
  }