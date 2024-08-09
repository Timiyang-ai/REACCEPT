  @Test
  public void areAllReplicasForPartitionUpTest() {
    MockDataNodeId dn1 = getDataNodeId("dn1", "DC1");
    MockDataNodeId dn2 = getDataNodeId("dn2", "DC2");
    MockPartitionId partitionId = new MockPartitionId(1, "default", Arrays.asList(dn1, dn2), 0);
    MockReplicaId replicaId1 = (MockReplicaId) partitionId.getReplicaIds().get(0);
    MockReplicaId replicaId2 = (MockReplicaId) partitionId.getReplicaIds().get(1);
    assertTrue("All replicas should be up", ClusterMapUtils.areAllReplicasForPartitionUp(partitionId));
    replicaId1.markReplicaDownStatus(true);
    assertFalse("Not all replicas should be up", ClusterMapUtils.areAllReplicasForPartitionUp(partitionId));
    replicaId2.markReplicaDownStatus(true);
    assertFalse("Not all replicas should be up", ClusterMapUtils.areAllReplicasForPartitionUp(partitionId));
    replicaId1.markReplicaDownStatus(false);
    assertFalse("Not all replicas should be up", ClusterMapUtils.areAllReplicasForPartitionUp(partitionId));
    replicaId2.markReplicaDownStatus(false);
    assertTrue("All replicas should be up", ClusterMapUtils.areAllReplicasForPartitionUp(partitionId));
  }