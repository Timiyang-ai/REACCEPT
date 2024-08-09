  @Test
  public void isDiskAvailableTest() throws Exception {
    MockDataNodeId dataNode = clusterMap.getDataNodes().get(0);
    List<ReplicaId> replicas = clusterMap.getReplicaIds(dataNode);
    Map<DiskId, List<ReplicaId>> diskToReplicas = new HashMap<>();
    StorageManager storageManager = createStorageManager(dataNode, metricRegistry, null);
    storageManager.start();
    assertEquals("There should be no unexpected partitions reported", 0, getNumUnrecognizedPartitionsReported());
    for (ReplicaId replica : replicas) {
      diskToReplicas.computeIfAbsent(replica.getDiskId(), disk -> new ArrayList<>()).add(replica);
    }
    // for each disk, shutdown all the stores except for the last one
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      for (int i = 0; i < replicasOnDisk.size() - 1; ++i) {
        storageManager.getStore(replicasOnDisk.get(i).getPartitionId(), false).shutdown();
      }
    }
    // verify all disks are still available because at least one store on them is up
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      assertTrue("Disk should be available", storageManager.isDiskAvailable(replicasOnDisk.get(0).getDiskId()));
      assertEquals("Disk state be available", HardwareState.AVAILABLE, replicasOnDisk.get(0).getDiskId().getState());
    }

    // now, shutdown the last store on each disk
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      storageManager.getStore(replicasOnDisk.get(replicasOnDisk.size() - 1).getPartitionId(), false).shutdown();
    }
    // verify all disks are unavailable because all stores are down
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      assertFalse("Disk should be unavailable", storageManager.isDiskAvailable(replicasOnDisk.get(0).getDiskId()));
    }

    // then, start the one store on each disk to test if disk is up again
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      storageManager.startBlobStore(replicasOnDisk.get(0).getPartitionId());
    }
    // verify all disks are available again because one store is started
    for (List<ReplicaId> replicasOnDisk : diskToReplicas.values()) {
      assertTrue("Disk should be available", storageManager.isDiskAvailable(replicasOnDisk.get(0).getDiskId()));
      assertEquals("Disk state be available", HardwareState.AVAILABLE, replicasOnDisk.get(0).getDiskId().getState());
    }

    shutdownAndAssertStoresInaccessible(storageManager, replicas);
  }