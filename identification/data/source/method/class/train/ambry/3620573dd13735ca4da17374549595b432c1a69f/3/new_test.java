  @Test
  public void startBlobStoreTest() throws Exception {
    MockDataNodeId dataNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> replicas = clusterMap.getReplicaIds(dataNode);
    List<MockDataNodeId> dataNodes = new ArrayList<>();
    dataNodes.add(dataNode);
    MockPartitionId invalidPartition =
        new MockPartitionId(Long.MAX_VALUE, MockClusterMap.DEFAULT_PARTITION_CLASS, dataNodes, 0);
    List<? extends ReplicaId> invalidPartitionReplicas = invalidPartition.getReplicaIds();
    StorageManager storageManager = createStorageManager(dataNode, metricRegistry, null);
    PartitionId id = null;
    storageManager.start();
    assertEquals("There should be 1 unexpected partition reported", 1, getNumUnrecognizedPartitionsReported());
    // shutdown all the replicas first
    for (ReplicaId replica : replicas) {
      id = replica.getPartitionId();
      assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
    }
    ReplicaId replica = replicas.get(0);
    id = replica.getPartitionId();
    // test start a store successfully
    assertTrue("Start should succeed on given store", storageManager.startBlobStore(id));
    // test start the store which is already started
    assertTrue("Start should succeed on the store which is already started", storageManager.startBlobStore(id));
    // test invalid partition
    replica = invalidPartitionReplicas.get(0);
    id = replica.getPartitionId();
    assertFalse("Start should fail on given invalid replica", storageManager.startBlobStore(id));
    // test start the store whose DiskManager is not running
    replica = replicas.get(replicas.size() - 1);
    id = replica.getPartitionId();
    storageManager.getDiskManager(id).shutdown();
    assertFalse("Start should fail on given store whose DiskManager is not running", storageManager.startBlobStore(id));
    shutdownAndAssertStoresInaccessible(storageManager, replicas);
  }