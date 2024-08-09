@Test
  public void testSetUpNewTable() {
    // Insufficient instances - 2 replicas, 1 instance, 4 partitions
    testSetUpNewTable(2, 1, 4, true);

    // Noop path - 2 replicas, 3 instances, 0 partition
    testSetUpNewTable(2, 3, 0, false);

    // Happy paths
    // 2 replicas, 3 instances, 4 partitions
    testSetUpNewTable(2, 3, 4, false);
    // 2 replicas, 3 instances, 8 partitions
    testSetUpNewTable(2, 3, 8, false);
    // 8 replicas, 10 instances, 4 partitions
    testSetUpNewTable(8, 10, 4, false);
  }