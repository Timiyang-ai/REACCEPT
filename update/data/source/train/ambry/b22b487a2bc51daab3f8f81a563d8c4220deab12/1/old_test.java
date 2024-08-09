@Test
  public void removeBlobStoreTest() throws Exception {
    MockDataNodeId dataNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> replicas = clusterMap.getReplicaIds(dataNode);
    List<MockDataNodeId> dataNodes = new ArrayList<>();
    dataNodes.add(dataNode);
    MockPartitionId invalidPartition =
        new MockPartitionId(Long.MAX_VALUE, MockClusterMap.DEFAULT_PARTITION_CLASS, dataNodes, 0);
    StorageManager storageManager = createStorageManager(replicas, metricRegistry, null);
    storageManager.start();
    // shut down replica[1] ~ replica[size - 2]. The replica[0] will be used to test removing store that disk is not running
    // Replica[1] will be used to test removing a started store. Replica[2] will be used to test a store with compaction enabled
    for (int i = 3; i < replicas.size(); i++) {
      ReplicaId replica = replicas.get(i);
      PartitionId id = replica.getPartitionId();
      assertTrue("Disable compaction should succeed", storageManager.controlCompactionForBlobStore(id, false));
      assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
      assertTrue("Removing store should succeed", storageManager.removeBlobStore(id));
      assertNull("The store should not exist", storageManager.getStore(id));
    }
    // test remove store that compaction is still enabled on it, even though it is shutdown
    PartitionId id = replicas.get(2).getPartitionId();
    assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
    assertFalse("Removing store should fail because compaction is enabled on this store",
        storageManager.removeBlobStore(id));
    // test remove store that is still started
    id = replicas.get(1).getPartitionId();
    assertFalse("Removing store should fail because store is still started", storageManager.removeBlobStore(id));
    // test remove store that the disk manager is not running
    id = replicas.get(0).getPartitionId();
    storageManager.getDiskManager(id).shutdown();
    assertFalse("Removing store should fail because disk manager is not running", storageManager.removeBlobStore(id));
    // test a store that doesn't exist
    assertTrue("Removing not-found store should be considered success",
        storageManager.removeBlobStore(invalidPartition));
    shutdownAndAssertStoresInaccessible(storageManager, replicas);

    // test that remove store when compaction executor is not instantiated
    // by default, storeCompactionTriggers = "" which makes compaction executor = null during initialization
    VerifiableProperties vProps = new VerifiableProperties(new Properties());
    storageManager =
        new StorageManager(new StoreConfig(vProps), diskManagerConfig, Utils.newScheduler(1, false), metricRegistry,
            replicas, new MockIdFactory(), new DummyMessageStoreRecovery(), new DummyMessageStoreHardDelete(), null,
            SystemTime.getInstance());
    storageManager.start();
    for (ReplicaId replica : replicas) {
      id = replica.getPartitionId();
      assertTrue("Disable compaction should succeed", storageManager.controlCompactionForBlobStore(id, false));
      assertTrue("Shutdown should succeed on given store", storageManager.shutdownBlobStore(id));
      assertTrue("Removing store should succeed", storageManager.removeBlobStore(id));
      assertNull("The store should not exist", storageManager.getStore(id));
    }
    shutdownAndAssertStoresInaccessible(storageManager, replicas);
  }