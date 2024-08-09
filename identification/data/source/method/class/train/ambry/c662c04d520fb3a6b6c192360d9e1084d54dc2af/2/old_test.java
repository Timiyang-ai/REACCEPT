@Test
  public void shutdownBlobStoreTest() throws Exception {
    MockDataNodeId dataNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> replicas = clusterMap.getReplicaIds(dataNode);
    List<MockDataNodeId> dataNodes = new ArrayList<>();
    dataNodes.add(dataNode);
    MockPartitionId invalidPartition = new MockPartitionId(Long.MAX_VALUE, dataNodes, 0);
    List<? extends ReplicaId> invalidPartitionReplicas = invalidPartition.getReplicaIds();
    StorageManager storageManager = createStorageManager(replicas, metricRegistry);
    storageManager.start();
    for (int i = 0; i < replicas.size() - 1; i++) {
      ReplicaId replica = replicas.get(i);
      PartitionId id = replica.getPartitionId();
      assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
    }
    // test shutdown the store which is not started
    ReplicaId replica = replicas.get(replicas.size() - 1);
    PartitionId id = replica.getPartitionId();
    Store store = storageManager.getStore(id);
    store.shutdown();
    assertFalse("Shutdown should fail on given store", storageManager.shutdownBlobStore(id));
    // test invalid partition
    replica = invalidPartitionReplicas.get(0);
    id = replica.getPartitionId();
    assertFalse("Shutdown should fail on given invalid replica", storageManager.shutdownBlobStore(id));
    shutdownAndAssertStoresInaccessible(storageManager, replicas);
  }